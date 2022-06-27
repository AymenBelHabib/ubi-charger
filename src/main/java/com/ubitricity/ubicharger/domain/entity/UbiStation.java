package com.ubitricity.ubicharger.domain.entity;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UbiStation {

    private final Set<UbiChargingPoint> chargingPoints = Collections.newSetFromMap(
            new ConcurrentHashMap<>(10));
    int availableAmp;

    public UbiStation() {
        availableAmp = 100;

    }

    public List<UbiChargingPoint> getChargingPoints() {
        return chargingPoints.stream().toList();
    }
    public boolean isFull ()
    {
        return chargingPoints.size()==10;
    }
    public boolean isEmpty() { return chargingPoints.size()==0; }
    public synchronized void plugCar(UbiChargingPoint chargingPoint) {

        if (availableAmp >= 20) {
            // add in fast mode and remove 20 from station available Amp
            chargingPoint.plugInCar(20, new Date(), true);
            chargingPoints.add(chargingPoint);
            availableAmp -= 20;

        } else if (availableAmp == 10) {
            // add in fast mode and remove 10 from station available Amp
            chargingPoint.plugInCar(10, new Date(), true);

            availableAmp -=10;

        } else {
            // get list of plugged chargerPoints
            List<UbiChargingPoint> pluggedChargers = chargingPoints.stream().toList();

            // get List of CP using 20 Amp
            List<UbiChargingPoint> chargingPointTpUpdate = pluggedChargers.stream().
                    filter(chargingPoint1 -> chargingPoint1.getUsedAmp() == 20)
                    .collect(Collectors.toList());
            // sort pluggedChargers from oldest plugged to newest
            Collections.sort(chargingPointTpUpdate,
                    Comparator.comparing(UbiChargingPoint::getPluggedInDate));
            // set oldest Two CP to 10 amp
            chargingPointTpUpdate.get(0).setUsedAmp(10);
            chargingPointTpUpdate.get(1).setUsedAmp(10);
            chargingPoint.plugInCar(20, new Date(), true);
            chargingPoints.add(chargingPoint);
        }

        // notify all charging point about the update in charging Points sheet
        notifyCPAmpUpdate();

    }

    public synchronized  void unPlugCharger(UbiChargingPoint chargingPointToDelete) {
        // get full info of the CP
        UbiChargingPoint foundChargingPoint=chargingPoints.stream().filter(
                chargingPoint -> chargingPoint.getCpNumber() == chargingPointToDelete.getCpNumber()
        ).findFirst().get();

        // set the Amp that was used by the CP as available
        availableAmp += foundChargingPoint.getUsedAmp();
        chargingPoints.remove(foundChargingPoint); // this is safe due to thread-safe Set
        // redistribute available amp on Used CP if there is a low Charging CP
        if (availableAmp>0&& chargingPoints.size()>=5)
        redistributeAvailableAmp();
        notifyCPAmpUpdate();

    }

    private void redistributeAvailableAmp()
    {
        List<UbiChargingPoint> pluggedChargers = chargingPoints.stream().toList();

        List<UbiChargingPoint> chargingPointTpUpdate = pluggedChargers.stream().
                filter(chargingPoint1 -> chargingPoint1.getUsedAmp() == 10)
                .collect(Collectors.toList());
        Collections.sort(chargingPointTpUpdate,
                Comparator.comparing(UbiChargingPoint::getPluggedInDate).reversed());
        int i=0;
        while (availableAmp>=10)
        {
            chargingPointTpUpdate.get(i).setUsedAmp(20);
            availableAmp-=10;
            i++;
        }

    }

    private void notifyCPAmpUpdate() {
        for (UbiChargingPoint chargingPoint : chargingPoints) { // this is safe due to thread-safe Set
            chargingPoint.updatedAmpDispatchingTrack(chargingPoints.stream().toList());
        }
    }



}

package com.ubitricity.ubicharger.domain.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UbiChargingPoint {

    private Integer cpNumber;
    private Integer usedAmp;
    private Date pluggedInDate;
    private boolean isPlugged;
    private List<UbiChargingPoint> cpAmpDispatchingTrack;

    public UbiChargingPoint() {

        usedAmp = 0;
        isPlugged = false;
    }

    public Integer getCpNumber() {
        return cpNumber;
    }

    public void setCpNumber(Integer cpNumber) {
        this.cpNumber = cpNumber;
    }

    public Integer getUsedAmp() {
        return usedAmp;
    }

    public void setUsedAmp(Integer usedAmp) {
        this.usedAmp = usedAmp;
    }

    public Date getPluggedInDate() {
        return pluggedInDate;
    }

    public void setPluggedInDate(Date pluggedInDate) {
        this.pluggedInDate = pluggedInDate;
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean isPlugged) {
        isPlugged = isPlugged;
    }

    void updatedAmpDispatchingTrack(List<UbiChargingPoint> cpAmpDispatchingTrackUpdate) {
        this.cpAmpDispatchingTrack = cpAmpDispatchingTrackUpdate;
        updateCPInfo();
    }

    void updateCPInfo() {
        UbiChargingPoint updatedCP = cpAmpDispatchingTrack.stream().filter(
                chargingPoint -> chargingPoint.getCpNumber() == this.cpNumber
        ).findFirst().get();
        usedAmp = updatedCP.getUsedAmp();
        if (isPlugged == false) {
            isPlugged = true;
            pluggedInDate = new Date();
        }
    }

    public List<UbiChargingPoint> getCpAmpDispatchingTrack() {
        return cpAmpDispatchingTrack;
    }

    @Override
    public String toString() {
        return "UbiChargingPoint{" +
                "cpNumber='" + cpNumber + '\'' +
                ", usedAmp=" + usedAmp +
                ", pluggedInDate=" + pluggedInDate +
                ", isPlugged=" + isPlugged +
                '}';
    }

    public void plugInCar(int usedAmp, Date pluggedInDate, boolean isPlugged) {
        this.usedAmp = usedAmp;

        this.pluggedInDate = pluggedInDate;
        this.isPlugged = isPlugged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UbiChargingPoint)) return false;
        UbiChargingPoint that = (UbiChargingPoint) o;
        return Objects.equals(cpNumber, that.cpNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpNumber);
    }
}

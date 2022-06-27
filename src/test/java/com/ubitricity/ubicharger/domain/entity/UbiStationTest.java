package com.ubitricity.ubicharger.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UbiStationTest {

    private  UbiStation ubiStationTest= new UbiStation();
    private UbiChargingPoint chargingPointTest= new UbiChargingPoint();
    private UbiChargingPoint chargingPointTest1= new UbiChargingPoint();
    private UbiChargingPoint chargingPointTest2= new UbiChargingPoint();

    @BeforeEach
    void setUp() {
        chargingPointTest.setCpNumber(1);
        chargingPointTest1.setCpNumber(2);
        chargingPointTest2.setCpNumber(3);
    }

    @Test
    void plugCar()
    {
        ubiStationTest.plugCar(chargingPointTest);
        assertEquals(true,chargingPointTest.equals(ubiStationTest.getChargingPoints().get(0)) );
    }

    @Test
    void unplugCar()
    {
        ubiStationTest.plugCar(chargingPointTest);
        ubiStationTest.unPlugCharger(chargingPointTest);
        assertEquals(true,ubiStationTest.isEmpty() );

    }

    @Test
    void notifyChargerPointOnCpTrackUpdate()
    {
        ubiStationTest.plugCar(chargingPointTest);
        ubiStationTest.plugCar(chargingPointTest1);
        ubiStationTest.plugCar(chargingPointTest2);


        assertEquals(true,chargingPointTest.getCpAmpDispatchingTrack().size()==3);

    }

}
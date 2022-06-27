package com.ubitricity.ubicharger.service.implementation;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;
import com.ubitricity.ubicharger.domain.entity.UbiStation;
import com.ubitricity.ubicharger.service.UbiChargingPointService;
import com.ubitricity.ubicharger.service.utils.helper.ChargingPointHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UbiChargingPointImplementation implements UbiChargingPointService {
    @Autowired
    ChargingPointHelper chargingPointHelper;
    @Autowired
    UbiStation ubiStation;
    @Override
    public int addCar(Integer cpNumber) {

        if (!chargingPointHelper.isValidCPNumber(cpNumber) ) return HttpStatus.BAD_REQUEST.value();

        UbiChargingPoint cp = new UbiChargingPoint();
        cp.setCpNumber(cpNumber);

        if (ubiStation.isFull()||chargingPointHelper.isChargerPointPresentInList(ubiStation.getChargingPoints(), cp)) return HttpStatus.NOT_ACCEPTABLE.value();

        ubiStation.plugCar(cp);
        return HttpStatus.OK.value();
    }

    @Override
    public int removeCar(Integer cpNumber) {
        if (!chargingPointHelper.isValidCPNumber(cpNumber)) return HttpStatus.BAD_REQUEST.value();

        UbiChargingPoint cp = new UbiChargingPoint();
        cp.setCpNumber(cpNumber);
        if (!chargingPointHelper.isChargerPointPresentInList(ubiStation.getChargingPoints(), cp)) return HttpStatus.NOT_ACCEPTABLE.value();
        ubiStation.unPlugCharger(cp);
        return HttpStatus.OK.value();
    }
}

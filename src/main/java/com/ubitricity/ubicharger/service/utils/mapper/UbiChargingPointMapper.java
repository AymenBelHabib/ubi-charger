package com.ubitricity.ubicharger.service.utils.mapper;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;
import com.ubitricity.ubicharger.domain.enums.CPStatus;
import com.ubitricity.ubicharger.service.dto.UbiChargingPointDTO;
import com.ubitricity.ubicharger.service.utils.helper.ChargingPointHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UbiChargingPointMapper {

    @Autowired
    ChargingPointHelper chargingPointHelper;



    UbiChargingPointDTO ubiChargingPointToDTO(UbiChargingPoint chargingPointNumber)
    {
        UbiChargingPointDTO chargingPointDTO = new UbiChargingPointDTO();

        chargingPointDTO.setCpNumber(chargingPointNumber.getCpNumber());
        chargingPointDTO.setChargingPointRef("CP"+chargingPointNumber.getCpNumber());
        chargingPointDTO.setStatus(
                chargingPointNumber.isPlugged()? CPStatus.OCCUPIED:CPStatus.AVAILABLE);
        chargingPointDTO.setPluggedInDuration(
                chargingPointHelper.dateTimeDifferenceHHmmSS(chargingPointNumber.getPluggedInDate(),new Date()));
        chargingPointDTO.setUsedAmp(chargingPointNumber.getUsedAmp()+"A");
        return chargingPointDTO;
    }

    UbiChargingPointDTO availableUbiChargingPointToDTO(Integer ChargingPointNumber)
    {
     return new UbiChargingPointDTO(ChargingPointNumber);
    }

}

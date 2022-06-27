package com.ubitricity.ubicharger.service.implementation;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;
import com.ubitricity.ubicharger.domain.entity.UbiStation;
import com.ubitricity.ubicharger.service.UbiStationService;
import com.ubitricity.ubicharger.service.dto.UbiStationDTO;
import com.ubitricity.ubicharger.service.utils.helper.ChargingPointHelper;
import com.ubitricity.ubicharger.service.utils.mapper.UbiStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbiStationServiceImplementation implements UbiStationService {

    @Autowired
    UbiStation ubiStation;

    @Autowired
    UbiStationMapper stationMapper;

    UbiStationServiceImplementation() {
        ubiStation = new UbiStation();
    }



    @Override
    public UbiStationDTO getUbiStationReport() {
        return stationMapper.ubiStationToDTO(ubiStation);
    }
}

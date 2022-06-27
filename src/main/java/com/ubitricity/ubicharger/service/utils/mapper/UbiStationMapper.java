package com.ubitricity.ubicharger.service.utils.mapper;

import com.ubitricity.ubicharger.domain.entity.UbiStation;
import com.ubitricity.ubicharger.service.dto.UbiChargingPointDTO;
import com.ubitricity.ubicharger.service.dto.UbiStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UbiStationMapper {

    @Autowired
    UbiChargingPointMapper chargingPointMapper;

    public  UbiStationDTO ubiStationToDTO(UbiStation station)
    {
        List<Integer> listOfCp = IntStream.rangeClosed(1, 10)
                .boxed().collect(Collectors.toList());

        List<UbiChargingPointDTO> report= station.getChargingPoints().stream().map(
                chargingPoint -> {
                    listOfCp.remove(chargingPoint.getCpNumber());
                    return chargingPointMapper.ubiChargingPointToDTO(chargingPoint);
                }
        ).collect(Collectors.toList());

        if(listOfCp.size()>0){
            report.addAll( listOfCp.stream().map(chargingPointNumber -> {
                return chargingPointMapper.availableUbiChargingPointToDTO(chargingPointNumber);
            }).collect(Collectors.toList()));
        }
        UbiStationDTO ubiStationDTO = new UbiStationDTO();
        Collections.sort(report, Comparator.comparingInt(UbiChargingPointDTO::getCpNumber));

        ubiStationDTO.setReportt(report);
        return ubiStationDTO;
    }

}

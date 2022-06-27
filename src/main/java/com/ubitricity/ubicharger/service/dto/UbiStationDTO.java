package com.ubitricity.ubicharger.service.dto;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;

import java.util.List;

public class UbiStationDTO {

    List<UbiChargingPointDTO> Report;


    public List<UbiChargingPointDTO> getReport() {
        return Report;
    }

    public void setReportt(List<UbiChargingPointDTO> Report) {
        this.Report = Report;
    }
}

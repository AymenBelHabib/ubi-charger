package com.ubitricity.ubicharger.service.dto;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;
import com.ubitricity.ubicharger.domain.enums.CPStatus;

public class UbiChargingPointDTO {

    int CpNumber;
    String chargingPointRef;
    CPStatus status;
    String usedAmp;
    String pluggedInDuration;

    public UbiChargingPointDTO() {
    }

    public UbiChargingPointDTO( int CpNumber)
    {
        this.CpNumber =CpNumber;
        this.chargingPointRef="CP"+CpNumber;
        status = CPStatus.AVAILABLE;
        usedAmp = "0A";
        pluggedInDuration="00:00:00";

    }

    public String getChargingPointRef() {
        return chargingPointRef;
    }

    public void setChargingPointRef(String chargingPointRef) {
        this.chargingPointRef = chargingPointRef;
    }

    public CPStatus getStatus() {
        return status;
    }

    public void setStatus(CPStatus status) {
        this.status = status;
    }

    public String getUsedAmp() {
        return usedAmp;
    }

    public void setUsedAmp(String usedAmp) {
        this.usedAmp = usedAmp;
    }

    public int getCpNumber() {
        return CpNumber;
    }

    public void setCpNumber(int cpNumber) {
        CpNumber = cpNumber;
    }

    public String getPluggedInDuration() {
        return pluggedInDuration;
    }

    public void setPluggedInDuration(String pluggedInDuration) {
        this.pluggedInDuration = pluggedInDuration;
    }

    @Override
    public String toString() {
        return "UbiChargingPointDTO{" +
                "chargingPointRef='" + chargingPointRef + '\'' +
                ", status=" + status +
                ", usedAmp='" + usedAmp + '\'' +
                ", pluggedInDuration='" + pluggedInDuration + '\'' +
                '}';
    }
}

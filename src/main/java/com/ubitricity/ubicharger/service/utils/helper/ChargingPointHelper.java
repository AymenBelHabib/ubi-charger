package com.ubitricity.ubicharger.service.utils.helper;

import com.ubitricity.ubicharger.domain.entity.UbiChargingPoint;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChargingPointHelper {

    public ChargingPointHelper() {
    }

    public String dateTimeDifferenceHHmmSS (Date date1, Date date2)
    {
        Long diff= date2.getTime() - date1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);

        return  String.format("%02d:%02d:%02d", diffHours,diffMinutes,diffSeconds);
    }


    public boolean isValidCPNumber( int chargingPointNumber)
    {
        if (chargingPointNumber>0&& chargingPointNumber<11)
        {
            return true;
        }
        return false;
    }

    public boolean isChargerPointPresentInList(List<UbiChargingPoint> chargingPointList, UbiChargingPoint chargingPoint)
    {
        return chargingPointList.stream().
                filter(chargingPoint1 -> chargingPoint1.getCpNumber()==chargingPoint.getCpNumber()
        ).findFirst().isPresent();
    }


}

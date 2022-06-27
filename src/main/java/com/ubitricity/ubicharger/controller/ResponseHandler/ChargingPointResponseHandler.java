package com.ubitricity.ubicharger.controller.ResponseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChargingPointResponseHandler {


    public ResponseEntity<Object> plugCarResponse(int pluggingCarStatus)
    {
        switch (pluggingCarStatus)
        {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the stations contains only 10 Charging point" +
                        "\n please select a point number between {1..10}");
            case 406:
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("the charging point is already occupied");
            case 200:
                return ResponseEntity.status(HttpStatus.OK).body("Car successfully plugged");
            default :
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Object> unplugCarResponse(int unPluggingCarStatus)
    {
        switch (unPluggingCarStatus)
        {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the stations contains only 10 Charging point" +
                        "\n please select a point number between {1..10}");
            case 406:
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("the charging point is already unoccupied");
            case 200:
                return ResponseEntity.status(HttpStatus.OK).body("Car successfully unplugged");
            default :
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

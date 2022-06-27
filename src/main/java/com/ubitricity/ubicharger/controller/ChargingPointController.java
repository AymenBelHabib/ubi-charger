package com.ubitricity.ubicharger.controller;

import com.ubitricity.ubicharger.controller.ResponseHandler.ChargingPointResponseHandler;
import com.ubitricity.ubicharger.service.UbiChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ubi/cp")
@Controller
public class ChargingPointController {
    @Autowired
    UbiChargingPointService ubiChargingPointService;

    @Autowired
    ChargingPointResponseHandler responseHandler;

    @PostMapping("/{ubiRef}")
    public ResponseEntity<Object> plugCar(@PathVariable("ubiRef") Integer ubiCpNumber) {
        return responseHandler.plugCarResponse(ubiChargingPointService.addCar(ubiCpNumber));
    }


    @DeleteMapping("/{ubiRef}")
    public ResponseEntity<Object> unplugCar(@PathVariable("ubiRef") Integer ubiCpNumber) {
        return responseHandler.unplugCarResponse(ubiChargingPointService.removeCar(ubiCpNumber));
    }
}

package com.ubitricity.ubicharger.controller;

import com.ubitricity.ubicharger.service.UbiStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ubi/station")
@Controller
public class UbiController {

    @Autowired
    UbiStationService ubiStationService;

    @GetMapping("")
    public ResponseEntity<Object> getUbiReport() {

        return ResponseEntity.status(HttpStatus.OK).body(ubiStationService.getUbiStationReport());
    }



}

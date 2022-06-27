package com.ubitricity.ubicharger.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChargingPointController.class)
@ContextConfiguration(locations = "classpath*:UbiStationContext.xml")
class ChargingPointControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void plugCarValid() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/ubi/cp/1") ;

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void plugCarNotAccepted() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/ubi/cp/1") ;
        mvc.perform(request);
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    @Test
    void plugCarBadRequest() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/ubi/cp/22") ;

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void unplugCarBadRequest() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.delete("/ubi/cp/22") ;
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void unplugCarNotAccepted() throws Exception {

        RequestBuilder request= MockMvcRequestBuilders.delete("/ubi/cp/1") ;
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    @Test
    void unplugCarValid() throws Exception {
        RequestBuilder addrequest= MockMvcRequestBuilders.post("/ubi/cp/1") ;
        RequestBuilder deleterequest= MockMvcRequestBuilders.delete("/ubi/cp/1") ;
        mvc.perform(addrequest);
        mvc.perform(deleterequest).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
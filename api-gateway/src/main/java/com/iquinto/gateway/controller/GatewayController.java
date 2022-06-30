package com.iquinto.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class GatewayController {
    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    @RequestMapping(value = {"/monitor"}, method = RequestMethod.GET)
    public String monitor() {
        log.info("[m:monitor] GATEWAY SERVICE IS WORKING ");
        return  "GATEWAY SERVICE IS WORKING";
    }

}

package com.iquinto.petservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/test/monitor", consumes = "application/json")
    String checkUserViaFeign();

    @RequestMapping(method = RequestMethod.GET, value = "/auth/check-token/{token}", consumes = "application/json")
    String checkToken(@PathVariable String token);

    @RequestMapping(method = RequestMethod.GET, value = "/test/user", consumes = "application/json")
    String testauth();
}

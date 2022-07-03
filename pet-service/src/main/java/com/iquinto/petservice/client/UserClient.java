package com.iquinto.petservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/test/monitor", consumes = "application/json")
    String checkUserViaFeign();


    @RequestMapping(method = RequestMethod.POST, value = "/auth/testFeignClient", consumes = "application/json")
    String testFeignClient();
}

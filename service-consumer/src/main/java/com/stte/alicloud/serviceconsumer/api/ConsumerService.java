package com.stte.alicloud.serviceconsumer.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider")
public interface ConsumerService {

    @RequestMapping("/getName")
    String getName(@RequestParam("name") String name);
}

package com.stte.alicloud.serviceconsumer.controller;

import com.stte.alicloud.serviceconsumer.api.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/getName")
    public String getName(@RequestParam("name") String name) {
        String nmaes = consumerService.getName(name);
        log.info(".......消费者fegin调用.....配置中心: {}", nmaes);
        return nmaes;
    }

}

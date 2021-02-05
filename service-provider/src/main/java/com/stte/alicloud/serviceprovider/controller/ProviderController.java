package com.stte.alicloud.serviceprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProviderController {

    @GetMapping("/getName")
    public String getNameList(String name) {
        log.info("生产者");
        return name + "生产者";
    }
}

package com.stte.alicloud.serviceconsumer.api;


import com.stte.alicloud.serviceconsumer.fallback.ConsumerServiceFallback;
import com.stte.alicloud.serviceconsumer.fallback.ConsumerServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = ConsumerServiceFallback.class,
        fallbackFactory = ConsumerServiceFallbackFactory.class)
public interface ConsumerService {

    @RequestMapping("/getName")
    String getName(@RequestParam("name") String name);
}

package com.stte.alicloud.serviceconsumer.fallback;

import com.stte.alicloud.serviceconsumer.api.ConsumerService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerServiceFallbackFactory implements FallbackFactory<ConsumerService> {
    @Override
    public ConsumerService create(Throwable cause) {
        return name -> {
            log.info("FallbackFactory 熔断处理 {}", "ConsumerServiceFallbackFactory");
            return "FallbackFactory {由于你的访问次数太多，已为你限流、您已进入保护模式，请稍后再试！}>>>熔断处理函数";
        };
    }
}

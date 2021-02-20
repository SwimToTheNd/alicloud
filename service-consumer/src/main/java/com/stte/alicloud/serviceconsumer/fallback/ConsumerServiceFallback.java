package com.stte.alicloud.serviceconsumer.fallback;

import com.stte.alicloud.serviceconsumer.api.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 注解FeignClient使用的Fallback处理器
 */
@Slf4j
@Component
public class ConsumerServiceFallback implements ConsumerService {


    @Override
    public String getName(String name) {
        log.info("FeignClient 熔断处理 {}", "ConsumerServiceFallback");
        return "FeignClient {由于你的访问次数太多，已为你限流、您已进入保护模式，请稍后再试！}>>>熔断处理函数";
    }
}

package com.stte.alicloud.serviceconsumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.stte.alicloud.serviceconsumer.api.ConsumerService;
import com.stte.alicloud.serviceconsumer.fallback.SentinelExceptionHandler;
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

    /**
     * 在@SentinelResource注解，在exceptionHandler中指定流量控制规则的阻断处理器方法名称
     * @param name
     * @return
     */
    @SentinelResource(value = "getName", blockHandler = "exceptionHandler", fallbackClass = SentinelExceptionHandler.class)
    @GetMapping("/getName")
    public String getName(@RequestParam("name") String name) {
        String nmaes = consumerService.getName(name);
        log.info(".......消费者fegin调用.....配置中心: {}", nmaes);
        return nmaes;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(String name, BlockException ex) {
        // Do some log here.
        log.error("Thread:{}\tparam:{}", Thread.currentThread().getName(), name, ex);
        return String.format("error: getName  is not OK with param:%s", name);
    }

}

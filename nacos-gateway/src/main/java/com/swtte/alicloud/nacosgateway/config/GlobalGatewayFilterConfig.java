package com.swtte.alicloud.nacosgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Gateway全局过滤器配置
 * 实现 GlobalFilter 和 Ordered，重写相关方法，加入到spring容器管理即可，无需配置。
 * 全局过滤器对所有的路由都有效。
 * <li>可以进行权限校验</li>
 */
@Slf4j
@Configuration
public class GlobalGatewayFilterConfig {

    @Bean
    public GlobalFilter aFilter(){
        return new AFilter();
    }

    @Bean
    public GlobalFilter bFilter(){
        return new BFilter();
    }


    public class AFilter implements GlobalFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("AFilter 前置处理...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("AFilter 后置处理...")));
        }

        /**
         * order数值越小，越优先执行
         *
         * @return
         */
        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE + 200;
        }
    }

    public class BFilter implements GlobalFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("BFilter 前置处理逻辑...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("BFilter 后置处理逻辑...")));
        }

        /**
         * order数值越小，越优先执行
         *
         * @return
         */
        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE + 100;
        }
    }
}

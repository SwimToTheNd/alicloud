package com.swtte.alicloud.nacosgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Gateway局部过滤器
 * 1、需要实现GatewayFilter, Ordered接口，实现相关的方法
 * 2、加入到过滤器工厂，并且注册到spring容器中。
 * 3、在配置文件中进行配置，如果不配置则不启用此过滤器规则。
 */
@Slf4j
@Component
public class PartFilterGatewayFilterFactory extends AbstractGatewayFilterFactory {


    @Override
    public GatewayFilter apply(Object config) {
        return new UserIdCheckGatewayFilter();
    }

    /**
     * 局部过滤器
     */
    public class UserIdCheckGatewayFilter implements GatewayFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            String url = exchange.getRequest().getPath().pathWithinApplication().value();
            log.info("请求URL:" + url);
            log.info("method:" + exchange.getRequest().getMethod());
            String host = exchange.getRequest().getHeaders().getFirst("host");
            log.info("host:{}", host);
            //获取param 请求参数
            String username = exchange.getRequest().getQueryParams().getFirst("userName");
            //获取header
            String userId = exchange.getRequest().getQueryParams().getFirst("userId");
            log.info("username:{},userId：{}", username, userId);
            if (StringUtils.isBlank(userId)) {
                log.info("*****头部验证不通过，请在头部输入  user-id");
                //终止请求，直接回应
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("UserIdCheckGatewayFilter 后置处理。。。")));
        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE;
        }
    }
}

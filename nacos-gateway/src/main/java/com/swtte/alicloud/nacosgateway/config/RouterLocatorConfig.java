package com.swtte.alicloud.nacosgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterLocatorConfig {

    @Bean
    public RouteLocator cuRouterLocator(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes().route("forward", r -> r.path("/bd/**").uri("http://www.jd.com")).build();
    }
}

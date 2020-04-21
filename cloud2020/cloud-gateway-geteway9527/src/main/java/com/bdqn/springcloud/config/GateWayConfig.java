package com.bdqn.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-20 12:53
 **/
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator comsumerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_routes_bdqn",
                r -> r.path("/guonei")
                            .uri("http://news.baidu.com/guonei")).build();
        routes.route("path_routes_bdqn2",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}

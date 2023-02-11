package com.order.ordergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/order/*")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8100/"))
				.route(p -> p
						.path("/payment/*")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8200/"))
				.route(p -> p
						.path("/inventory/*")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8300/"))
				.build();
	}
}

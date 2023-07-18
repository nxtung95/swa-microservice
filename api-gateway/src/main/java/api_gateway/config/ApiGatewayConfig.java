package api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("AVATAR-SERVICE", r -> r.path("/avatar-service/**")
						.uri("lb://AVATAR-SERVICE"))
				.route("ELEMENT-SERVICE", r -> r.path("/element-service/**")
						.uri("lb://ELEMENT-SERVICE"))
				.route("REWARD-SERVICE", r -> r.path("/reward-service/**")
						.uri("lb://REWARD-SERVICE"))
				.route("SCHOOL-SERVICE", r -> r.path("/school-service/**")
						.uri("lb://SCHOOL-SERVICE"))
				.route("STUDENT-SERVICE", r -> r.path("/student-service/**")
						.uri("lb://STUDENT-SERVICE"))
				.route("TEACHER-SERVICE", r -> r.path("/teacher-service/**")
						.uri("lb://TEACHER-SERVICE"))
				.route("USER-SERVICE", r -> r.path("/user-service/**")
						.uri("lb://USER-SERVICE"))
				.build();
	}
}

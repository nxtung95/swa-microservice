package authen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"authen"})
@EnableEurekaClient
public class AuthenServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthenServiceApplication.class, args);
	}
}

package avatar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"avatar"})
@EnableEurekaClient
public class AvatarServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AvatarServiceApplication.class, args);
	}
}

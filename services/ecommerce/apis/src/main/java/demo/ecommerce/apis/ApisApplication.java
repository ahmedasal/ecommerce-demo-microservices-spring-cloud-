package demo.ecommerce.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("demo.ecommerce.*")
@EnableJpaRepositories(basePackages = "demo.ecommerce.repository")
@EntityScan(basePackages ="demo.ecommerce.model")
@EnableEurekaClient
@EnableCaching
public class ApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApisApplication.class, args);
	}

}

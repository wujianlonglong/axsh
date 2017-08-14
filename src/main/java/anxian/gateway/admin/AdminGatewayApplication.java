package anxian.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("client.api")
@EntityScan(basePackageClasses = {AdminGatewayApplication.class, Jsr310JpaConverters.class})
@MultipartConfig
public class AdminGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminGatewayApplication.class, args);
    }
}

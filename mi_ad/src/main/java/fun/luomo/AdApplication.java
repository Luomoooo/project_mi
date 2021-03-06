package fun.luomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@SpringBootApplication
@EnableEurekaClient
public class AdApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

}

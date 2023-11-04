package danekerscode.api;

import danekerscode.api.domain.service.AESService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner r
            (AESService aesService){
        return args -> {
            System.out.println(aesService.decrypt("HNJmrPhK1LZq0GRSbF/m8V9uqIPCiljha56U4TLKHxQ="));
        };
    }

}

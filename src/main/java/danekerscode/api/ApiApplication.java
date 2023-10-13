package danekerscode.api;

import danekerscode.api.payload.request.MailSendRequest;
import danekerscode.api.service.MailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(
            MailService mailService
    ){
        return args -> {
            mailService.send(
                    new MailSendRequest(
                            "kkraken2005@gmail.com",
                            "www.google.com"
                    )
            );
        };
    }
}

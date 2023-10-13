package danekerscode.api;

import com.ea.async.Async;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    static {
        Async.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}

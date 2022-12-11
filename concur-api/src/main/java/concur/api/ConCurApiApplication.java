package concur.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"concur.core", "concur.api"})
public class ConCurApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConCurApiApplication.class, args);
    }
}

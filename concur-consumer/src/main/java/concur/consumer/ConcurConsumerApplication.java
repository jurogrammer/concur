package concur.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"concur.core", "concur.consumer"})
public class ConcurConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcurConsumerApplication.class, args);
    }
}

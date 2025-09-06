package org.example.lokalebotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LokaleBotApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LokaleBotApiApplication.class, args);
    }

}

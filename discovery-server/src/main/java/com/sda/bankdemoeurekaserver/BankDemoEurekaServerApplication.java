package com.sda.bankdemoeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BankDemoEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankDemoEurekaServerApplication.class, args);
    }

}

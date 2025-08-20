package com.group.server_register_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerRegisterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerRegisterServiceApplication.class, args);
	}

}

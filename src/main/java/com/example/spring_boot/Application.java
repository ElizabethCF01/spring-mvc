package com.example.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.spring_boot.client")
@EnableScheduling // 🔥 Habilita las tareas programadas
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

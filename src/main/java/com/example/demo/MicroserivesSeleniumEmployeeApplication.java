package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient // not mandatory with Eureka
@EnableFeignClients //Mandatory for spring to create beans of FeignClient
public class MicroserivesSeleniumEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserivesSeleniumEmployeeApplication.class, args);
	}

}

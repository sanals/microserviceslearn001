package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

    @Value("${address.service.base.url}")
    String addressServiceBaseUrl;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}


    @Bean
    WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(addressServiceBaseUrl)
                .build();
    }
	
}

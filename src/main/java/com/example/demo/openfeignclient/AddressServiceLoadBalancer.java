package com.example.demo.openfeignclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(name = "microserives-selenium-address", configuration = CustomLoadBalancerConfig.class) //give same name as in properties file "spring.application.name" or same as one registered in eureka
public class AddressServiceLoadBalancer {

	@LoadBalanced
	@Bean
	public Feign.Builder feignBuilder(){
		return Feign.builder();
	}
}

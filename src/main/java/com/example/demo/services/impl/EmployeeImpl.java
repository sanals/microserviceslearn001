package com.example.demo.services.impl;

import com.example.demo.view.AddressView;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.openfeignclient.AddressClient;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.services.EmployeeService;
import com.example.demo.view.EmployeeView;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate; //RestTemplate no longer needs @Autowired since it is added in constructor

	@Value("${address.service.base.url}") //moved to EmployeeImpl constructor
	private String addressServiceBaseUrl;

	@Autowired
	private WebClient webClient;
	
	@Autowired
	AddressClient feignAddressClient; 
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;

//	public EmployeeImpl(@Value("${address.service.base.url}") String addressServiceBaseUrl, RestTemplateBuilder restTemplateBuilder) {
//		this.restTemplate = restTemplateBuilder
//				.rootUri(addressServiceBaseUrl)
//				.build();
//	}

	@Override
	public EmployeeView getEmployeeDetails(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		return new EmployeeView(employee, fetchAddressWithFeignClientForEmployee(id));
	}
	
	@Override
	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		EmployeeView employeeView = modelMapper.map(employee, EmployeeView.class);
		employeeView.setAddress(fetchAddressWithFeignClientForEmployee(id));
		return employeeView;
	}

	@Override
	public AddressView fetchAddressWithRestTemplateForEmployee(Integer id) throws Exception {
		// If using DiscoveryClient use this step
//		List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERIVES-SELENIUM-ADDRESS");
//		ServiceInstance serviceInstance = instances.get(0);
		
		// If using LoadBalancerClient use this step
//		ServiceInstance serviceInstance = loadBalancerClient.choose("MICROSERIVES-SELENIUM-ADDRESS");
//		String addressUri = serviceInstance.getUri().toString();
//		String configPath = serviceInstance.getMetadata().get("configPath");
//		String someExample = serviceInstance.getMetadata().get("someExample");
//		System.out.println(addressUri+" addressUri ::::");
//		System.out.println(someExample+" someExample ::::");
//		return restTemplate.getForObject(addressUri+configPath+"/address/{id}", AddressView.class, id);
		
		// Load Balancing can also be done using RestTemplate, by adding @LoadBalanced annotation to RestTemplate Bean.
		// Now we can give the URL as the value of 'spring.application.name' which can be seen in the eureka as a client, plus the 'context-path'.
		return restTemplate.getForObject("http://microserives-selenium-address/address-app/api/address/{id}", AddressView.class, id);
	}

	@Override
	public AddressView fetchAddressWithWebClientForEmployee(Integer id) throws Exception {
		return webClient
				.get()
				.uri("/address/{id}",id)
				.retrieve()
				.bodyToMono(AddressView.class)
				.block();
	}

	@Override
	public AddressView fetchAddressWithFeignClientForEmployee(Integer id) throws Exception {
		System.out.println("in fetchAddressWithFeignClientForEmployee");
		ResponseEntity<AddressView> entity = feignAddressClient.getEmployeeByEmployeeId(id);
		return entity.getBody();
	}

}

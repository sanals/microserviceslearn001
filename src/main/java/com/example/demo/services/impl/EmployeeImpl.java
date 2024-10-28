package com.example.demo.services.impl;

import com.example.demo.view.AddressView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.services.EmployeeService;
import com.example.demo.view.EmployeeView;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;

//	@Autowired
	private final RestTemplate restTemplate;

//	@Value("${address.service.base.url}") //moved to EmployeeImpl constructor
//	private String addressServiceBaseUrl;

	public EmployeeImpl(@Value("${address.service.base.url}") String addressServiceBaseUrl, RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.rootUri(addressServiceBaseUrl)
				.build();
	}

	@Override
	public EmployeeView getEmployeeDetails(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		return new EmployeeView(employee, fetchAddressWithRestTemplateForEmployee(id));
	}
	
	@Override
	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		EmployeeView employeeView = modelMapper.map(employee, EmployeeView.class);
		employeeView.setAddress(fetchAddressWithRestTemplateForEmployee(id));
		return employeeView;
	}

	@Override
	public AddressView fetchAddressWithRestTemplateForEmployee(Integer id) throws Exception {
		return restTemplate.getForObject("/address/{id}", AddressView.class, id);
	}

}

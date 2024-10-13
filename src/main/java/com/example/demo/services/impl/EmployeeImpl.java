package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.services.EmployeeService;
import com.example.demo.view.EmployeeView;

@Service
public class EmployeeImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public EmployeeView getEmployeeDetails(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		return new EmployeeView(employee);
	}

}

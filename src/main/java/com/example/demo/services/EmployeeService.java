package com.example.demo.services;

import com.example.demo.view.EmployeeView;

public interface EmployeeService {
	public EmployeeView getEmployeeDetails(Integer id) throws Exception;

	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception;
}

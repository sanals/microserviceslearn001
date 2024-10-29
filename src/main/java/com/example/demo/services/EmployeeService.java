package com.example.demo.services;

import com.example.demo.view.AddressView;
import com.example.demo.view.EmployeeView;

public interface EmployeeService {
	public EmployeeView getEmployeeDetails(Integer id) throws Exception;

	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception;

	public AddressView fetchAddressWithRestTemplateForEmployee(Integer id) throws Exception;

	public AddressView fetchAddressWithWebClientForEmployee(Integer id) throws Exception;
}

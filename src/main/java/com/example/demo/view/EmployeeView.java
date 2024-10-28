package com.example.demo.view;

import com.example.demo.entity.Employee;

public class EmployeeView {
	private int id;
	private String name;
	private String email;
	private String bloodGroup;
	private AddressView address;

	public EmployeeView(Employee employee, AddressView address) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.email = employee.getEmail();
		this.bloodGroup = employee.getBloodGroup();
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public AddressView getAddress() {
		return address;
	}

	public void setAddress(AddressView address) {
		this.address = address;
	}
}

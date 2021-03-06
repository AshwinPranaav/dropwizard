package com.ashwin.EmployeesDropwizard.model;

import javax.validation.constraints.NotEmpty;

public class Employee {
	private int id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String code;
	
	public Employee() {}
	
	public Employee(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

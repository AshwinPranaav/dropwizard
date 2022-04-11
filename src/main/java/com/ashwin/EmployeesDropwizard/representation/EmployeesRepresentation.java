package com.ashwin.EmployeesDropwizard.representation;

import org.hibernate.validator.constraints.Length;

public class EmployeesRepresentation<T> {
	private long code;
	
	@Length(max=3)
	private T data;
	
	public EmployeesRepresentation() {}
	
	public EmployeesRepresentation(long code, T data) {
		this.code = code;
		this.data = data;
	}

	public long getCode() {
		return code;
	}

	public T getData() {
		return data;
	}
}

package com.ashwin.EmployeesDropwizard.service;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.ashwin.EmployeesDropwizard.dao.EmployeesDao;
import com.ashwin.EmployeesDropwizard.model.Employee;

public abstract class EmployeesService {
	private static final String EMPLOYEE_NOT_FOUND = "Employee id %s not found.";
	private static final String DATABASE_REACH_ERROR =
			"Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
	private static final String DATABASE_CONNECTION_ERROR =
			"Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
	private static final String DATABASE_UNEXPECTED_ERROR =
			"Unexpected error occurred while attempting to reach the database. Details: ";
	private static final String SUCCESS = "Success...";
	private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting employee.";
	
	@CreateSqlObject
	abstract EmployeesDao employeesDao();
	
	public List<Employee> getEmployees() {
		List<Employee> result = employeesDao().getEmployees();
		System.out.println(result);
		return result;
	}
	
	public Employee getEmployee(int id) {
		Employee employee = employeesDao().getEmployee(id);
		if(Objects.isNull(employee)) {
			throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, id), Status.NOT_FOUND);
		}
		return employee;
	}
	
	public Employee createEmployee(Employee employee) {
		employeesDao().createEmployee(employee);
		return employeesDao().getEmployee(employeesDao().lastInsertId());
	}
	
	public Employee editEmployee(Employee employee) {
	    if (Objects.isNull(employeesDao().getEmployee(employee.getId()))) {
	      throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, employee.getId()), Status.NOT_FOUND);
	    }
	    employeesDao().editEmployee(employee);
	    return employeesDao().getEmployee(employee.getId());
	}
	
	public String deleteEmployee(final int id) {
		int result = employeesDao().deleteEmployee(id);
		switch(result) {
		case 1:
			return SUCCESS;
		case 0:
			throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, id), Status.NOT_FOUND);
		default:
			throw new WebApplicationException(UNEXPECTED_ERROR, Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	public String performHealthCheck() {
		try {
			employeesDao().getEmployees();
		}
		catch (Exception e) {
			return e.getCause().getLocalizedMessage();
		}
		return null;
	}
}

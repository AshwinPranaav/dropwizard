package com.ashwin.EmployeesDropwizard.health;

import com.codahale.metrics.health.HealthCheck;
import com.ashwin.EmployeesDropwizard.service.EmployeesService;

public class EmployeesHealthCheck extends HealthCheck{
	private final EmployeesService employeesService;
	
	public EmployeesHealthCheck(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}
	
	@Override
	public Result check() throws Exception {
		String mySqlHealthStatus = employeesService.performHealthCheck();
		
		if(mySqlHealthStatus == null) {
			return Result.healthy("Service is healthy");
		}
		else {
			return Result.healthy("Service is unhealthy " + mySqlHealthStatus);
		}
	}
}

package com.ashwin.EmployeesDropwizard.auth;

import java.util.Objects;

import io.dropwizard.auth.Authorizer;

public class EmployeesAuthorizer implements Authorizer<User> {
	@Override
	public boolean authorize(User principal, String role) {
		if(Objects.nonNull(principal)) {
			return true;
		}
		return false;
	}
}

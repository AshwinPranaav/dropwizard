package com.ashwin.EmployeesDropwizard.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class EmployeesAuthenticator implements Authenticator<String, User> {
	@Override
	public Optional<User> authenticate(String token) throws AuthenticationException {
		if("test_token".equals(token)) {
			return Optional.of(new User());
		}
		return Optional.empty();
	}
}

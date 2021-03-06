package com.ashwin.EmployeesDropwizard.auth;

import java.security.Principal;

public class User implements Principal {
	private int id;
	private String username;
	private String password;
	private String badge;
	
	public String getBadge() {
		badge = id < 5 ? "blue" : "green";
		return badge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getName() {
		return username;
	}
}

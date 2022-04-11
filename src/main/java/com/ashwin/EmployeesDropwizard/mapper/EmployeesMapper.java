package com.ashwin.EmployeesDropwizard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.ashwin.EmployeesDropwizard.model.Employee;

public class EmployeesMapper implements ResultSetMapper<Employee>{
	private final String ID = "id";
	private final String NAME = "name";
	private final String CODE = "code";
	
	public Employee map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException{
		return new Employee(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(CODE));
	}
}

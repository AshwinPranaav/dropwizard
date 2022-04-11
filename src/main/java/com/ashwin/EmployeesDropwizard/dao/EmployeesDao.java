package com.ashwin.EmployeesDropwizard.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.ashwin.EmployeesDropwizard.mapper.EmployeesMapper;
import com.ashwin.EmployeesDropwizard.model.Employee;

@RegisterMapper(EmployeesMapper.class)
public interface EmployeesDao {
	
	@SqlQuery("select * from employees")
	public List<Employee> getEmployees();
	
	@SqlQuery("select * from employees where id = :id")
	public Employee getEmployee(@Bind("id") final int id);
	
	@SqlUpdate("insert into employees(name, code) values(:name, :code)")
	public void createEmployee(@BindBean final Employee employee);
	
	@SqlUpdate("update employees set name = coalesce(:name, name), code = coalesce(:code, code) where id = :id")
	public void editEmployee(@BindBean final Employee employee);
	
	@SqlUpdate("delete from employees where id = :id")
	public int deleteEmployee(@Bind("id") final int id);
	
	@SqlQuery("select last_insert_id()")
	public int lastInsertId();
}

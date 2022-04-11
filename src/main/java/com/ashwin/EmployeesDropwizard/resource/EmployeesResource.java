package com.ashwin.EmployeesDropwizard.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Timed;
import com.ashwin.EmployeesDropwizard.model.Employee;
import com.ashwin.EmployeesDropwizard.representation.EmployeesRepresentation;
import com.ashwin.EmployeesDropwizard.service.EmployeesService;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class EmployeesResource {
	private final EmployeesService employeesService;
	
	public EmployeesResource(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}
	
	@GET
	@Timed
	public EmployeesRepresentation<List<Employee>> getEmployees() {
		return new EmployeesRepresentation(HttpStatus.OK_200, employeesService.getEmployees());
	}
	
	@GET
	@Timed
	@Path("{id}")
	public EmployeesRepresentation<Employee> getEmployee(@PathParam("id") final int id) {
		return new EmployeesRepresentation(HttpStatus.OK_200, employeesService.getEmployee(id));
	}
	
	@POST
	@Timed
	public EmployeesRepresentation<Employee> createEmployee(@NotNull @Valid final Employee employee) {
		return new EmployeesRepresentation(HttpStatus.OK_200, employeesService.createEmployee(employee));
	}
	
	@PUT
	  @Timed
	  @Path("{id}")
	  public EmployeesRepresentation<Employee> editEmployee(@NotNull @Valid final Employee employee, @PathParam("id") final int id) {
	    employee.setId(id);
	    return new EmployeesRepresentation<Employee>(HttpStatus.OK_200, employeesService.editEmployee(employee));
	  }

	  @DELETE
	  @Timed
	  @Path("{id}")
	  public EmployeesRepresentation<String> deleteEmployee(@PathParam("id") final int id) {
	    return new EmployeesRepresentation<String>(HttpStatus.OK_200, employeesService.deleteEmployee(id));
	  }
}

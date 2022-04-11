package com.ashwin.EmployeesDropwizard;

import javax.sql.DataSource;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import com.ashwin.EmployeesDropwizard.auth.EmployeesAuthenticator;
import com.ashwin.EmployeesDropwizard.auth.EmployeesAuthorizer;
import com.ashwin.EmployeesDropwizard.auth.User;
import com.ashwin.EmployeesDropwizard.health.EmployeesHealthCheck;
import com.ashwin.EmployeesDropwizard.resource.EmployeesResource;
import com.ashwin.EmployeesDropwizard.service.EmployeesService;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EmployeesDropwizardApplication extends Application<EmployeesDropwizardConfiguration> {

    private static final String SQL = "sql";
    private static final String DROPWIZARD_EMPLOYEE_SERVICE = "Dropwizard employee service";
    private static final String BEARER = "bearer";
    
	public static void main(final String[] args) throws Exception {
//        new EmployeesDropwizardApplication().run(args);
		new EmployeesDropwizardApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "EmployeesDropwizard";
    }
    
    @Override
    public void initialize(final Bootstrap<EmployeesDropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(EmployeesDropwizardConfiguration configuration,
                    Environment environment) {
        // TODO: implement application
    	// dataSource configuration
    	final DataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), SQL);
    	DBI dbi = new DBI(dataSource);
    	
    	// register health checks
    	EmployeesHealthCheck healthCheck = new EmployeesHealthCheck(dbi.onDemand(EmployeesService.class));
    	environment.healthChecks().register(DROPWIZARD_EMPLOYEE_SERVICE, healthCheck);
    	
    	// registerOAuth authentication
    	environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
    			.setAuthenticator(new EmployeesAuthenticator())
    			.setAuthorizer(new EmployeesAuthorizer()).setPrefix(BEARER).buildAuthFilter()));
    	environment.jersey().register(RolesAllowedDynamicFeature.class);
    	environment.jersey().register(new EmployeesResource(dbi.onDemand(EmployeesService.class)));
    	System.out.println("Application ran");
    }
}

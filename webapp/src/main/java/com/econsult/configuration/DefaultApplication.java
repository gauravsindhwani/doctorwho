package com.econsult.configuration;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


public class DefaultApplication extends ResourceConfig {
	
	public DefaultApplication(){
		 register(RequestContextFilter.class);
		 register(DefaultObjectMapperProvider.class);
		 register(JacksonFeature.class);
		 packages("com.econsult.service");
	}
	
}

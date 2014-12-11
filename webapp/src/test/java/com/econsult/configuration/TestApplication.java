package com.econsult.configuration;

public class TestApplication extends DefaultApplication {
	public TestApplication(){
		super();
		property("contextConfigLocation", "classpath:webapp-context.xml");
	}
}

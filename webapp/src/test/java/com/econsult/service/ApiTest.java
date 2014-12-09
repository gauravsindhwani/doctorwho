package com.econsult.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTestNg;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.econsult.configuration.TestApplication;
import com.econsult.model.ServicePlan;

public class ApiTest extends JerseyTestNg.ContainerPerClassTest{
	
	@BeforeTest
	public void setup(){
		System.setProperty("spring.profiles.active", "test");
	}

	@Path("root")
	public static class TestResource {
		@GET
		public String get() {
			return "GET";
		}
	}

	@Override
	protected TestContainerFactory getTestContainerFactory()
			throws TestContainerException {
		return new InMemoryTestContainerFactory();
	}

	@Override
	protected Application configure() {
		return new TestApplication();
	}

	

	@Test
	public void testGet() {
		WebTarget t = target("econsult/admin/plan/BASIC");

		ServicePlan s = t.request().get(ServicePlan.class);
		System.out.println(s.getId());
		Assert.assertEquals("GET", s);
	}

}

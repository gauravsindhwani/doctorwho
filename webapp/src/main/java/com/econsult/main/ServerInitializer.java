package com.econsult.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;

public final class ServerInitializer {

	public static void main(String[] args) {
		try {
			ServletHolder sh = new ServletHolder(ServletContainer.class);
	//		sh.setInitParameter("com.sun.jersey.config.property.packages", "com.econsult, com.fasterxml.jackson.jaxrs.json");
			sh.setInitParameter("javax.ws.rs.Application", "com.econsult.configuration.DefaultApplication");
			Server server = new Server(8080);
			ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
			context.addEventListener(new ContextLoaderListener());
			context.setInitParameter("contextConfigLocation", "classpath:webapp-context.xml");	
			context.addServlet(sh, "/*");
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

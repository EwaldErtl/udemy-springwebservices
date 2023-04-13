package com.in28minutes.soap.webservices.coursemanagement;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) { 
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://in28minutes.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(courseSchema);
		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

	@Bean
	public XwsSecurityInterceptor securityInterveptor() { 
		XwsSecurityInterceptor securityInterveptor = new XwsSecurityInterceptor();
	}

}
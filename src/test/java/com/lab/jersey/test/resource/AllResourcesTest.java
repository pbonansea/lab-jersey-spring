package com.lab.jersey.test.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import com.lab.jersey.util.ApplicationConstant;

//@RunWith(Suite.class)
//@SuiteClasses({
//	UserResourceTest.class,
//	CityResourceTest.class,
//	CompanyResourceTest.class,
//})
@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*ResourceTest.class")
public class AllResourcesTest {

	private static Client client;
	private static WebTarget service;

	private static final String HOST = "localhost";
	private static String PORT = "8080";
	private static String DOMAIN = "lab-jersey-spring";
		
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		String runtimeEnvironment = System.getProperty(
				ApplicationConstant.ENVIRONMET_PARAM);
		if (runtimeEnvironment != null) {	
			ApplicationConstant.Runtime.ENVIRONMENT
			.setValue(runtimeEnvironment);		
		}
		
		client = ClientBuilder.newClient();
		service = client.target("http://" + HOST + ":" + PORT).path(DOMAIN);
	
	}
	
	public static WebTarget getWebTargetServiceInstance() {

		if (service == null) {
			if (client == null) {
				client = ClientBuilder.newClient();				
			}
			service = client.target("http://" + HOST + ":" + PORT).path(DOMAIN);			
		}
		
		return service;
		
	}
	
}

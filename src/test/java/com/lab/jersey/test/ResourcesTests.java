package com.lab.jersey.test;

import javax.ws.rs.client.ClientBuilder;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserResourceTest.class,
	CityResourceTest.class,
	CompanyResourceTest.class,
})
public class ResourcesTests extends BaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		client = ClientBuilder.newClient();
		service = client.target("http://" + HOST + ":" + PORT).path(DOMAIN);
	
	}
	
}

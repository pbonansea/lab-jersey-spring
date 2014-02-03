/**
 * 
 */
package com.lab.jersey.test.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author paolobonansea
 * 
 */
public class UserResourceTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception { }

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception { }

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception { }
	
	@Test
	public void testCreate() {
		
		String userJsonObject = "{\"lastName\":\"last name jersey spring\",\"name\":\"test name 75\",\"cityId\":\"2\", \"email\":\"test@test.com\", \"login\":\"test\"}";
		
		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user").path("create")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userJsonObject, 
						MediaType.APPLICATION_JSON), Response.class);
				
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
				
	}
	
	@Test
	public void testUpdate() {

		String userJsonObject = "{ \"id\": \"4\", \"lastName\":\"last name update\",\"name\":\"test name 1\",\"cityId\":\"1\"}";

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user").path("update")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userJsonObject, 
						MediaType.APPLICATION_JSON), Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testDelete() {

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user").path("delete/" + 15)
				.request()
				.post (Entity.text("payload"), Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetById() {

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user/" + 2)
				.request()
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetAll() {

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user")
				.path("all")
				.request()
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_USERNAME, "jersey")
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_PASSWORD, "123")
				.get(Response.class);
		
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
 	
	@Test
	public void testGetByCityId() {

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user")
				.path("city/" + 1)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetByCityIdCompanyId() {

		Response response = AllResourcesTest.getWebTargetServiceInstance().path("user")
				.path("city/" + 1)
				.path("company/" + 1)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}

}
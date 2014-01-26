/**
 * 
 */
package com.lab.jersey.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author paolobonansea
 * 
 */
public class UserResourceTest extends BaseTest {
	
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
		
		Response response = service.path("user").path("create")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userJsonObject, 
						MediaType.APPLICATION_JSON), Response.class);
				
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
				
	}
	
	@Test
	public void testUpdate() {

		String userJsonObject = "{ \"id\": \"8\", \"lastName\":\"last name update\",\"name\":\"test name 1\",\"cityId\":\"1\"}";

		Response response = service.path("user").path("update")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userJsonObject, 
						MediaType.APPLICATION_JSON), Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testDelete() {

		Response response = service.path("user").path("delete/" + 12)
				.request()
				.post (Entity.text("payload"), Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetById() {

		Response response = service.path("user/" + 2)
				.request()
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetAll() {

		Response response = service.path("user")
				.path("all")
				.request()
				.get(Response.class);
		
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
 	
	@Test
	public void testGetByCityId() {

		Response response = service.path("user")
				.path("city/" + 1)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}
	
	@Test
	public void testGetByCityIdCompanyId() {

		Response response = service.path("user")
				.path("city/" + 1)
				.path("company/" + 1)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}

}
/**
 * 
 */
package com.lab.jersey.test;

import static org.junit.Assert.assertEquals;

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
public class CityResourceTest extends BaseTest {

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
	public void testGetAll() {

		Response response = service.path("city")
				.path("all")
				.request().get(Response.class);

		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

	}

}

/**
 * 
 */
package com.lab.jersey.test.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lab.jersey.model.City;
import com.lab.jersey.service.CityService;

/**
 * @author paolobonansea
 *
 */
public class CityServiceTest extends AbstractTestService {

	@Autowired
	private CityService cityService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAll() throws Exception {
		
		List<City> cities = cityService.getAll();
		
		assert(!cities.isEmpty());
		
	}

}

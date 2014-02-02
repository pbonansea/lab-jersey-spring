/**
 * 
 */
package com.lab.jersey.test.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.User;
import com.lab.jersey.service.UserService;

/**
 * @author paolobonansea
 *
 */
public class UserServiceTest extends AbstractTestService {
	
	@Autowired
	private UserService userService;
	
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
	public void createUserTest() throws Exception {

		User user = new User();
		user.setName("test unit");
		userService.create(user);
			
		assert(user.getId() != null);
		
	}

}
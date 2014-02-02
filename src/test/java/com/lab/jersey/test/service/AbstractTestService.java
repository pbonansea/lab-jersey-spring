package com.lab.jersey.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author paolobonansea
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resource/com/lab/jersey/test/config/applicationContext.xml")
public abstract class AbstractTestService {
	
}

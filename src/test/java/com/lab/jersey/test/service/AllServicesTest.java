/**
 * 
 */
package com.lab.jersey.test.service;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import com.lab.jersey.util.ApplicationConstant;

/**
 * @author paolobonansea
 * 
 */
@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*ServiceTest.class")
public class AllServicesTest {	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		String runtimeEnvironment = System
				.getProperty(ApplicationConstant.ENVIRONMET_PARAM);
		if (runtimeEnvironment != null) {
			ApplicationConstant.Runtime.ENVIRONMENT
					.setValue(runtimeEnvironment);
		}

	}

}

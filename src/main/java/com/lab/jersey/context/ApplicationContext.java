/**
 * 
 */
package com.lab.jersey.context;

import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.lab.jersey.util.ApplicationConstant;


/**
 * 
 * @author paolobonansea
 * 
 */
public class ApplicationContext extends ResourceConfig {
	
	private static Logger LOG = Logger.getLogger(ApplicationContext.class);

	public ApplicationContext() {
		
        register(JacksonFeature.class);
		String runtimeEnvironment = System.getProperty(
				ApplicationConstant.ENVIRONMET_PARAM);
		if (runtimeEnvironment != null) {	
			ApplicationConstant.Runtime.ENVIRONMENT.setValue(runtimeEnvironment);		
		}
        LOG.info("application initialized - environment " + ApplicationConstant.Runtime.ENVIRONMENT.getValue());
        
    }	
	
}

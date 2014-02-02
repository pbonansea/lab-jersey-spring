/**
 * 
 */
package com.lab.jersey.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.lab.jersey.hibernate.HibernateUtil;

/**
 * @author paolobonansea
 *
 */
@Provider
public class HibernateResponseFilter implements ContainerResponseFilter {
	
	private static Logger LOG = Logger.getLogger(HibernateResponseFilter.class);
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		
		try {

			HibernateUtil.closeSession();
			
		} catch (HibernateException ex) {
			LOG.error("hibernate cannot close the session", ex);
		}		
	}

}

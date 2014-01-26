/**
 * 
 */
package com.lab.jersey.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * @author paolobonansea
 *
 */
public abstract class BaseTest {

	protected static Client client;
	protected static WebTarget service;

	protected static final String HOST = "localhost";
	protected static String PORT = "8080";
	protected static String DOMAIN = "lab-jersey-spring";
	
}

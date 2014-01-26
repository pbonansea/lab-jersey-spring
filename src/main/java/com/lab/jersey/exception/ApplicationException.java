/**
 * 
 */
package com.lab.jersey.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author paolobonansea
 * 
 */
public class ApplicationException extends WebApplicationException {

	public ApplicationException() {
		super(Response.serverError().build());
	}

	public ApplicationException(String message) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(message).type("text/plain").build());
	}
}

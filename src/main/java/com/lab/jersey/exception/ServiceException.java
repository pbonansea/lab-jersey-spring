/**
 * 
 */
package com.lab.jersey.exception;

/**
 * @author paolobonansea
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}

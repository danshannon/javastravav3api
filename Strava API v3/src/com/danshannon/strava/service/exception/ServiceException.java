package com.danshannon.strava.service.exception;

/**
 * @author DShannon
 * 
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 * @param e
	 */
	public ServiceException(String string, Exception e) {
		super(string, e);
	}

	/**
	 * @param string
	 */
	public ServiceException(String string) {
		super(string);
	}

}

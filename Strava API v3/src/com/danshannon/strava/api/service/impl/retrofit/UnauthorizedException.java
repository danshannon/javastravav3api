package com.danshannon.strava.api.service.impl.retrofit;


/**
 * @author dshannon
 *
 */
public class UnauthorizedException extends Throwable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param cause
	 */
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

}

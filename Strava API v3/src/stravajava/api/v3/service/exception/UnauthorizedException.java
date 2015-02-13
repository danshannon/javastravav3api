package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

/**
 * @author dshannon
 *
 */
public class UnauthorizedException extends RuntimeException implements StravaAPIException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StravaResponse	response;

	/**
	 * @param cause
	 */
	public UnauthorizedException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + (response == null ? "" : response.toString()),cause);
		this.response = response;
	}
	
	public UnauthorizedException(final String reason) {
		super(reason);
	}

	@Override
	public StravaResponse getResponse() {
		return this.response;
	}

	@Override
	public void setResponse(final StravaResponse response) {
		this.response = response;
		
	}

}

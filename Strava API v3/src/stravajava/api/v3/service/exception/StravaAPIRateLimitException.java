package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

public class StravaAPIRateLimitException extends Throwable implements StravaAPIException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private StravaResponse	response;
	
	public StravaAPIRateLimitException(final String status, final StravaResponse response, final Throwable cause) {
		super(status + " : " + response.toString(),cause);
		setResponse(response);
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
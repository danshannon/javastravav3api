package stravajava.api.v3.service.exception;

import stravajava.api.v3.model.StravaResponse;

public interface StravaAPIException {
	/**
	 * @return the response
	 */
	public StravaResponse getResponse();
	
	/**
	 * @param response the response to set
	 */
	public void setResponse(final StravaResponse response);
}

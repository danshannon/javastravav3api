package javastrava.api.v3.model;

import java.util.List;

/**
 * <p>
 * Representation of the response received from Strava in error situations (most commonly when resources are not found or there is
 * an authorisation issue)
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaResponse {
	/**
	 * Text message describing the overall error
	 */
	private String					message;
	/**
	 * List of error details
	 */
	private List<StravaAPIError>	errors;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
	/**
	 * @return the errors
	 */
	public List<StravaAPIError> getErrors() {
		return this.errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(final List<StravaAPIError> errors) {
		this.errors = errors;
	}
}

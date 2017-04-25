package javastrava.model;

import java.util.List;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Representation of the response received from Strava in error situations (most commonly when resources are not found or there is an authorisation issue)
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaResponse implements StravaEntity {
	/**
	 * Text message describing the overall error
	 */
	private String					message;
	/**
	 * List of error details
	 */
	private List<StravaAPIError>	errors;

	/**
	 * No args constructor
	 */
	public StravaResponse() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaResponse)) {
			return false;
		}
		final StravaResponse other = (StravaResponse) obj;
		if (this.errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (!this.errors.equals(other.errors)) {
			return false;
		}
		if (this.message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!this.message.equals(other.message)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the errors
	 */
	public List<StravaAPIError> getErrors() {
		return this.errors;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.errors == null) ? 0 : this.errors.hashCode());
		result = (prime * result) + ((this.message == null) ? 0 : this.message.hashCode());
		return result;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(final List<StravaAPIError> errors) {
		this.errors = errors;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaResponse [message=" + this.message + ", errors=" + this.errors + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}

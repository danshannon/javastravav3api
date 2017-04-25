package javastrava.auth.model;

import javastrava.api.API;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaEntity;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * The TokenResponse is returned by authorisation services; it contains user details and the access token which is then used for authentication purposes for all other Strava API access
 * </p>
 *
 * @author Dan Shannon
 */
public class TokenResponse implements StravaEntity {
	/**
	 * The value of the access token
	 */
	private String accessToken;

	/**
	 * The type of token (usually "Bearer" - is used to create the authentication request header - see {@link API#instance(Class, Token)}
	 */
	private String tokenType;

	/**
	 * Strava returns details of the athlete along with the access token
	 */
	private StravaAthlete athlete;

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
		if (!(obj instanceof TokenResponse)) {
			return false;
		}
		final TokenResponse other = (TokenResponse) obj;
		if (this.accessToken == null) {
			if (other.accessToken != null) {
				return false;
			}
		} else if (!this.accessToken.equals(other.accessToken)) {
			return false;
		}
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.tokenType == null) {
			if (other.tokenType != null) {
				return false;
			}
		} else if (!this.tokenType.equals(other.tokenType)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return this.accessToken;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return this.tokenType;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.accessToken == null) ? 0 : this.accessToken.hashCode());
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.tokenType == null) ? 0 : this.tokenType.hashCode());
		return result;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param tokenType
	 *            the tokenType to set
	 */
	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenResponse [accessToken=" + this.accessToken + ", tokenType=" + this.tokenType + ", athlete=" + this.athlete + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}

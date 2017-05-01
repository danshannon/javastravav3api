package javastrava.auth.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javastrava.auth.AuthorisationService;
import javastrava.auth.TokenService;
import javastrava.auth.impl.TokenServiceImpl;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaEntity;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ActivityService;
import javastrava.service.AthleteService;
import javastrava.service.ChallengeService;
import javastrava.service.ClubGroupEventService;
import javastrava.service.ClubService;
import javastrava.service.GearService;
import javastrava.service.RouteService;
import javastrava.service.RunningRaceService;
import javastrava.service.SegmentEffortService;
import javastrava.service.SegmentService;
import javastrava.service.StravaService;
import javastrava.service.StreamService;
import javastrava.service.UploadService;
import javastrava.service.WebhookService;
import javastrava.service.impl.ActivityServiceImpl;
import javastrava.service.impl.AthleteServiceImpl;
import javastrava.service.impl.ChallengeServiceImpl;
import javastrava.service.impl.ClubGroupEventServiceImpl;
import javastrava.service.impl.ClubServiceImpl;
import javastrava.service.impl.GearServiceImpl;
import javastrava.service.impl.RouteServiceImpl;
import javastrava.service.impl.RunningRaceServiceImpl;
import javastrava.service.impl.SegmentEffortServiceImpl;
import javastrava.service.impl.SegmentServiceImpl;
import javastrava.service.impl.StravaServiceImpl;
import javastrava.service.impl.StreamServiceImpl;
import javastrava.service.impl.UploadServiceImpl;
import javastrava.service.impl.WebhookServiceImpl;

/**
 * <p>
 * The token acts as the bearer of authentication within each request to the Strava API.
 * </p>
 *
 * <p>
 * A token is used to acquire an implementation of each of the service objects that sub-class {@link StravaServiceImpl}
 * </p>
 *
 * <p>
 * Tokens are acquired through the OAuth process; this implementation of the API does not provide a purely programmatic way to acquire a token as that would kind of destroy the point(!) - although
 * once a user has given their permission to the application via the OAuth process, you can use {@link AuthorisationService#tokenExchange(Integer, String, String, AuthorisationScope...)} to acquire a
 * token at that point in the process.
 * </p>
 *
 * <p>
 * The application will now be able to make requests on the user’s behalf using the access_token query string parameter (GET) or POST/PUT body, or the Authorization header. This is done auto-magically
 * by javastrava.
 * </p>
 *
 * <p>
 * Applications should check for a 401 Unauthorized response. Access for those tokens has been revoked by the user.
 * </p>
 *
 * @see <a href="http://strava.github.io/api/v3/oauth/">http://strava.github.io/api/v3/oauth/</a>
 *
 * @author Dan Shannon
 *
 */
public class Token implements StravaEntity {
	/**
	 * The {@link StravaAthlete athlete} to whom this token is assigned
	 */
	private StravaAthlete athlete;

	/**
	 * The value of the access token, which is used in requests issued via the API
	 */
	private String token;

	/**
	 * List of {@link AuthorisationScope authorisation scopes} granted for this token
	 */
	private List<AuthorisationScope> scopes;

	/**
	 * List of service implementations associated with this token
	 */
	private HashMap<Class<? extends StravaService>, StravaService> services;

	/**
	 * Token type used in the authorisation header of requests to the Strava API - usually set to "Bearer"
	 */
	private String tokenType;

	/**
	 * No-args constructor
	 */
	public Token() {
		super();

		// Get pre-packed instances of all the services
		addServiceInstances();
	}

	/**
	 * <p>
	 * Default constructor is based on the {@link TokenResponse} structure received from {@link AuthorisationService#tokenExchange(Integer, String, String, AuthorisationScope...)}
	 * </p>
	 *
	 * @param tokenResponse
	 *            The response as received from {@link AuthorisationService#tokenExchange(Integer, String, String, AuthorisationScope...)}
	 * @param scopes
	 *            The list of authorisation scopes to be associated with the token
	 */
	public Token(final TokenResponse tokenResponse, final AuthorisationScope... scopes) {
		this.athlete = tokenResponse.getAthlete();
		this.token = tokenResponse.getAccessToken();
		this.tokenType = tokenResponse.getTokenType();
		this.scopes = Arrays.asList(scopes);

		// Get pre-packed instances of all the services
		addServiceInstances();
	}

	private void addServiceInstances() {
		this.services = new HashMap<Class<? extends StravaService>, StravaService>();
		this.addService(ActivityService.class, ActivityServiceImpl.instance(this));
		this.addService(AthleteService.class, AthleteServiceImpl.instance(this));
		this.addService(ChallengeService.class, ChallengeServiceImpl.instance(this));
		this.addService(ClubService.class, ClubServiceImpl.instance(this));
		this.addService(ClubGroupEventService.class, ClubGroupEventServiceImpl.instance(this));
		this.addService(GearService.class, GearServiceImpl.instance(this));
		this.addService(RouteService.class, RouteServiceImpl.instance(this));
		this.addService(RunningRaceService.class, RunningRaceServiceImpl.instance(this));
		this.addService(SegmentEffortService.class, SegmentEffortServiceImpl.instance(this));
		this.addService(SegmentService.class, SegmentServiceImpl.instance(this));
		this.addService(StreamService.class, StreamServiceImpl.instance(this));
		this.addService(TokenService.class, TokenServiceImpl.instance(this));
		this.addService(UploadService.class, UploadServiceImpl.instance(this));
		this.addService(WebhookService.class, WebhookServiceImpl.instance(this));
	}

	/**
	 * <p>
	 * Adds a service implementation into the Token's store
	 * </p>
	 *
	 * @param class1
	 *            The class of the service implementation
	 * @param service
	 *            The service implementation
	 */
	public void addService(final Class<? extends StravaService> class1, final StravaService service) {
		this.services.put(class1, service);
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
		if (!(obj instanceof Token)) {
			return false;
		}
		final Token other = (Token) obj;
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.scopes == null) {
			if (other.scopes != null) {
				return false;
			}
		} else if (!this.scopes.equals(other.scopes)) {
			return false;
		}
		if (this.services == null) {
			if (other.services != null) {
				return false;
			}
		} else if (!this.services.equals(other.services)) {
			return false;
		}
		if (this.token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!this.token.equals(other.token)) {
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
	 * @return the scopes
	 */
	public List<AuthorisationScope> getScopes() {
		return this.scopes;
	}

	/**
	 * <p>
	 * Gets the service implementation of the required class from the token
	 * </p>
	 *
	 * @param <T>
	 *            The class being returned
	 * @param class1
	 *            The class to return
	 * @return The implementation of the service required
	 */
	@SuppressWarnings("unchecked")
	public <T extends StravaService> T getService(final Class<T> class1) {
		return (T) this.services.get(class1);
	}

	/**
	 * @return the services
	 */
	public HashMap<Class<? extends StravaService>, StravaService> getServices() {
		return this.services;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return this.token;
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
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.scopes == null) ? 0 : this.scopes.hashCode());
		result = (prime * result) + ((this.services == null) ? 0 : this.services.hashCode());
		result = (prime * result) + ((this.token == null) ? 0 : this.token.hashCode());
		result = (prime * result) + ((this.tokenType == null) ? 0 : this.tokenType.hashCode());
		return result;
	}

	/**
	 * <p>
	 * Validates that the toke has view private access (according to the scopes that it was granted on creation at least; it is quite possible that permissions have subsequently been revoked by the
	 * user)
	 * </p>
	 *
	 * @return <code>true</code> if the token contains the {@link AuthorisationScope#VIEW_PRIVATE}
	 */
	public boolean hasViewPrivate() {
		if ((this.scopes != null) && this.scopes.contains(AuthorisationScope.VIEW_PRIVATE)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Validates that the token has write access (according to the scopes that it was granted on creation at least; it is quite possible that permissions have subsequently been revoked by the user)
	 * </p>
	 *
	 * @return <code>true</code> if the token contains the {@link AuthorisationScope#WRITE}
	 */
	public boolean hasWriteAccess() {
		if ((this.scopes != null) && this.scopes.contains(AuthorisationScope.WRITE)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Removes the service from the Token's store
	 * </p>
	 *
	 * @param class1
	 *            The class of token to be removed
	 */
	public void removeService(final Class<? extends StravaService> class1) {
		this.services.remove(class1);
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(final StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param scopes
	 *            the scopes to set
	 */
	public void setScopes(final List<AuthorisationScope> scopes) {
		this.scopes = scopes;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	public void setServices(final HashMap<Class<? extends StravaService>, StravaService> services) {
		this.services = services;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(final String token) {
		this.token = token;
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
		return "Token [athlete=" + this.athlete + ", token=" + this.token + ", scopes=" + this.scopes + ", services=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.services + ", tokenType=" //$NON-NLS-1$
				+ this.tokenType + "]"; //$NON-NLS-1$
	}

}

package javastrava.api.v3.auth.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.ActivityService;
import javastrava.api.v3.service.AthleteService;
import javastrava.api.v3.service.ClubService;
import javastrava.api.v3.service.GearService;
import javastrava.api.v3.service.SegmentEffortService;
import javastrava.api.v3.service.SegmentService;
import javastrava.api.v3.service.StravaService;
import javastrava.api.v3.service.StreamService;
import javastrava.api.v3.service.UploadService;
import javastrava.api.v3.service.impl.ActivityServiceImpl;
import javastrava.api.v3.service.impl.AthleteServiceImpl;
import javastrava.api.v3.service.impl.ClubServiceImpl;
import javastrava.api.v3.service.impl.GearServiceImpl;
import javastrava.api.v3.service.impl.SegmentServiceImpl;
import javastrava.api.v3.service.impl.StravaServiceImpl;
import javastrava.api.v3.service.impl.StreamServiceImpl;
import javastrava.api.v3.service.impl.UploadServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
 * Tokens are acquired through the OAuth process; this implementation of the API does not provide a purely programmatic way to acquire a token as that would
 * kind of destroy the point(!) - although once a user has given their permission to the application via the OAuth process, you can use
 * {@link AuthorisationService#tokenExchange(Integer, String, String, AuthorisationScope...)} to acquire a token at that point in the process.
 * </p>
 * 
 * <p>
 * The application will now be able to make requests on the user’s behalf using the access_token query string parameter (GET) or POST/PUT body, or the
 * Authorization header. This is done auto-magically by javastrava.
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
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Token {
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
	
	private String tokenType;

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
		this.services = new HashMap<Class<? extends StravaService>, StravaService>();
		
		// Get pre-packed instances of all the services
		this.addService(ActivityService.class, ActivityServiceImpl.instance(this));
		this.addService(AthleteService.class, AthleteServiceImpl.instance(this));
		this.addService(ClubService.class, ClubServiceImpl.instance(this));
		this.addService(GearService.class, GearServiceImpl.instance(this));
		this.addService(SegmentEffortService.class, GearServiceImpl.instance(this));
		this.addService(SegmentService.class, SegmentServiceImpl.instance(this));
		this.addService(StreamService.class, StreamServiceImpl.instance(this));
		this.addService(UploadService.class, UploadServiceImpl.instance(this));
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
	 * <p>
	 * Gets the service implementation of the required class from the token
	 * </p>
	 * 
	 * @param <T> The class being returned
	 * @param class1 The class to return
	 * @return The implementation of the service required
	 */
	@SuppressWarnings("unchecked")
	public <T extends StravaService> T getService(final Class<T> class1) {
		return (T) this.services.get(class1);
	}

	/**
	 * <p>
	 * Removes the service from the Token's store
	 * </p>
	 * @param class1 The class of token to be removed
	 */
	public void removeService(final Class<? extends StravaService> class1) {
		this.services.remove(class1);
	}

	/**
	 * <p>
	 * Validates that the token has write access (according to the scopes that it was granted on creation at least; it is quite possible that permissions have subsequently been revoked by the user)
	 * </p>
	 * @return <code>true</code> if the token contains the {@link AuthorisationScope#WRITE}
	 */
	public boolean hasWriteAccess() {
		if (this.scopes != null && this.scopes.contains(AuthorisationScope.WRITE)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Validates that the toke has view private access (according to the scopes that it was granted on creation at least; it is quite possible that permissions have subsequently been revoked by the user)
	 * </p>
	 * @return <code>true</code> if the token contains the {@link AuthorisationScope#VIEW_PRIVATE}
	 */
	public boolean hasViewPrivate() {
		if (this.scopes != null && this.scopes.contains(AuthorisationScope.VIEW_PRIVATE)) {
			return true;
		}
		return false;
	}

}

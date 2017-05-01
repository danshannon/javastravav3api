package javastrava.api;

import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.concurrent.CompletableFuture;

import javastrava.api.async.StravaAPICallback;
import javastrava.api.async.StravaAPIFuture;
import javastrava.api.util.RetrofitClientResponseInterceptor;
import javastrava.api.util.RetrofitErrorHandler;
import javastrava.auth.impl.AuthorisationServiceImpl;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.config.StravaConfig;
import javastrava.json.impl.JsonUtilImpl;
import javastrava.model.StravaActivity;
import javastrava.model.StravaActivityUpdate;
import javastrava.model.StravaActivityZone;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaAthleteZones;
import javastrava.model.StravaChallenge;
import javastrava.model.StravaClub;
import javastrava.model.StravaClubAnnouncement;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubEventJoinResponse;
import javastrava.model.StravaClubMembershipResponse;
import javastrava.model.StravaComment;
import javastrava.model.StravaGear;
import javastrava.model.StravaLap;
import javastrava.model.StravaPhoto;
import javastrava.model.StravaResponse;
import javastrava.model.StravaRoute;
import javastrava.model.StravaRunningRace;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaSegmentExplorerResponse;
import javastrava.model.StravaSegmentLeaderboard;
import javastrava.model.StravaStatistics;
import javastrava.model.StravaStream;
import javastrava.model.StravaUploadResponse;
import javastrava.model.reference.StravaActivityType;
import javastrava.model.reference.StravaAgeGroup;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaLeaderboardDateRange;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSegmentExplorerActivityType;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaWeightClass;
import javastrava.model.webhook.StravaEventSubscription;
import javastrava.model.webhook.reference.StravaSubscriptionAspectType;
import javastrava.model.webhook.reference.StravaSubscriptionObjectType;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * <p>
 * Provides a static method {@link #instance(Class, Token)} which constructs a standard retrofit service with all the required options.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class API {
	/**
	 * Instance of authorisation API which is used for token exchange
	 */
	private static AuthorisationAPI authorisationAPI;

	/**
	 * <p>
	 * Get an instance of the authorisation API (cached)
	 * </p>
	 *
	 * @return Instance of the authorisation API
	 */
	public static AuthorisationAPI authorisationInstance() {
		if (authorisationAPI == null) {
			authorisationAPI = new RestAdapter.Builder().setClient(new RetrofitClientResponseInterceptor()).setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
					.setLogLevel(API.logLevel(AuthorisationServiceImpl.class)).setEndpoint(StravaConfig.AUTH_ENDPOINT).setErrorHandler(new RetrofitErrorHandler()).build()
					.create(AuthorisationAPI.class);
		}
		return authorisationAPI;
	}

	/**
	 * Generates a callback for the API, based on a {@link CompletableFuture}. {@link CompletableFuture#complete(Object)} will be called when the asynchronous call to the API is complete
	 *
	 * @param <T>
	 *            The type of object which will be returned to the caller
	 * @param completableFuture
	 *            The future
	 * @return The callback
	 */
	private static <T> StravaAPICallback<T> callback(final StravaAPIFuture<T> completableFuture) {
		return new StravaAPICallback<T>(completableFuture);
	}

	/**
	 * @return the authorisationAPI
	 */
	public static AuthorisationAPI getAuthorisationAPI() {
		return authorisationAPI;
	}

	/**
	 * <p>
	 * Creates and returns a new API RestAdapter instance.
	 * </p>
	 *
	 * @param class1
	 *            The class to be returned
	 * @param token
	 *            The access token required for authentication of requests to the Strava API
	 * @param <T>
	 *            Class of API interface to be instantiated (one of the *API.java interfaces)
	 * @return A REST service
	 */
	public static <T> T instance(final Class<T> class1, final Token token) {
		return new RestAdapter.Builder()
				// Client overrides handling of Strava-specific headers in the response, to deal with rate limiting
				.setClient(new RetrofitClientResponseInterceptor())
				// Converter is a GSON implementation with custom converters
				.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
				// Log level is determined per API service
				.setLogLevel(API.logLevel(class1))
				// Endpoint is the same for all services
				.setEndpoint(StravaConfig.ENDPOINT)
				// Request interceptor adds the access token into headers for each request
				.setRequestInterceptor(request -> request.addHeader(StravaConfig.string("strava.authorization_header_name"), //$NON-NLS-1$
						token.getTokenType() + " " + token.getToken())) //$NON-NLS-1$
				// Error handler deals with Strava's implementations of 400, 401, 403, 404 errors etc.
				.setErrorHandler(new RetrofitErrorHandler()).build().create(class1);
	}

	/**
	 * @param class1
	 *            Class for which log level is to be determined
	 * @return The appropriate log level for the class
	 */
	public static LogLevel logLevel(final Class<?> class1) {
		final String propertyName = "retrofit." + class1.getName() + ".log_level"; //$NON-NLS-1$ //$NON-NLS-2$
		RestAdapter.LogLevel logLevel = null;
		try {
			logLevel = RestAdapter.LogLevel.valueOf(StravaConfig.string(propertyName));
		} catch (final MissingResourceException e) {
			logLevel = RestAdapter.LogLevel.valueOf(StravaConfig.string("retrofit.log_level")); //$NON-NLS-1$
		}
		return logLevel;
	}

	/**
	 * @param authorisationAPI
	 *            the authorisationAPI to set
	 */
	public static void setAuthorisationAPI(AuthorisationAPI authorisationAPI) {
		API.authorisationAPI = authorisationAPI;
	}

	/**
	 * API instance for access to activity data
	 */
	private ActivityAPI activityAPI;

	/**
	 * API instance for access to athlete data
	 */
	private AthleteAPI athleteAPI;

	/**
	 * API instance for access to club data
	 */
	private ClubAPI clubAPI;

	/**
	 * API instance for access to gear data
	 */
	private GearAPI gearAPI;

	/**
	 * API instance for access to running race data
	 */
	private RunningRaceAPI runningRaceAPI;

	/**
	 * API instance for access to route data
	 */
	private RouteAPI routeAPI;

	/**
	 * API instance for access to challenge data
	 */
	private ChallengeAPI challengeAPI;

	/**
	 * API instance for access to club group event data
	 */
	private ClubGroupEventAPI clubGroupEventAPI;

	/**
	 * API instance for access to segment data
	 */
	private SegmentAPI segmentAPI;

	/**
	 * API instance for access to segment effort data
	 */
	private SegmentEffortAPI effortAPI;

	/**
	 * API instance for access to streams data
	 */
	private StreamAPI streamAPI;

	/**
	 * API instance for access to token deauthorisation
	 */
	private TokenAPI tokenAPI;

	/**
	 * API instance for access to activity upload functionality
	 */
	private UploadAPI uploadAPI;

	/**
	 * API instance for access to webhook subscriptions
	 */
	private WebhookAPI webhookAPI;

	/**
	 * Construct an API instance with a given token
	 *
	 * @param token
	 *            The access token to be used with calls to the API
	 */
	public API(final Token token) {
		addAPIInstances(token);
	}

	/**
	 * Construct an API with a previously know token value
	 *
	 * @param tokenValue
	 *            String value of the token
	 * @param scopes
	 *            Authorisation scopes granted to the token
	 */
	public API(final String tokenValue, AuthorisationScope... scopes) {
		final Token token = new Token();
		token.setScopes(Arrays.asList(scopes));
		token.setToken(tokenValue);
		addAPIInstances(token);
		token.setAthlete(this.athleteAPI.getAuthenticatedAthlete());
	}

	private void addAPIInstances(Token token) {
		this.activityAPI = API.instance(ActivityAPI.class, token);
		this.athleteAPI = API.instance(AthleteAPI.class, token);
		this.challengeAPI = API.instance(ChallengeAPI.class, token);
		this.clubAPI = API.instance(ClubAPI.class, token);
		this.clubGroupEventAPI = API.instance(ClubGroupEventAPI.class, token);
		this.gearAPI = API.instance(GearAPI.class, token);
		this.segmentAPI = API.instance(SegmentAPI.class, token);
		this.effortAPI = API.instance(SegmentEffortAPI.class, token);
		this.routeAPI = API.instance(RouteAPI.class, token);
		this.runningRaceAPI = API.instance(RunningRaceAPI.class, token);
		this.streamAPI = API.instance(StreamAPI.class, token);
		this.tokenAPI = API.instance(TokenAPI.class, token);
		this.uploadAPI = API.instance(UploadAPI.class, token);
		this.webhookAPI = API.instance(WebhookAPI.class, token);
	}

	/**
	 * @param id
	 *            The upload id as given back in the response to {@link #upload(StravaActivityType, String, String, Boolean, Boolean, Boolean, String, String, TypedFile)}
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @see javastrava.api.UploadAPI#checkUploadStatus(java.lang.Long)
	 */
	public StravaUploadResponse checkUploadStatus(final Long id) {
		return this.uploadAPI.checkUploadStatus(id);
	}

	/**
	 * @param uploadId
	 *            The upload id as given back in the response to {@link #upload(StravaActivityType, String, String, Boolean, Boolean, Boolean, String, String, TypedFile)}
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.UploadAPI#checkUploadStatus(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaUploadResponse> checkUploadStatusAsync(final Long uploadId) {
		final StravaAPIFuture<StravaUploadResponse> future = new StravaAPIFuture<StravaUploadResponse>();
		this.uploadAPI.checkUploadStatus(uploadId, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param text
	 *            Text of the comment to create
	 * @return The comment as posted
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the comment text is null or the empty string
	 * @see javastrava.api.ActivityAPI#createComment(java.lang.Long, java.lang.String)
	 */
	public StravaComment createComment(final Long activityId, final String text) throws BadRequestException, NotFoundException {
		return this.activityAPI.createComment(activityId, text);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param text
	 *            Text of the comment to create
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the comment text is null or the empty string
	 */
	public StravaAPIFuture<StravaComment> createCommentAsync(final Long activityId, final String text) throws BadRequestException, NotFoundException {
		final StravaAPIFuture<StravaComment> future = new StravaAPIFuture<StravaComment>();
		this.activityAPI.createComment(activityId, text, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Create a manual activity
	 * </p>
	 *
	 * @param activity
	 *            Activity to create on Strava
	 * @return The activity as created on Strava, if successful
	 * @throws BadRequestException
	 *             If the request is invalid in some way
	 * @see javastrava.api.ActivityAPI#createManualActivity(javastrava.model.StravaActivity)
	 */
	public StravaActivity createManualActivity(final StravaActivity activity) throws BadRequestException {
		return this.activityAPI.createManualActivity(activity);
	}

	/**
	 * @param activity
	 *            The activity to be created on Strava
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the activity is malformed and can't be uploaded
	 * @see javastrava.api.ActivityAPI#createManualActivity(javastrava.model.StravaActivity, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> createManualActivityAsync(final StravaActivity activity) throws BadRequestException {
		final StravaAPIFuture<StravaActivity> future = new StravaAPIFuture<StravaActivity>();
		this.activityAPI.createManualActivity(activity, callback(future));
		return future;
	}

	/**
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @param objectType
	 *            The type of object being subscribed to
	 * @param aspectType
	 *            The aspect being subscribed to
	 * @param callbackURL
	 *            (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken
	 *            The token's value will be included in the GET callback request when verifying the endpoint
	 * @return Details of the event subscription
	 */
	public StravaEventSubscription createSubscription(final Integer clientId, final String clientSecret, final StravaSubscriptionObjectType objectType, final StravaSubscriptionAspectType aspectType,
			final String callbackURL, final String verifyToken) {
		return this.webhookAPI.createSubscription(clientId, clientSecret, objectType, aspectType, callbackURL, verifyToken);
	}

	/**
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @param objectType
	 *            The type of object being subscribed to
	 * @param aspectType
	 *            The aspect being subscribed to
	 * @param callbackURL
	 *            (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken
	 *            The token's value will be included in the GET callback request when verifying the endpoint
	 * @return Details of the event subscription
	 */
	public StravaAPIFuture<StravaEventSubscription> createSubscriptionAsync(final Integer clientId, final String clientSecret, final StravaSubscriptionObjectType objectType,
			final StravaSubscriptionAspectType aspectType, final String callbackURL, final String verifyToken) {
		final StravaAPIFuture<StravaEventSubscription> future = new StravaAPIFuture<StravaEventSubscription>();
		this.webhookAPI.createSubscription(clientId, clientSecret, objectType, aspectType, callbackURL, verifyToken, callback(future));
		return future;
	}

	/**
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 * @see javastrava.api.TokenAPI#deauthoriseToken(java.lang.String)
	 */
	public TokenResponse deauthoriseToken(final String accessToken) throws UnauthorizedException {
		return this.tokenAPI.deauthoriseToken(accessToken);
	}

	/**
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 * @see javastrava.api.TokenAPI#deauthorise(java.lang.String, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<TokenResponse> deauthoriseTokenAsync(final String accessToken) throws UnauthorizedException {
		final StravaAPIFuture<TokenResponse> future = new StravaAPIFuture<TokenResponse>();
		this.tokenAPI.deauthorise(accessToken, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Delete an activity on Strava
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @return The representation of the deleted activity
	 * @throws NotFoundException
	 *             If the identified activity does not exist
	 * @see javastrava.api.ActivityAPI#deleteActivity(java.lang.Long)
	 */
	public StravaActivity deleteActivity(final Long id) throws NotFoundException {
		return this.activityAPI.deleteActivity(id);
	}

	/**
	 * <p>
	 * Delete an activity on Strava
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the identified activity does not exist
	 * @see javastrava.api.ActivityAPI#deleteActivity(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> deleteActivityAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaActivity> future = new StravaAPIFuture<StravaActivity>();
		this.activityAPI.deleteActivity(activityId, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Id of the activity the comment was posted to
	 * @param commentId
	 *            Id of the comment
	 * @return Strava response
	 * @throws NotFoundException
	 *             If the comment does not exist
	 * @see javastrava.api.ActivityAPI#deleteComment(java.lang.Long, java.lang.Integer)
	 */
	public StravaResponse deleteComment(final Long activityId, final Integer commentId) throws NotFoundException {
		return this.activityAPI.deleteComment(activityId, commentId);
	}

	/**
	 * @param activityId
	 *            Id of the activity the comment was posted to
	 * @param commentId
	 *            Id of the comment
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the comment does not exist
	 * @see javastrava.api.ActivityAPI#deleteComment(java.lang.Long, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaResponse> deleteCommentAsync(final Long activityId, final Integer commentId) throws NotFoundException {
		final StravaAPIFuture<StravaResponse> future = new StravaAPIFuture<StravaResponse>();
		this.activityAPI.deleteComment(activityId, commentId, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Deletes (and cancels) an event, which must be editable by the authenticating user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event to be deleted
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the authenticated athlete does not have permission to delete the event
	 */
	@DELETE("/group_events/{id}")
	public void deleteEvent(@Path("id") Integer id) throws NotFoundException, UnauthorizedException {
		this.clubGroupEventAPI.deleteEvent(id);
	}

	/**
	 * <p>
	 * Deletes (and cancels) an event, which must be editable by the authenticating user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event to be deleted
	 * @return Callback that can be used later to get results
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the authenticated athlete does not have permission to delete the event
	 */
	@DELETE("/group_events/{id}")
	public StravaAPIFuture<Void> deleteEventAsync(@Path("id") Integer id) throws NotFoundException, UnauthorizedException {
		final StravaAPIFuture<Void> future = new StravaAPIFuture<>();
		this.clubGroupEventAPI.deleteEvent(id, callback(future));
		return future;
	}

	/**
	 * @param subscriptionId
	 *            The id of the subscription to be deleted
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @return Returns nothing on success
	 * @see javastrava.api.WebhookAPI#deleteSubscription(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public StravaResponse deleteSubscription(final Integer subscriptionId, final Integer clientId, final String clientSecret) {
		return this.webhookAPI.deleteSubscription(subscriptionId, clientId, clientSecret);
	}

	/**
	 * @param subscriptionId
	 *            The id of the subscription to be deleted
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @return Returns nothing on success
	 * @see javastrava.api.WebhookAPI#deleteSubscription(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public StravaAPIFuture<StravaResponse> deleteSubscriptionAsync(final Integer subscriptionId, final Integer clientId, final String clientSecret) {
		final StravaAPIFuture<StravaResponse> future = new StravaAPIFuture<StravaResponse>();
		this.webhookAPI.deleteSubscription(subscriptionId, clientId, clientSecret, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Get details of an activity
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @param includeAllEfforts
	 *            Whether or not to include only efforts that Strava considers 'important'
	 * @return The Strava activity with the given id, if it exists
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @see javastrava.api.ActivityAPI#getActivity(java.lang.Long, java.lang.Boolean)
	 */
	public StravaActivity getActivity(final Long id, final Boolean includeAllEfforts) throws NotFoundException {
		return this.activityAPI.getActivity(id, includeAllEfforts);
	}

	/**
	 * @return the activityAPI
	 */
	public ActivityAPI getActivityAPI() {
		return this.activityAPI;
	}

	/**
	 * <p>
	 * Get details of an activity
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @param includeAllEfforts
	 *            Whether or not to include only efforts that Strava considers 'important'
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @see javastrava.api.ActivityAPI#getActivity(java.lang.Long, java.lang.Boolean, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> getActivityAsync(final Long id, final Boolean includeAllEfforts) throws NotFoundException {
		final StravaAPIFuture<StravaActivity> future = new StravaAPIFuture<StravaActivity>();
		this.activityAPI.getActivity(id, includeAllEfforts, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getActivityStreams(java.lang.Long, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getActivityStreams(final Long activityId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getActivityStreams(activityId, types, resolution, seriesType);
	}

	/**
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getActivityStreams(java.lang.Long, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getActivityStreamsAsync(final Long activityId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaStream[]> future = new StravaAPIFuture<StravaStream[]>();
		this.streamAPI.getActivityStreams(activityId, types, resolution, seriesType, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Details of the athlete, will be somewhat anonymised if the athlete is private
	 * @throws NotFoundException
	 *             If the athlete doesn't exist
	 * @see javastrava.api.AthleteAPI#getAthlete(java.lang.Integer)
	 */
	public StravaAthlete getAthlete(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.getAthlete(athleteId);
	}

	/**
	 * @return the athleteAPI
	 */
	public AthleteAPI getAthleteAPI() {
		return this.athleteAPI;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the athlete doesn't exist
	 * @see javastrava.api.AthleteAPI#getAthlete(java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete> getAthleteAsync(final Integer athleteId) throws NotFoundException {
		final StravaAPIFuture<StravaAthlete> future = new StravaAPIFuture<StravaAthlete>();
		this.athleteAPI.getAthlete(athleteId, callback(future));
		return future;
	}

	/**
	 * @return Full details of the authenticated athlete
	 * @see javastrava.api.AthleteAPI#getAuthenticatedAthlete()
	 */
	public StravaAthlete getAuthenticatedAthlete() {
		return this.athleteAPI.getAuthenticatedAthlete();
	}

	/**
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.AthleteAPI#getAuthenticatedAthlete(javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete> getAuthenticatedAthleteAsync() {
		final StravaAPIFuture<StravaAthlete> future = new StravaAPIFuture<StravaAthlete>();
		this.athleteAPI.getAuthenticatedAthlete(callback(future));
		return future;
	}

	/**
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1
	 *
	 * @return The athlete zones object
	 */
	@GET("/athlete/zones")
	public StravaAthleteZones getAuthenticatedAthleteZones() {
		return this.athleteAPI.getAuthenticatedAthleteZones();
	}

	/**
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1
	 *
	 * @return The athlete zones object (via a future)
	 */
	@GET("/athlete/zones")
	public StravaAPIFuture<StravaAthleteZones> getAuthenticatedAthleteZonesAsync() {
		final StravaAPIFuture<StravaAthleteZones> future = new StravaAPIFuture<StravaAthleteZones>();
		this.athleteAPI.getAuthenticatedAthleteZones(callback(future));
		return future;
	}

	/**
	 * <p>
	 * Retrieve a challenge
	 * </p>
	 *
	 * <p>
	 * Returns a detailed representation of a challenge
	 * </p>
	 *
	 * @param id
	 *            Identifier of the challenge
	 * @return The challenge
	 */
	public StravaChallenge getChallenge(Integer id) {
		return this.challengeAPI.getChallenge(id);
	}

	/**
	 * @return the challengeAPI
	 */
	public ChallengeAPI getChallengeAPI() {
		return this.challengeAPI;
	}

	/**
	 * <p>
	 * Retrieve a challenge
	 * </p>
	 *
	 * <p>
	 * Returns a detailed representation of a challenge
	 * </p>
	 *
	 * @param id
	 *            Identifier of the challenge
	 * @return The {@link CompletableFuture} giving access to the returned challenge
	 */
	public StravaAPIFuture<StravaChallenge> getChallengeAsync(Integer id) {
		final StravaAPIFuture<StravaChallenge> future = new StravaAPIFuture<>();
		this.challengeAPI.getChallenge(id, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Club details
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#getClub(java.lang.Integer)
	 */
	public StravaClub getClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.getClub(clubId);
	}

	/**
	 * @return the clubAPI
	 */
	public ClubAPI getClubAPI() {
		return this.clubAPI;
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#getClub(java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClub> getClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClub> future = new StravaAPIFuture<StravaClub>();
		this.clubAPI.getClub(clubId, callback(future));
		return future;
	}

	/**
	 * @return the clubGroupEventAPI
	 */
	public ClubGroupEventAPI getClubGroupEventAPI() {
		return this.clubGroupEventAPI;
	}

	/**
	 * @return the effortAPI
	 */
	public SegmentEffortAPI getEffortAPI() {
		return this.effortAPI;
	}

	/**
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getEffortStreams(java.lang.Long, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getEffortStreams(final Long segmentEffortId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType);
	}

	/**
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getEffortStreams(java.lang.Long, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getEffortStreamsAsync(final Long segmentEffortId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaStream[]> future = new StravaAPIFuture<StravaStream[]>();
		this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The group event
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	public StravaClubEvent getEvent(Integer id) throws NotFoundException {
		return this.clubGroupEventAPI.getEvent(id);
	}

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return Future which can be called later to return the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	public StravaAPIFuture<StravaClubEvent> getEventAsync(Integer id) throws NotFoundException {
		final StravaAPIFuture<StravaClubEvent> future = new StravaAPIFuture<>();
		this.clubGroupEventAPI.getEvent(id, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Returns a single group event summary representation.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The group event as a raw Retrofit response
	 * @throws NotFoundException
	 *             If the event does not exist
	 */
	public Response getEventRaw(Integer id) throws NotFoundException {
		return this.clubGroupEventAPI.getEventRaw(id);
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 * @see javastrava.api.GearAPI#getGear(java.lang.String)
	 */
	public StravaGear getGear(final String gearId) throws NotFoundException {
		return this.gearAPI.getGear(gearId);
	}

	/**
	 * @return the gearAPI
	 */
	public GearAPI getGearAPI() {
		return this.gearAPI;
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 * @see javastrava.api.GearAPI#getGear(java.lang.String, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaGear> getGearAsync(final String gearId) throws NotFoundException {
		final StravaAPIFuture<StravaGear> future = new StravaAPIFuture<StravaGear>();
		this.gearAPI.getGear(gearId, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A detailed representation of the running race
	 * @throws NotFoundException
	 *             If the race does not exist
	 * @throws UnauthorizedException
	 *             If the race is private or a security exception has occurred
	 */
	public StravaRunningRace getRace(Integer id) throws NotFoundException, UnauthorizedException {
		return this.runningRaceAPI.getRace(id);
	}

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A detailed representation of the running race
	 * @throws NotFoundException
	 *             If the race does not exist
	 * @throws UnauthorizedException
	 *             If the race is private or a security exception has occurred
	 */
	public StravaAPIFuture<StravaRunningRace> getRaceAsync(Integer id) throws NotFoundException, UnauthorizedException {
		final StravaAPIFuture<StravaRunningRace> future = new StravaAPIFuture<>();
		this.runningRaceAPI.getRace(id, callback(future));
		return future;
	}

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The route
	 * @throws NotFoundException
	 *             If the route does not exist
	 * @throws BadRequestException
	 *             If the id is not an integer
	 * @throws UnauthorizedException
	 *             If the route is private and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private}
	 */
	public StravaRoute getRoute(@Path("id") Integer routeId) throws NotFoundException, BadRequestException, UnauthorizedException {
		return this.routeAPI.getRoute(routeId);
	}

	/**
	 * @return the routeAPI
	 */
	public RouteAPI getRouteAPI() {
		return this.routeAPI;
	}

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The route
	 * @throws NotFoundException
	 *             If the route does not exist
	 * @throws BadRequestException
	 *             If the id is not an integer
	 * @throws UnauthorizedException
	 *             If the route is private and the token has {@link AuthorisationScope#VIEW_PRIVATE view_private}
	 */
	public StravaAPIFuture<StravaRoute> getRouteAsync(@Path("id") Integer routeId) throws NotFoundException, BadRequestException, UnauthorizedException {
		final StravaAPIFuture<StravaRoute> future = new StravaAPIFuture<>();
		this.routeAPI.getRoute(routeId, callback(future));
		return future;
	}

	/**
	 * @return the runningRaceAPI
	 */
	public RunningRaceAPI getRunningRaceAPI() {
		return this.runningRaceAPI;
	}

	/**
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return The Segment
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @see javastrava.api.SegmentAPI#getSegment(java.lang.Integer)
	 */
	public StravaSegment getSegment(final Integer segmentId) throws NotFoundException {
		return this.segmentAPI.getSegment(segmentId);
	}

	/**
	 * @return the segmentAPI
	 */
	public SegmentAPI getSegmentAPI() {
		return this.segmentAPI;
	}

	/**
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @see javastrava.api.SegmentAPI#getSegment(java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegment> getSegmentAsync(final Integer segmentId) throws NotFoundException {
		final StravaAPIFuture<StravaSegment> future = new StravaAPIFuture<StravaSegment>();
		this.segmentAPI.getSegment(segmentId, callback(future));
		return future;
	}

	/**
	 * @param segmentEffortId
	 *            Effort identifier
	 * @return Effort details
	 * @throws NotFoundException
	 *             If the effort with the given id doesn't exist
	 * @see javastrava.api.SegmentEffortAPI#getSegmentEffort(java.lang.Long)
	 */
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId) throws NotFoundException {
		return this.effortAPI.getSegmentEffort(segmentEffortId);
	}

	/**
	 * @param segmentEffortId
	 *            Effort identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the effort with the given id doesn't exist
	 * @see javastrava.api.SegmentEffortAPI#getSegmentEffort(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentEffort> getSegmentEffortAsync(final Long segmentEffortId) throws NotFoundException {
		final StravaAPIFuture<StravaSegmentEffort> future = new StravaAPIFuture<StravaSegmentEffort>();
		this.effortAPI.getSegmentEffort(segmentEffortId, callback(future));
		return future;
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param gender
	 *            (Optional) Gender to filter the leaderboard by
	 * @param ageGroup
	 *            (Optional) Age group to filter the leaderboard by
	 * @param weightClass
	 *            (Optional) Weight class to filter the leaderboard by
	 * @param following
	 *            (Optional) If <code>true</code> then filter leaderboard by athletes the authenticated athlete is following
	 * @param clubId
	 *            (Optional) Club to filter the leaderboard by
	 * @param dateRange
	 *            (Optional) Date range (this year, this month etc.) to filter the leaderboard by
	 * @param page
	 *            (Optional) Page number to return (default is 1)
	 * @param perPage
	 *            (Optional) Page size to return (default is 50)
	 * @param contextEntries
	 *            (Optional) Number of context entries to return either side of the authenticated athlete (default is 2, maximum is 15)
	 * @return A Strava leaderboard
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#getSegmentLeaderboard(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Integer page, final Integer perPage, final Integer contextEntries)
			throws NotFoundException, BadRequestException {
		return this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page, perPage, contextEntries);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param gender
	 *            (Optional) Gender to filter the leaderboard by
	 * @param ageGroup
	 *            (Optional) Age group to filter the leaderboard by
	 * @param weightClass
	 *            (Optional) Weight class to filter the leaderboard by
	 * @param following
	 *            (Optional) If <code>true</code> then filter leaderboard by athletes the authenticated athlete is following
	 * @param clubId
	 *            (Optional) Club to filter the leaderboard by
	 * @param dateRange
	 *            (Optional) Date range (this year, this month etc.) to filter the leaderboard by
	 * @param page
	 *            (Optional) Page number to return (default is 1)
	 * @param perPage
	 *            (Optional) Page size to return (default is 50)
	 * @param contextEntries
	 *            (Optional) Number of context entries to return either side of the authenticated athlete (default is 2, maximum is 15)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#getSegmentLeaderboard(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Integer page, final Integer perPage, final Integer contextEntries)
			throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegmentLeaderboard> future = new StravaAPIFuture<StravaSegmentLeaderboard>();
		this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page, perPage, contextEntries, callback(future));
		return future;
	}

	/**
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getSegmentStreams(java.lang.Integer, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getSegmentStreams(final Integer segmentId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType);
	}

	/**
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.StreamAPI#getSegmentStreams(java.lang.Integer, java.lang.String, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getSegmentStreamsAsync(final Integer segmentId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaStream[]> future = new StravaAPIFuture<StravaStream[]>();
		this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType, callback(future));
		return future;
	}

	/**
	 * @return the streamAPI
	 */
	public StreamAPI getStreamAPI() {
		return this.streamAPI;
	}

	/**
	 * @return the tokenAPI
	 */
	public TokenAPI getTokenAPI() {
		return this.tokenAPI;
	}

	/**
	 * @return the uploadAPI
	 */
	public UploadAPI getUploadAPI() {
		return this.uploadAPI;
	}

	/**
	 * @return the webhookAPI
	 */
	public WebhookAPI getWebhookAPI() {
		return this.webhookAPI;
	}

	/**
	 * @param activityId
	 *            Activity to be kudoed
	 * @return Strava response
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @see javastrava.api.ActivityAPI#giveKudos(java.lang.Long)
	 */
	public StravaResponse giveKudos(final Long activityId) throws NotFoundException {
		return this.activityAPI.giveKudos(activityId);
	}

	/**
	 * @param activityId
	 *            Activity to be kudoed
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @see javastrava.api.ActivityAPI#giveKudos(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaResponse> giveKudosAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaResponse> future = new StravaAPIFuture<StravaResponse>();
		this.activityAPI.giveKudos(activityId, callback(future));
		return future;
	}

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 */
	public void joinChallenge(Integer id) {
		this.challengeAPI.joinChallenge(id);
	}

	/**
	 * Join a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 * @return The {@link CompletableFuture} giving access to the returned challenge
	 */
	public StravaAPIFuture<StravaChallenge> joinChallengeAsync(Integer id) {
		final StravaAPIFuture<StravaChallenge> future = new StravaAPIFuture<>();
		this.challengeAPI.joinChallenge(id, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#joinClub(java.lang.Integer)
	 */
	public StravaClubMembershipResponse joinClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.joinClub(clubId);
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#joinClub(Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClubMembershipResponse> joinClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClubMembershipResponse> future = new StravaAPIFuture<StravaClubMembershipResponse>();
		this.clubAPI.joinClub(clubId, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Join a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, join the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	public StravaClubEventJoinResponse joinEvent(Integer id) throws NotFoundException, UnauthorizedException {
		return this.clubGroupEventAPI.joinEvent(id);
	}

	/**
	 * <p>
	 * Join a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, join the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return Future which can be called later to return the join response
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	public StravaAPIFuture<StravaClubEventJoinResponse> joinEventAsync(Integer id) throws NotFoundException, UnauthorizedException {
		final StravaAPIFuture<StravaClubEventJoinResponse> future = new StravaAPIFuture<>();
		this.clubGroupEventAPI.joinEvent(id, callback(future));
		return future;
	}

	/**
	 * Leave a challenge on behalf of the authenticated user. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to leave
	 */
	public void leaveChallenge(Integer id) {
		this.challengeAPI.leaveChallenge(id);

	}

	/**
	 * Leave a challenge on behalf of the authenticated athlete. An access token with write permissions is required.
	 *
	 * @param id
	 *            The id of the challenge to be joined
	 * @return The {@link CompletableFuture} giving access to the returned challenge
	 */
	public StravaAPIFuture<StravaChallenge> leaveChallengeAsync(Integer id) {
		final StravaAPIFuture<StravaChallenge> future = new StravaAPIFuture<>();
		this.challengeAPI.leaveChallenge(id, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#leaveClub(java.lang.Integer)
	 */
	public StravaClubMembershipResponse leaveClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.leaveClub(clubId);
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.ClubAPI#leaveClub(Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClubMembershipResponse> leaveClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClubMembershipResponse> future = new StravaAPIFuture<StravaClubMembershipResponse>();
		this.clubAPI.leaveClub(clubId, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Leave a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, leave the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return The response indicating whether the authenticated athlete has joined the event
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	public StravaClubEventJoinResponse leaveEvent(Integer id) throws NotFoundException, UnauthorizedException {
		return this.clubGroupEventAPI.leaveEvent(id);
	}

	/**
	 * <p>
	 * Leave a group {@link StravaClubEvent event} on behalf of the authenticated {@link StravaAthlete athlete}. For recurring events, leave the upcoming occurrence. An {@link Token access token} with
	 * {@link AuthorisationScope#WRITE write scope} is required.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the group event
	 * @return Future which can be called later to return the join response
	 * @throws NotFoundException
	 *             if the event does not exist
	 * @throws UnauthorizedException
	 *             if the {@link Token access token} does not have {@link AuthorisationScope#WRITE write scope}
	 */
	public StravaAPIFuture<StravaClubEventJoinResponse> leaveEventAsync(Integer id) throws NotFoundException, UnauthorizedException {
		final StravaAPIFuture<StravaClubEventJoinResponse> future = new StravaAPIFuture<>();
		this.clubGroupEventAPI.leaveEvent(id, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether or not to return comments including markdown
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return Array of comments belonging to the activity
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listActivityComments(java.lang.Long, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	public StravaComment[] listActivityComments(final Long activityId, final Boolean markdown, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listActivityComments(activityId, markdown, page, perPage);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether or not to return comments including markdown
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listActivityComments(java.lang.Long, java.lang.Boolean, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaComment[]> listActivityCommentsAsync(final Long activityId, final Boolean markdown, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaComment[]> future = new StravaAPIFuture<StravaComment[]>();
		this.activityAPI.listActivityComments(activityId, markdown, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return Array of athletes who have kudoed the activity
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listActivityKudoers(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listActivityKudoers(final Long activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listActivityKudoers(activityId, page, perPage);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listActivityKudoers(Long, Integer, Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listActivityKudoersAsync(final Long activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.activityAPI.listActivityKudoers(activityId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            The activity identifier
	 * @return Array of laps belonging to the activity
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @see javastrava.api.ActivityAPI#listActivityLaps(java.lang.Long)
	 */
	public StravaLap[] listActivityLaps(final Long activityId) throws NotFoundException {
		return this.activityAPI.listActivityLaps(activityId);
	}

	/**
	 * @param activityId
	 *            The activity identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listActivityLaps(Long, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaLap[]> listActivityLapsAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaLap[]> future = new StravaAPIFuture<StravaLap[]>();
		this.activityAPI.listActivityLaps(activityId, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return Array of photos attached to the activity
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @see javastrava.api.ActivityAPI#listActivityPhotos(java.lang.Long)
	 */
	public StravaPhoto[] listActivityPhotos(final Long activityId) throws NotFoundException {
		return this.activityAPI.listActivityPhotos(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @see javastrava.api.ActivityAPI#listActivityPhotos(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaPhoto[]> listActivityPhotosAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaPhoto[]> future = new StravaAPIFuture<StravaPhoto[]>();
		this.activityAPI.listActivityPhotos(activityId, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            The activity identifier
	 * @return Array of activity zones for the activity
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @see javastrava.api.ActivityAPI#listActivityZones(java.lang.Long)
	 */
	public StravaActivityZone[] listActivityZones(final Long activityId) throws NotFoundException {
		return this.activityAPI.listActivityZones(activityId);
	}

	/**
	 * @param activityId
	 *            The activity identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @see javastrava.api.ActivityAPI#listActivityZones(java.lang.Long, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivityZone[]> listActivityZonesAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaActivityZone[]> future = new StravaAPIFuture<StravaActivityZone[]>();
		this.activityAPI.listActivityZones(activityId, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who the identified athlete is following
	 * @throws NotFoundException
	 *             If the athlete with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthleteFriends(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthleteFriends(athleteId, page, perPage);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the athlete with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAthleteFriendsAsync(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.athleteAPI.listAthleteFriends(athleteId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of segment efforts which represent the athlete's KOM/QOM's
	 * @throws NotFoundException
	 *             If the athlete doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentEffort[] listAthleteKOMs(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthleteKOMs(athleteId, page, perPage);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the athlete doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentEffort[]> listAthleteKOMsAsync(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegmentEffort[]> future = new StravaAPIFuture<StravaSegmentEffort[]>();
		this.athleteAPI.listAthleteKOMs(athleteId, page, perPage, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param page
	 *            Page of the results to return
	 * @param perPage
	 *            Number of items per page
	 * @return The route
	 */
	public StravaRoute[] listAthleteRoutes(final Integer id, final Integer page, final Integer perPage) {
		return this.routeAPI.listAthleteRoutes(id, page, perPage);
	}

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @param page
	 *            Page of the results to return
	 * @param perPage
	 *            Number of items per page
	 * @return The route
	 */
	public StravaAPIFuture<StravaRoute[]> listAthleteRoutesAsync(final Integer id, final Integer page, final Integer perPage) {
		final StravaAPIFuture<StravaRoute[]> future = new StravaAPIFuture<>();
		this.routeAPI.listAthleteRoutes(id, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who both the identified athlete and the authenticated athlete are following
	 * @throws NotFoundException
	 *             If the athlete with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthletesBothFollowing(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthletesBothFollowing(athleteId, page, perPage);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the athlete with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAthletesBothFollowingAsync(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.athleteAPI.listAthletesBothFollowing(athleteId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param before
	 *            Time in seconds since the UNIX epoch date - only return activities commenced before this time
	 * @param after
	 *            Time in seconds since the UNIX epoch date - only return activities commenced after this time
	 * @param page
	 *            Page number to return
	 * @param perPage
	 *            Number of results to return
	 * @return Array of activities
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listAuthenticatedAthleteActivities(final Integer before, final Integer after, final Integer page, final Integer perPage) throws BadRequestException {
		return this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage);
	}

	/**
	 * @param before
	 *            Time in seconds since the UNIX epoch date - only return activities commenced before this time
	 * @param after
	 *            Time in seconds since the UNIX epoch date - only return activities commenced after this time
	 * @param page
	 *            Page number to return
	 * @param perPage
	 *            Number of results to return
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listAuthenticatedAthleteActivitiesAsync(final Integer before, final Integer after, final Integer page, final Integer perPage) throws BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage, callback(future));
		return future;
	}

	/**
	 * @return Array of clubs that the authenticated athlete belongs to
	 * @see javastrava.api.ClubAPI#listAuthenticatedAthleteClubs()
	 */
	public StravaClub[] listAuthenticatedAthleteClubs() {
		return this.clubAPI.listAuthenticatedAthleteClubs();
	}

	/**
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.ClubAPI#listAuthenticatedAthleteClubs(javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClub[]> listAuthenticatedAthleteClubsAsync() {
		final StravaAPIFuture<StravaClub[]> future = new StravaAPIFuture<StravaClub[]>();
		this.clubAPI.listAuthenticatedAthleteClubs(callback(future));
		return future;
	}

	/**
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who the authenticated athlete is following
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAuthenticatedAthleteFriends(final Integer page, final Integer perPage) throws BadRequestException {
		return this.athleteAPI.listAuthenticatedAthleteFriends(page, perPage);
	}

	/**
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.AthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAuthenticatedAthleteFriendsAsync(final Integer page, final Integer perPage) throws BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.athleteAPI.listAuthenticatedAthleteFriends(page, perPage, callback(future));
		return future;
	}

	/**
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegment[] listAuthenticatedAthleteStarredSegments(final Integer page, final Integer perPage) throws BadRequestException {
		return this.segmentAPI.listAuthenticatedAthleteStarredSegments(page, perPage);
	}

	/**
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegment[]> listAuthenticatedAthleteStarredSegmentsAsync(final Integer page, final Integer perPage) throws BadRequestException {
		final StravaAPIFuture<StravaSegment[]> future = new StravaAPIFuture<StravaSegment[]>();
		this.segmentAPI.listAuthenticatedAthleteStarredSegments(page, perPage, callback(future));
		return future;
	}

	/**
	 * <p>
	 * List the administrators of a club
	 * </p>
	 *
	 * @param clubId
	 *            Identifier of the club whose admins should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of {@link StravaAthlete}s who are admins of the club
	 */
	public StravaAthlete[] listClubAdmins(final Integer clubId, final Integer page, final Integer perPage) {
		return this.clubAPI.listClubAdmins(clubId, page, perPage);
	}

	/**
	 * <p>
	 * List the administrators of a club
	 * </p>
	 *
	 * @param clubId
	 *            Identifier of the club whose admins should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return The {@link StravaAPIFuture} on which to call complete when ready; this will return the array of {@link StravaAthlete}s.
	 */
	public StravaAPIFuture<StravaAthlete[]> listClubAdminsAsync(final Integer clubId, final Integer page, final Integer perPage) {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.clubAPI.listClubAdmins(clubId, page, perPage, callback(future));
		return future;

	}

	/**
	 * @param clubId
	 *            The club id for which announcements should be returned
	 * @return Array of {@link StravaClubAnnouncement} for the given {@link StravaClub club}
	 * @throws NotFoundException
	 *             If the club with the given id does not exist
	 * @see ClubAPI#listClubAnnouncements(Integer)
	 */
	public StravaClubAnnouncement[] listClubAnnouncements(final Integer clubId) throws NotFoundException {
		return this.clubAPI.listClubAnnouncements(clubId);
	}

	/**
	 * @param clubId
	 *            The club id for which announcements should be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id does not exist
	 * @see javastrava.api.ClubAPI#listClubAnnouncements(java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClubAnnouncement[]> listClubAnnouncementsAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClubAnnouncement[]> future = new StravaAPIFuture<StravaClubAnnouncement[]>();
		this.clubAPI.listClubAnnouncements(clubId, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            Unique id of the club whose events should be listed
	 * @return Array of summary events
	 */
	public StravaClubEvent[] listClubGroupEvents(final Integer clubId) {
		return this.clubAPI.listClubGroupEvents(clubId);
	}

	/**
	 * @param clubId
	 *            Unique id of the club whose events should be listed
	 * @return The {@link CompletableFuture} on which to call future.complete() when the API call returns.
	 */
	public StravaAPIFuture<StravaClubEvent[]> listClubGroupEventsAsync(final Integer clubId) {
		final StravaAPIFuture<StravaClubEvent[]> future = new StravaAPIFuture<>();
		this.clubAPI.listClubGroupEvents(clubId, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            CLub identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who are members of the identified club
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listClubMembers(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.clubAPI.listClubMembers(clubId, page, perPage);
	}

	/**
	 * @param clubId
	 *            CLub identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listClubMembersAsync(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.clubAPI.listClubMembers(clubId, page, perPage, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Retrieve summary information about athletes joined a specific group event, or the upcoming occurrence for recurring events.
	 * </p>
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * <p>
	 * Returns an array of athletes summary representations with athletes who the authenticated athlete is following first.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event for which athletes should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of athletes who have joined the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the event is private??
	 */
	public StravaAthlete[] listEventJoinedAthletes(Integer id, final Integer page, final Integer perPage) throws NotFoundException, UnauthorizedException {
		return this.clubGroupEventAPI.listEventJoinedAthletes(id, page, perPage);
	}

	/**
	 * <p>
	 * Retrieve summary information about athletes joined a specific group event, or the upcoming occurrence for recurring events.
	 * </p>
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * <p>
	 * Returns an array of athletes summary representations with athletes who the authenticated athlete is following first.
	 * </p>
	 *
	 * @param id
	 *            The identifier of the event for which athletes should be listed
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Callback which can be used to get the array of athletes who have joined the event
	 * @throws NotFoundException
	 *             If the event does not exist
	 * @throws UnauthorizedException
	 *             If the event is private??
	 */
	public StravaAPIFuture<StravaAthlete[]> listEventJoinedAthletesAsync(Integer id, final Integer page, final Integer perPage) throws NotFoundException, UnauthorizedException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<>();
		this.clubGroupEventAPI.listEventJoinedAthletes(id, page, perPage, callback(future));
		return future;
	}

	/**
	 *
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return List of Strava activities belonging to friends of the authenticated athlete
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listFriendsActivities(final Integer page, final Integer perPage) throws BadRequestException {
		return this.activityAPI.listFriendsActivities(page, perPage);
	}

	/**
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listFriendsActivities(Integer, Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listFriendsActivitiesAsync(final Integer page, final Integer perPage) throws BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.activityAPI.listFriendsActivities(page, perPage, callback(future));
		return future;
	}

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return Array of challenges that the athlete has joined
	 */
	public StravaChallenge[] listJoinedChallenges() {
		return this.challengeAPI.listJoinedChallenges();
	}

	/**
	 * List the challenges the athlete has joined.
	 *
	 * @return Array of challenges that the athlete has joined
	 */
	public StravaAPIFuture<StravaChallenge[]> listJoinedChallengesAsync() {
		final StravaAPIFuture<StravaChallenge[]> future = new StravaAPIFuture<>();
		this.challengeAPI.listJoinedChallenges(callback(future));
		return future;
	}

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return List of running races as summary representations
	 */
	public StravaRunningRace[] listRaces(Integer year) {
		return this.runningRaceAPI.listRaces(year);
	}

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return Future with list of running races as summary representations
	 */
	public StravaAPIFuture<StravaRunningRace[]> listRacesAsync(Integer year) {
		final StravaAPIFuture<StravaRunningRace[]> future = new StravaAPIFuture<StravaRunningRace[]>();
		this.runningRaceAPI.listRaces(year, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200 will be returned)
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRecentClubActivities(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.clubAPI.listRecentClubActivities(clubId, page, perPage);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param page
	 *            Page number to be returned (default is 1)
	 * @param perPage
	 *            Page size to be returned (default is 50)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listRecentClubActivitiesAsync(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.clubAPI.listRecentClubActivities(clubId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return Array of activities that Strava judges was 'done with' the activity identified by the id
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listRelatedActivities(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRelatedActivities(final Long activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listRelatedActivities(activityId, page, perPage);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param page
	 *            Page number to be returned
	 * @param perPage
	 *            Page size to be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.ActivityAPI#listRelatedActivities(java.lang.Long, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listRelatedActivitiesAsync(final Long activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.activityAPI.listRelatedActivities(activityId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param start
	 *            (Optional) ISO 8601 formatted date time
	 * @param end
	 *            (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentEffort[] listSegmentEfforts(final Integer segmentId, final Integer athleteId, final String start, final String end, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
		return this.segmentAPI.listSegmentEfforts(segmentId, athleteId, start, end, page, perPage);
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param start
	 *            (Optional) ISO 8601 formatted date time
	 * @param end
	 *            (Optional) ISO 8601 formatted date time
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentEffort[]> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final String start, final String end, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegmentEffort[]> future = new StravaAPIFuture<StravaSegmentEffort[]>();
		this.segmentAPI.listSegmentEfforts(segmentId, athleteId, start, end, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            The id of the athlete whose starred segments are to be retrieved
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	public StravaSegment[] listStarredSegments(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.segmentAPI.listStarredSegments(athleteId, page, perPage);
	}

	/**
	 * @param athleteId
	 *            The id of the athlete whose starred segments are to be retrieved
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.SegmentAPI#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegment[]> listStarredSegmentsAsync(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegment[]> future = new StravaAPIFuture<StravaSegment[]>();
		this.segmentAPI.listStarredSegments(athleteId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @return Returns an array of summary representations of the application's current subscriptions
	 * @see javastrava.api.WebhookAPI#listSubscriptions(java.lang.Integer, java.lang.String)
	 */
	public StravaEventSubscription[] listSubscriptions(final Integer clientId, final String clientSecret) {
		return this.webhookAPI.listSubscriptions(clientId, clientSecret);
	}

	/**
	 * @param clientId
	 *            Application's id, as obtained during registration with Strava
	 * @param clientSecret
	 *            Application's secret, as obtained during Strava registration
	 * @return Returns an array of summary representations of the application's current subscriptions
	 * @see javastrava.api.WebhookAPI#listSubscriptions(java.lang.Integer, java.lang.String)
	 */
	public StravaAPIFuture<StravaEventSubscription[]> listSubscriptionsAsync(final Integer clientId, final String clientSecret) {
		final StravaAPIFuture<StravaEventSubscription[]> future = new StravaAPIFuture<StravaEventSubscription[]>();
		this.webhookAPI.listSubscriptions(clientId, clientSecret, callback(future));
		return future;
	}

	/**
	 * @param bounds
	 *            Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType
	 *            (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory
	 *            (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory
	 *            (Optional) Maximum climb category for which rides should be returned
	 * @return A response full of slightly weird-looking segments
	 * @see javastrava.api.SegmentAPI#segmentExplore(java.lang.String, javastrava.model.reference.StravaSegmentExplorerActivityType, javastrava.model.reference.StravaClimbCategory,
	 *      javastrava.model.reference.StravaClimbCategory)
	 */
	public StravaSegmentExplorerResponse segmentExplore(final String bounds, final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCategory,
			final StravaClimbCategory maxCategory) {
		return this.segmentAPI.segmentExplore(bounds, activityType, minCategory, maxCategory);
	}

	/**
	 * @param bounds
	 *            Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType
	 *            (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory
	 *            (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory
	 *            (Optional) Maximum climb category for which rides should be returned
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.SegmentAPI#segmentExplore(java.lang.String, javastrava.model.reference.StravaSegmentExplorerActivityType, javastrava.model.reference.StravaClimbCategory,
	 *      javastrava.model.reference.StravaClimbCategory, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentExplorerResponse> segmentExploreAsync(final String bounds, final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCategory,
			final StravaClimbCategory maxCategory) {
		final StravaAPIFuture<StravaSegmentExplorerResponse> future = new StravaAPIFuture<StravaSegmentExplorerResponse>();
		this.segmentAPI.segmentExplore(bounds, activityType, minCategory, maxCategory, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Star or unstar a segment
	 * </p>
	 *
	 * @param segmentId
	 *            Identifier of the segment to be starred or unstarred
	 * @param starred
	 *            <code>true</code> if the segment is to be starred, <code>false</code> if to be unstarred
	 * @return Detailed representation of the segment
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If required parameters are not provided
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 */
	public StravaSegment starSegment(final Integer segmentId, final Boolean starred) throws NotFoundException, BadRequestException, UnauthorizedException {

		// Workaround for #162 - this will throw the required UnauthorizedException if the segment is private
		final StravaSegment segment = this.segmentAPI.getSegment(segmentId);

		// If the segment is already in the correct state, then we are done
		if ((starred != null) && starred.equals(segment.getStarred())) {
			return segment;
		}

		// Star the segment
		return this.segmentAPI.starSegment(segmentId, starred);
	}

	/**
	 * <p>
	 * Star or unstar a segment
	 * </p>
	 *
	 * @param segmentId
	 *            Identifier of the segment to be starred or unstarred
	 * @param starred
	 *            <code>true</code> if the segment is to be starred, <code>false</code> if to be unstarred
	 * @return Detailed representation of the segment
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If required parameters are not provided
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 */
	public StravaAPIFuture<StravaSegment> starSegmentAsync(final Integer segmentId, final Boolean starred) throws NotFoundException, BadRequestException, UnauthorizedException {
		final StravaAPIFuture<StravaSegment> future = new StravaAPIFuture<StravaSegment>();
		this.segmentAPI.starSegment(segmentId, starred, callback(future));
		return future;
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Statistics summary for the identified athlete
	 * @throws NotFoundException
	 *             If the identified athlete doesn't exist
	 * @see javastrava.api.AthleteAPI#getStatistics(java.lang.Integer)
	 */
	public StravaStatistics statistics(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.getStatistics(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the identified athlete doesn't exist
	 * @see javastrava.api.AthleteAPI#getStatistics(java.lang.Integer, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStatistics> statisticsAsync(final Integer athleteId) throws NotFoundException {
		final StravaAPIFuture<StravaStatistics> future = new StravaAPIFuture<StravaStatistics>();
		this.athleteAPI.getStatistics(athleteId, callback(future));
		return future;
	}

	/**
	 * <p>
	 * Update an activity that already exists on Strava
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @param activity
	 *            Update representation
	 * @return The activity as updated on Strava
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.api.ActivityAPI#updateActivity(java.lang.Long, javastrava.model.StravaActivityUpdate)
	 */
	public StravaActivity updateActivity(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException {
		return this.activityAPI.updateActivity(activityId, activity);
	}

	/**
	 * <p>
	 * Update an activity that already exists on Strava
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @param activity
	 *            Update representation
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.api.ActivityAPI#updateActivity(java.lang.Long, javastrava.model.StravaActivityUpdate, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> updateActivityAsync(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException {
		final StravaAPIFuture<StravaActivity> future = new StravaAPIFuture<StravaActivity>();
		this.activityAPI.updateActivity(activityId, activity, callback(future));
		return future;
	}

	/**
	 * @param city
	 *            City the athlete is from
	 * @param state
	 *            State/county/territory/canton/departement/whatever the athlete is from
	 * @param country
	 *            Country the athlete is from
	 * @param sex
	 *            Gender of the athlete
	 * @param weight
	 *            Weight in kilograms
	 * @return Athlete details as updated on Strava
	 * @see javastrava.api.AthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, javastrava.model.reference.StravaGender, java.lang.Float)
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.athleteAPI.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @param city
	 *            City the athlete is from
	 * @param state
	 *            State/county/territory/canton/departement/whatever the athlete is from
	 * @param country
	 *            Country the athlete is from
	 * @param sex
	 *            Gender of the athlete
	 * @param weight
	 *            Weight in kilograms
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.AthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, javastrava.model.reference.StravaGender, java.lang.Float,
	 *      javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete> updateAuthenticatedAthleteAsync(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		final StravaAPIFuture<StravaAthlete> future = new StravaAPIFuture<StravaAthlete>();
		this.athleteAPI.updateAuthenticatedAthlete(city, state, country, sex, weight, callback(future));
		return future;
	}

	/**
	 * Upload an activity
	 *
	 * @param activityType
	 *            Type of activity being uploaded
	 * @param name
	 *            Name of the activity
	 * @param description
	 *            (Optional) Description of the activity
	 * @param _private
	 *            (Optional) Whether the activity should be flagged as private
	 * @param trainer
	 *            (Optional) If <code>true</code> then the activity was done on a stationary trainer
	 * @param commute
	 *            (Optional) If <code>true</code> then the activity was a commute
	 * @param dataType
	 *            Type of data file being uploaded
	 * @param externalId
	 *            (Optional) External identifier generated by your application which Strava will later use as a reference when you're checking upload status
	 * @param file
	 *            The file to be uploaded!
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @throws BadRequestException
	 *             If required elements of the call are missing
	 * @see javastrava.api.UploadAPI#upload(javastrava.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean,
	 *      java.lang.String, java.lang.String, retrofit.mime.TypedFile)
	 */
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer, final Boolean commute,
			final String dataType, final String externalId, final TypedFile file) throws BadRequestException {
		return this.uploadAPI.upload(activityType, name, description, _private, trainer, commute, dataType, externalId, file);
	}

	/**
	 * Upload an activity
	 *
	 * @param activityType
	 *            Type of activity being uploaded
	 * @param name
	 *            Name of the activity
	 * @param description
	 *            (Optional) Description of the activity
	 * @param _private
	 *            (Optional) Whether the activity should be flagged as private
	 * @param trainer
	 *            (Optional) If <code>true</code> then the activity was done on a stationary trainer
	 * @param commute
	 *            (Optional) If <code>true</code> then the activity was a commute
	 * @param dataType
	 *            Type of data file being uploaded
	 * @param externalId
	 *            (Optional) External identifier generated by your application which Strava will later use as a reference when you're checking upload status
	 * @param file
	 *            The file to be uploaded!
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If required elements of the call are missing
	 * @see javastrava.api.UploadAPI#upload(javastrava.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean,
	 *      java.lang.String, java.lang.String, retrofit.mime.TypedFile, javastrava.api.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaUploadResponse> uploadAsync(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer,
			final Boolean commute, final String dataType, final String externalId, final TypedFile file) throws BadRequestException {
		final StravaAPIFuture<StravaUploadResponse> future = new StravaAPIFuture<StravaUploadResponse>();
		this.uploadAPI.upload(activityType, name, description, _private, trainer, commute, dataType, externalId, file, callback(future));
		return future;
	}

}

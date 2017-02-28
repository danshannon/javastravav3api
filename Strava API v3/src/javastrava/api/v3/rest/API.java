package javastrava.api.v3.rest;

import java.util.MissingResourceException;
import java.util.concurrent.CompletableFuture;

import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaAthleteZones;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubAnnouncement;
import javastrava.api.v3.model.StravaClubEvent;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.model.StravaComment;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.model.StravaLap;
import javastrava.api.v3.model.StravaPhoto;
import javastrava.api.v3.model.StravaResponse;
import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaSegmentExplorerResponse;
import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.StravaUploadResponse;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaAgeGroup;
import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.model.webhook.StravaEventSubscription;
import javastrava.api.v3.model.webhook.reference.StravaSubscriptionAspectType;
import javastrava.api.v3.model.webhook.reference.StravaSubscriptionObjectType;
import javastrava.api.v3.rest.async.StravaAPICallback;
import javastrava.api.v3.rest.async.StravaAPIFuture;
import javastrava.api.v3.rest.util.RetrofitClientResponseInterceptor;
import javastrava.api.v3.rest.util.RetrofitErrorHandler;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.JsonUtilImpl;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.mime.TypedFile;

/**
 * <p>
 * Provides a static method {@link #instance(Class, Token)} which constructs a standard retrofit service with all the required
 * options.
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
			authorisationAPI = new RestAdapter.Builder().setClient(new RetrofitClientResponseInterceptor())
					.setConverter(new GsonConverter(new JsonUtilImpl().getGson()))
					.setLogLevel(API.logLevel(AuthorisationServiceImpl.class)).setEndpoint(StravaConfig.AUTH_ENDPOINT)
					.setErrorHandler(new RetrofitErrorHandler()).build().create(AuthorisationAPI.class);
		}
		return authorisationAPI;
	}

	/**
	 * Generates a callback for the API, based on a {@link CompletableFuture}. {@link CompletableFuture#complete(Object)} will be
	 * called when the asynchronous call to the API is complete
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
	 * API instance for access to activity data
	 */
	private final ActivityAPI		activityAPI;
	/**
	 * API instance for access to athlete data
	 */
	private final AthleteAPI		athleteAPI;
	/**
	 * API instance for access to club data
	 */
	private final ClubAPI			clubAPI;
	/**
	 * API instance for access to gear data
	 */
	private final GearAPI			gearAPI;
	/**
	 * API instance for access to segment data
	 */
	private final SegmentAPI		segmentAPI;
	/**
	 * API instance for access to segment effort data
	 */
	private final SegmentEffortAPI	effortAPI;
	/**
	 * API instance for access to streams data
	 */
	private final StreamAPI			streamAPI;

	/**
	 * API instance for access to token deauthorisation
	 */
	private final TokenAPI tokenAPI;

	/**
	 * API instance for access to activity upload functionality
	 */
	private final UploadAPI uploadAPI;

	/**
	 * API instance for access to webhook subscriptions
	 */
	private final WebhookAPI webhookAPI;

	/**
	 * Construct an API instance with a given token
	 *
	 * @param token
	 *            The access token to be used with calls to the API
	 */
	public API(final Token token) {
		this.activityAPI = API.instance(ActivityAPI.class, token);
		this.athleteAPI = API.instance(AthleteAPI.class, token);
		this.clubAPI = API.instance(ClubAPI.class, token);
		this.gearAPI = API.instance(GearAPI.class, token);
		this.segmentAPI = API.instance(SegmentAPI.class, token);
		this.effortAPI = API.instance(SegmentEffortAPI.class, token);
		this.streamAPI = API.instance(StreamAPI.class, token);
		this.tokenAPI = API.instance(TokenAPI.class, token);
		this.uploadAPI = API.instance(UploadAPI.class, token);
		this.webhookAPI = API.instance(WebhookAPI.class, token);
	}

	/**
	 * @param id
	 *            The upload id as given back in the response to
	 *            {@link #upload(StravaActivityType, String, String, Boolean, Boolean, Boolean, String, String, TypedFile)}
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @see javastrava.api.v3.rest.UploadAPI#checkUploadStatus(java.lang.Long)
	 */
	public StravaUploadResponse checkUploadStatus(final Long id) {
		return this.uploadAPI.checkUploadStatus(id);
	}

	/**
	 * @param uploadId
	 *            The upload id as given back in the response to
	 *            {@link #upload(StravaActivityType, String, String, Boolean, Boolean, Boolean, String, String, TypedFile)}
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.UploadAPI#checkUploadStatus(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#createComment(java.lang.Long, java.lang.String)
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
	public StravaAPIFuture<StravaComment> createCommentAsync(final Long activityId, final String text)
			throws BadRequestException, NotFoundException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#createManualActivity(javastrava.api.v3.model.StravaActivity)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#createManualActivity(javastrava.api.v3.model.StravaActivity,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
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
	 *            (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook
	 *            endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken
	 *            The token's value will be included in the GET callback request when verifying the endpoint
	 * @return Details of the event subscription
	 */
	public StravaEventSubscription createSubscription(final Integer clientId, final String clientSecret,
			final StravaSubscriptionObjectType objectType, final StravaSubscriptionAspectType aspectType, final String callbackURL,
			final String verifyToken) {
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
	 *            (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook
	 *            endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken
	 *            The token's value will be included in the GET callback request when verifying the endpoint
	 * @return Details of the event subscription
	 */
	public StravaAPIFuture<StravaEventSubscription> createSubscriptionAsync(final Integer clientId, final String clientSecret,
			final StravaSubscriptionObjectType objectType, final StravaSubscriptionAspectType aspectType, final String callbackURL,
			final String verifyToken) {
		final StravaAPIFuture<StravaEventSubscription> future = new StravaAPIFuture<StravaEventSubscription>();
		this.webhookAPI.createSubscription(clientId, clientSecret, objectType, aspectType, callbackURL, verifyToken,
				callback(future));
		return future;
	}

	/**
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 * @see javastrava.api.v3.rest.TokenAPI#deauthoriseToken(java.lang.String)
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
	 * @see javastrava.api.v3.rest.TokenAPI#deauthorise(java.lang.String, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteActivity(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteActivity(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteComment(java.lang.Long, java.lang.Integer)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteComment(java.lang.Long, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaResponse> deleteCommentAsync(final Long activityId, final Integer commentId)
			throws NotFoundException {
		final StravaAPIFuture<StravaResponse> future = new StravaAPIFuture<StravaResponse>();
		this.activityAPI.deleteComment(activityId, commentId, callback(future));
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
	 * @see javastrava.api.v3.rest.WebhookAPI#deleteSubscription(java.lang.Integer, java.lang.Integer, java.lang.String)
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
	 * @see javastrava.api.v3.rest.WebhookAPI#deleteSubscription(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public StravaAPIFuture<StravaResponse> deleteSubscriptionAsync(final Integer subscriptionId, final Integer clientId,
			final String clientSecret) {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#getActivity(java.lang.Long, java.lang.Boolean)
	 */
	public StravaActivity getActivity(final Long id, final Boolean includeAllEfforts) throws NotFoundException {
		return this.activityAPI.getActivity(id, includeAllEfforts);
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
	 * @see javastrava.api.v3.rest.ActivityAPI#getActivity(java.lang.Long, java.lang.Boolean,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> getActivityAsync(final Long id, final Boolean includeAllEfforts)
			throws NotFoundException {
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
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getActivityStreams(java.lang.Long, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getActivityStreams(final Long activityId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getActivityStreams(activityId, types, resolution, seriesType);
	}

	/**
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getActivityStreams(java.lang.Long, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getActivityStreamsAsync(final Long activityId, final String types,
			final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#getAthlete(java.lang.Integer)
	 */
	public StravaAthlete getAthlete(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.getAthlete(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the athlete doesn't exist
	 * @see javastrava.api.v3.rest.AthleteAPI#getAthlete(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete> getAthleteAsync(final Integer athleteId) throws NotFoundException {
		final StravaAPIFuture<StravaAthlete> future = new StravaAPIFuture<StravaAthlete>();
		this.athleteAPI.getAthlete(athleteId, callback(future));
		return future;
	}

	/**
	 * @return Full details of the authenticated athlete
	 * @see javastrava.api.v3.rest.AthleteAPI#getAuthenticatedAthlete()
	 */
	public StravaAthlete getAuthenticatedAthlete() {
		return this.athleteAPI.getAuthenticatedAthlete();
	}

	/**
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.AthleteAPI#getAuthenticatedAthlete(javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @param clubId
	 *            Club identifier
	 * @return Club details
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#getClub(java.lang.Integer)
	 */
	public StravaClub getClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.getClub(clubId);
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
	 * @return The {@link StravaAPIFuture} on which to call complete when ready; this will return the array of
	 *         {@link StravaAthlete}s.
	 */
	public StravaAPIFuture<StravaAthlete[]> getClubAdminsAsync(final Integer clubId, final Integer page, final Integer perPage) {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.clubAPI.listClubAdmins(clubId, page, perPage, callback(future));
		return future;

	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#getClub(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClub> getClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClub> future = new StravaAPIFuture<StravaClub>();
		this.clubAPI.getClub(clubId, callback(future));
		return future;
	}

	/**
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getEffortStreams(java.lang.Long, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getEffortStreams(final Long segmentEffortId, final String types,
			final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType);
	}

	/**
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getEffortStreams(java.lang.Long, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getEffortStreamsAsync(final Long segmentEffortId, final String types,
			final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaStream[]> future = new StravaAPIFuture<StravaStream[]>();
		this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType, callback(future));
		return future;
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 * @see javastrava.api.v3.rest.GearAPI#getGear(java.lang.String)
	 */
	public StravaGear getGear(final String gearId) throws NotFoundException {
		return this.gearAPI.getGear(gearId);
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 * @see javastrava.api.v3.rest.GearAPI#getGear(java.lang.String, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaGear> getGearAsync(final String gearId) throws NotFoundException {
		final StravaAPIFuture<StravaGear> future = new StravaAPIFuture<StravaGear>();
		this.gearAPI.getGear(gearId, callback(future));
		return future;
	}

	/**
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return The Segment
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegment(java.lang.Integer)
	 */
	public StravaSegment getSegment(final Integer segmentId) throws NotFoundException {
		return this.segmentAPI.getSegment(segmentId);
	}

	/**
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegment(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.SegmentEffortAPI#getSegmentEffort(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.SegmentEffortAPI#getSegmentEffort(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 *            (Optional) Number of context entries to return either side of the authenticated athlete (default is 2, maximum is
	 *            15)
	 * @return A Strava leaderboard
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegmentLeaderboard(java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaGender, javastrava.api.v3.model.reference.StravaAgeGroup,
	 *      javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender,
			final StravaAgeGroup ageGroup, final StravaWeightClass weightClass, final Boolean following, final Integer clubId,
			final StravaLeaderboardDateRange dateRange, final Integer page, final Integer perPage, final Integer contextEntries)
			throws NotFoundException, BadRequestException {
		return this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page,
				perPage, contextEntries);
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
	 *            (Optional) Number of context entries to return either side of the authenticated athlete (default is 2, maximum is
	 *            15)
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegmentLeaderboard(java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaGender, javastrava.api.v3.model.reference.StravaAgeGroup,
	 *      javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender,
			final StravaAgeGroup ageGroup, final StravaWeightClass weightClass, final Boolean following, final Integer clubId,
			final StravaLeaderboardDateRange dateRange, final Integer page, final Integer perPage, final Integer contextEntries)
			throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegmentLeaderboard> future = new StravaAPIFuture<StravaSegmentLeaderboard>();
		this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page, perPage,
				contextEntries, callback(future));
		return future;
	}

	/**
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getSegmentStreams(java.lang.Integer, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getSegmentStreams(final Integer segmentId, final String types,
			final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType);
	}

	/**
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points,
	 *            streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index
	 *            the streams if the stream is being reduced
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.StreamAPI#getSegmentStreams(java.lang.Integer, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStream[]> getSegmentStreamsAsync(final Integer segmentId, final String types,
			final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaStream[]> future = new StravaAPIFuture<StravaStream[]>();
		this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType, callback(future));
		return future;
	}

	/**
	 * @param activityId
	 *            Activity to be kudoed
	 * @return Strava response
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @see javastrava.api.v3.rest.ActivityAPI#giveKudos(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#giveKudos(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaResponse> giveKudosAsync(final Long activityId) throws NotFoundException {
		final StravaAPIFuture<StravaResponse> future = new StravaAPIFuture<StravaResponse>();
		this.activityAPI.giveKudos(activityId, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to join
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#joinClub(java.lang.Integer)
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
	 * @see javastrava.api.v3.rest.ClubAPI#joinClub(Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClubMembershipResponse> joinClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClubMembershipResponse> future = new StravaAPIFuture<StravaClubMembershipResponse>();
		this.clubAPI.joinClub(clubId, callback(future));
		return future;
	}

	/**
	 * @param clubId
	 *            The club the authenticated athlete wishes to leave
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException
	 *             If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#leaveClub(java.lang.Integer)
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
	 * @see javastrava.api.v3.rest.ClubAPI#leaveClub(Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaClubMembershipResponse> leaveClubAsync(final Integer clubId) throws NotFoundException {
		final StravaAPIFuture<StravaClubMembershipResponse> future = new StravaAPIFuture<StravaClubMembershipResponse>();
		this.clubAPI.leaveClub(clubId, callback(future));
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityComments(java.lang.Long, java.lang.Boolean, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public StravaComment[] listActivityComments(final Long activityId, final Boolean markdown, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityComments(java.lang.Long, java.lang.Boolean, java.lang.Integer,
	 *      java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaComment[]> listActivityCommentsAsync(final Long activityId, final Boolean markdown,
			final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityKudoers(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listActivityKudoers(final Long activityId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityKudoers(Long, Integer, Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listActivityKudoersAsync(final Long activityId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityLaps(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityLaps(Long, StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityPhotos(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityPhotos(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityZones(java.lang.Long)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityZones(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthleteFriends(final Integer athleteId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAthleteFriendsAsync(final Integer athleteId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentEffort[] listAthleteKOMs(final Integer athleteId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentEffort[]> listAthleteKOMsAsync(final Integer athleteId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaSegmentEffort[]> future = new StravaAPIFuture<StravaSegmentEffort[]>();
		this.athleteAPI.listAthleteKOMs(athleteId, page, perPage, callback(future));
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthletesBothFollowing(final Integer athleteId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAthletesBothFollowingAsync(final Integer athleteId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.athleteAPI.listAthletesBothFollowing(athleteId, page, perPage, callback(future));
		return future;
	}

	/**
	 * @param before
	 *            Time in milliseconds since the UNIX epoch date - only return activities commenced before this time
	 * @param after
	 *            Time in milliseconds since the UNIX epoch date - only return activities commenced after this time
	 * @param page
	 *            Page number to return
	 * @param perPage
	 *            Number of results to return
	 * @return Array of activities
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listAuthenticatedAthleteActivities(final Integer before, final Integer after, final Integer page,
			final Integer perPage) throws BadRequestException {
		return this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage);
	}

	/**
	 * @param before
	 *            Time in milliseconds since the UNIX epoch date - only return activities commenced before this time
	 * @param after
	 *            Time in milliseconds since the UNIX epoch date - only return activities commenced after this time
	 * @param page
	 *            Page number to return
	 * @param perPage
	 *            Number of results to return
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listAuthenticatedAthleteActivitiesAsync(final Integer before, final Integer after,
			final Integer page, final Integer perPage) throws BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage, callback(future));
		return future;
	}

	/**
	 * @return Array of clubs that the authenticated athlete belongs to
	 * @see javastrava.api.v3.rest.ClubAPI#listAuthenticatedAthleteClubs()
	 */
	public StravaClub[] listAuthenticatedAthleteClubs() {
		return this.clubAPI.listAuthenticatedAthleteClubs();
	}

	/**
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.ClubAPI#listAuthenticatedAthleteClubs(javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
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
	 * @see javastrava.api.v3.rest.AthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listAuthenticatedAthleteFriendsAsync(final Integer page, final Integer perPage)
			throws BadRequestException {
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
	 * @see javastrava.api.v3.rest.SegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegment[] listAuthenticatedAthleteStarredSegments(final Integer page, final Integer perPage)
			throws BadRequestException {
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
	 * @see javastrava.api.v3.rest.SegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegment[]> listAuthenticatedAthleteStarredSegmentsAsync(final Integer page, final Integer perPage)
			throws BadRequestException {
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
	 * @see javastrava.api.v3.rest.ClubAPI#listClubAnnouncements(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
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
	 * @see javastrava.api.v3.rest.ClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listClubMembers(final Integer clubId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete[]> listClubMembersAsync(final Integer clubId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
		final StravaAPIFuture<StravaAthlete[]> future = new StravaAPIFuture<StravaAthlete[]>();
		this.clubAPI.listClubMembers(clubId, page, perPage, callback(future));
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listFriendsActivities(java.lang.Integer, java.lang.Integer)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listFriendsActivities(Integer, Integer, StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listFriendsActivitiesAsync(final Integer page, final Integer perPage)
			throws BadRequestException {
		final StravaAPIFuture<StravaActivity[]> future = new StravaAPIFuture<StravaActivity[]>();
		this.activityAPI.listFriendsActivities(page, perPage, callback(future));
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
	 * @see javastrava.api.v3.rest.ClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRecentClubActivities(final Integer clubId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listRecentClubActivitiesAsync(final Integer clubId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listRelatedActivities(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRelatedActivities(final Long activityId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.ActivityAPI#listRelatedActivities(java.lang.Long, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity[]> listRelatedActivitiesAsync(final Long activityId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations}
	 *         sorted by start_date_local ascending or by elapsed_time if an athlete_id is provided.
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentEffort[] listSegmentEfforts(final Integer segmentId, final Integer athleteId, final String start,
			final String end, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.SegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentEffort[]> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId,
			final String start, final String end, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	public StravaSegment[] listStarredSegments(final Integer athleteId, final Integer page, final Integer perPage)
			throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.SegmentAPI#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegment[]> listStarredSegmentsAsync(final Integer athleteId, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
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
	 * @see javastrava.api.v3.rest.WebhookAPI#listSubscriptions(java.lang.Integer, java.lang.String)
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
	 * @see javastrava.api.v3.rest.WebhookAPI#listSubscriptions(java.lang.Integer, java.lang.String)
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
	 * @see javastrava.api.v3.rest.SegmentAPI#segmentExplore(java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType,
	 *      javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.model.reference.StravaClimbCategory)
	 */
	public StravaSegmentExplorerResponse segmentExplore(final String bounds, final StravaSegmentExplorerActivityType activityType,
			final StravaClimbCategory minCategory, final StravaClimbCategory maxCategory) {
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
	 * @see javastrava.api.v3.rest.SegmentAPI#segmentExplore(java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType,
	 *      javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.model.reference.StravaClimbCategory,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaSegmentExplorerResponse> segmentExploreAsync(final String bounds,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCategory,
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
	public StravaSegment starSegment(final Integer segmentId, final Boolean starred)
			throws NotFoundException, BadRequestException, UnauthorizedException {
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
	public StravaAPIFuture<StravaSegment> starSegmentAsync(final Integer segmentId, final Boolean starred)
			throws NotFoundException, BadRequestException, UnauthorizedException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#statistics(java.lang.Integer)
	 */
	public StravaStatistics statistics(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.statistics(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the identified athlete doesn't exist
	 * @see javastrava.api.v3.rest.AthleteAPI#statistics(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaStatistics> statisticsAsync(final Integer athleteId) throws NotFoundException {
		final StravaAPIFuture<StravaStatistics> future = new StravaAPIFuture<StravaStatistics>();
		this.athleteAPI.statistics(athleteId, callback(future));
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
	 * @see javastrava.api.v3.rest.ActivityAPI#updateActivity(java.lang.Long, javastrava.api.v3.model.StravaActivityUpdate)
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
	 * @see javastrava.api.v3.rest.ActivityAPI#updateActivity(java.lang.Long, javastrava.api.v3.model.StravaActivityUpdate,
	 *      javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaActivity> updateActivityAsync(final Long activityId, final StravaActivityUpdate activity)
			throws NotFoundException {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country,
			final StravaGender sex, final Float weight) {
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
	 * @see javastrava.api.v3.rest.AthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaAthlete> updateAuthenticatedAthleteAsync(final String city, final String state,
			final String country, final StravaGender sex, final Float weight) {
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
	 *            (Optional) External identifier generated by your application which Strava will later use as a reference when
	 *            you're checking upload status
	 * @param file
	 *            The file to be uploaded!
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @throws BadRequestException
	 *             If required elements of the call are missing
	 * @see javastrava.api.v3.rest.UploadAPI#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String,
	 *      java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String,
	 *      retrofit.mime.TypedFile)
	 */
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description,
			final Boolean _private, final Boolean trainer, final Boolean commute, final String dataType, final String externalId,
			final TypedFile file) throws BadRequestException {
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
	 *            (Optional) External identifier generated by your application which Strava will later use as a reference when
	 *            you're checking upload status
	 * @param file
	 *            The file to be uploaded!
	 * @return future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException
	 *             If required elements of the call are missing
	 * @see javastrava.api.v3.rest.UploadAPI#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String,
	 *      java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String,
	 *      retrofit.mime.TypedFile, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public StravaAPIFuture<StravaUploadResponse> uploadAsync(final StravaActivityType activityType, final String name,
			final String description, final Boolean _private, final Boolean trainer, final Boolean commute, final String dataType,
			final String externalId, final TypedFile file) throws BadRequestException {
		final StravaAPIFuture<StravaUploadResponse> future = new StravaAPIFuture<StravaUploadResponse>();
		this.uploadAPI.upload(activityType, name, description, _private, trainer, commute, dataType, externalId, file,
				callback(future));
		return future;
	}

}

/**
 *
 */
package javastrava.api.v3.rest.async;

import java.util.MissingResourceException;
import java.util.concurrent.CompletableFuture;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubAnnouncement;
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
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.rest.API;
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
import retrofit.mime.TypedFile;

/**
 * <p>
 * Asynchronous version of the API
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class AsyncAPI {
	/**
	 * Generates a callback for the API, based on a {@link CompletableFuture}. {@link CompletableFuture#complete(Object)} will be called when the asynchronous call to the API is complete
	 * @param completableFuture The future
	 * @return The callback
	 */
	private static <T> StravaAPICallback<T> callback(final CompletableFuture<T> completableFuture) {
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
	 *            Class of asynchronous API interface to be instantiated (one of the Async*API.java interfaces)
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
		.setRequestInterceptor(request -> request.addHeader(StravaConfig.string("strava.authorization_header_name"), token.getTokenType() + " " + token.getToken())) //$NON-NLS-1$ //$NON-NLS-2$
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
	 * Asynchronous API definition for activities
	 */
	private final AsyncActivityAPI activityAPI;

	/**
	 * Asynchronous API definition for athletes
	 */
	private final AsyncAthleteAPI athleteAPI;

	/**
	 * Asynchronous API definition for clubs
	 */
	private final AsyncClubAPI clubAPI;

	/**
	 * Asynchronous API definition for gear
	 */
	private final AsyncGearAPI gearAPI;

	/**
	 * Asynchronous API definition for segments
	 */
	private final AsyncSegmentAPI segmentAPI;

	/**
	 * Asynchronous API definition for segment efforts
	 */
	private final AsyncSegmentEffortAPI effortAPI;

	/**
	 * Asynchronous API definition for streams
	 */
	private final AsyncStreamAPI streamAPI;

	/**
	 * Asynchronous API definition for token management
	 */
	private final AsyncTokenAPI tokenAPI;

	/**
	 * Asynchronous API definition for uploads
	 */
	private final AsyncUploadAPI uploadAPI;

	/**
	 * @param token The access token from Strava to be used to access the API
	 */
	public AsyncAPI(final Token token) {
		this.activityAPI = instance(AsyncActivityAPI.class, token);
		this.athleteAPI = instance(AsyncAthleteAPI.class, token);
		this.clubAPI = instance(AsyncClubAPI.class, token);
		this.effortAPI = instance(AsyncSegmentEffortAPI.class, token);
		this.gearAPI = instance(AsyncGearAPI.class, token);
		this.segmentAPI = instance(AsyncSegmentAPI.class, token);
		this.streamAPI = instance(AsyncStreamAPI.class, token);
		this.tokenAPI = instance(AsyncTokenAPI.class, token);
		this.uploadAPI = instance(AsyncUploadAPI.class, token);
	}

	/**
	 * @param uploadId The upload id as given back in the response to {@link #upload(StravaActivityType, String, String, Boolean, Boolean, String, String, TypedFile, CompletableFuture)}
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.async.AsyncUploadAPI#checkUploadStatus(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void checkUploadStatus(final Integer uploadId, final CompletableFuture<StravaUploadResponse> future) {
		this.uploadAPI.checkUploadStatus(uploadId, callback(future));
	}

	/**
	 * @param activityId Activity identifier
	 * @param text Text of the comment to create
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity does not exist
	 * @throws BadRequestException If the comment text is null or the empty string
	 */
	public void createComment(final Integer activityId, final String text, final CompletableFuture<StravaComment> future) throws BadRequestException, NotFoundException {
		this.activityAPI.createComment(activityId, text, callback(future));
	}

	/**
	 * @param activity The activity to be created on Strava
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If the activity is malformed and can't be uploaded
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#createManualActivity(javastrava.api.v3.model.StravaActivity, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void createManualActivity(final StravaActivity activity, final CompletableFuture<StravaActivity> future) throws BadRequestException {
		this.activityAPI.createManualActivity(activity, callback(future));
	}

	/**
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 * @see javastrava.api.v3.rest.async.AsyncTokenAPI#deauthorise(java.lang.String, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void deauthoriseToken(final String accessToken, final CompletableFuture<TokenResponse> future) throws UnauthorizedException {
		this.tokenAPI.deauthorise(accessToken, callback(future));
	}

	/**
	 * <p>
	 * Delete an activity on Strava
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the identified activity does not exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#deleteActivity(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void deleteActivity(final Integer id, final CompletableFuture<StravaActivity> future) throws NotFoundException {
		this.activityAPI.deleteActivity(id, callback(future));
	}

	/**
	 * @param activityId Id of the activity the comment was posted to
	 * @param commentId Id of the comment
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the comment does not exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#deleteComment(java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void deleteComment(final Integer activityId, final Integer commentId, final CompletableFuture<StravaResponse> future) throws NotFoundException {
		this.activityAPI.deleteComment(activityId, commentId, callback(future));
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
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#getActivity(java.lang.Integer, java.lang.Boolean, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getActivity(final Integer id, final Boolean includeAllEfforts, final CompletableFuture<StravaActivity> future) throws NotFoundException {
		this.activityAPI.getActivity(id, includeAllEfforts, callback(future));
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
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.async.AsyncStreamAPI#getActivityStreams(java.lang.Integer, java.lang.String, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getActivityStreams(final Integer activityId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final CompletableFuture<StravaStream[]> future) throws UnauthorizedException, NotFoundException, BadRequestException {
		this.streamAPI.getActivityStreams(activityId, types, resolution, seriesType, callback(future));
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the athlete doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#getAthlete(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getAthlete(final Integer athleteId, final CompletableFuture<StravaAthlete> future) throws NotFoundException {
		this.athleteAPI.getAthlete(athleteId, callback(future));
	}

	/**
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#getAuthenticatedAthlete(javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getAuthenticatedAthlete(final CompletableFuture<StravaAthlete> future) {
		this.athleteAPI.getAuthenticatedAthlete(callback(future));
	}

	/**
	 * @param clubId Club identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#getClub(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getClub(final Integer clubId, final CompletableFuture<StravaClub> future) throws NotFoundException {
		this.clubAPI.getClub(clubId, callback(future));
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
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.async.AsyncStreamAPI#getEffortStreams(java.lang.Long, java.lang.String, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getEffortStreams(final Long segmentEffortId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final CompletableFuture<StravaStream[]> future) throws UnauthorizedException, NotFoundException, BadRequestException {
		this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType, callback(future));
	}

	/**
	 * @param gearId Gear identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the gear with the given id doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncGearAPI#getGear(java.lang.String, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getGear(final String gearId, final CompletableFuture<StravaGear> future) throws NotFoundException {
		this.gearAPI.getGear(gearId, callback(future));
	}

	/**
	 * @param segmentId The unique identifier of the segment
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the segment with the given id does not exist
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#getSegment(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getSegment(final Integer segmentId, final CompletableFuture<StravaSegment> future) throws NotFoundException {
		this.segmentAPI.getSegment(segmentId, callback(future));
	}

	/**
	 * @param segmentEffortId Effort identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the effort with the given id doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncSegmentEffortAPI#getSegmentEffort(java.lang.Long, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getSegmentEffort(final Long segmentEffortId, final CompletableFuture<StravaSegmentEffort> future) throws NotFoundException {
		this.effortAPI.getSegmentEffort(segmentEffortId, callback(future));
	}

	/**
	 * @param segmentId Segment identifier
	 * @param gender (Optional) Gender to filter the leaderboard by
	 * @param ageGroup (Optional) Age group to filter the leaderboard by
	 * @param weightClass (Optional) Weight class to filter the leaderboard by
	 * @param following (Optional) If <code>true</code> then filter leaderboard by athletes the authenticated athlete is following
	 * @param clubId (Optional) Club to filter the leaderboard by
	 * @param dateRange (Optional) Date range (this year, this month etc.) to filter the leaderboard by
	 * @param page (Optional) Page number to return (default is 1)
	 * @param perPage (Optional) Page size to return (default is 50)
	 * @param contextEntries (Optional) Number of context entries to return either side of the authenticated athlete (default is 2, maximum is 15)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the segment with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#getSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender, javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass, final Boolean following,
			final Integer clubId, final StravaLeaderboardDateRange dateRange, final Integer page, final Integer perPage, final Integer contextEntries,
			final CompletableFuture<StravaSegmentLeaderboard> future) throws NotFoundException, BadRequestException {
		this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page, perPage, contextEntries, callback(future));
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
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 * @see javastrava.api.v3.rest.async.AsyncStreamAPI#getSegmentStreams(java.lang.Integer, java.lang.String, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void getSegmentStreams(final Integer segmentId, final String types, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final CompletableFuture<StravaStream[]> future) throws UnauthorizedException, NotFoundException, BadRequestException {
		this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType, callback(future));
	}

	/**
	 * @param activityId Activity to be kudoed
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException if the activity does not exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#giveKudos(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void giveKudos(final Integer activityId, final CompletableFuture<StravaResponse> future) throws NotFoundException {
		this.activityAPI.giveKudos(activityId, callback(future));
	}

	/**
	 * @param clubId The club the authenticated athlete wishes to join
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#joinClub(Integer, StravaAPICallback)
	 */
	public void joinClub(final Integer clubId, final CompletableFuture<StravaClubMembershipResponse> future) throws NotFoundException {
		this.clubAPI.joinClub(clubId, callback(future));
	}

	/**
	 * @param clubId The club the authenticated athlete wishes to leave
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#leaveClub(Integer, StravaAPICallback)
	 */
	public void leaveClub(final Integer clubId, final CompletableFuture<StravaClubMembershipResponse> future) throws NotFoundException {
		this.clubAPI.leaveClub(clubId, callback(future));
	}

	/**
	 * @param activityId Activity identifier
	 * @param markdown Whether or not to return comments including markdown
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listActivityComments(final Integer activityId, final Boolean markdown, final Integer page, final Integer perPage, final CompletableFuture<StravaComment[]> future)
			throws NotFoundException, BadRequestException {
		this.activityAPI.listActivityComments(activityId, markdown, page, perPage, callback(future));
	}

	/**
	 * @param activityId Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listActivityKudoers(Integer, Integer, Integer, StravaAPICallback)
	 */
	public void listActivityKudoers(final Integer activityId, final Integer page, final Integer perPage, final CompletableFuture<StravaAthlete[]> future) throws NotFoundException,
	BadRequestException {
		this.activityAPI.listActivityKudoers(activityId, page, perPage, callback(future));
	}

	/**
	 * @param activityId The activity identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listActivityLaps(Integer, StravaAPICallback)
	 */
	public void listActivityLaps(final Integer activityId, final CompletableFuture<StravaLap[]> future) throws NotFoundException {
		this.activityAPI.listActivityLaps(activityId, callback(future));
	}

	/**
	 * @param activityId Activity identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listActivityPhotos(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listActivityPhotos(final Integer activityId, final CompletableFuture<StravaPhoto[]> future) throws NotFoundException {
		this.activityAPI.listActivityPhotos(activityId, callback(future));
	}

	/**
	 * @param activityId The activity identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listActivityZones(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listActivityZones(final Integer activityId, final CompletableFuture<StravaActivityZone[]> future) throws NotFoundException {
		this.activityAPI.listActivityZones(activityId, callback(future));
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAthleteFriends(final Integer athleteId, final Integer page, final Integer perPage, final CompletableFuture<StravaAthlete[]> future) throws NotFoundException,
	BadRequestException {
		this.athleteAPI.listAthleteFriends(athleteId, page, perPage, callback(future));
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the athlete doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAthleteKOMs(final Integer athleteId, final Integer page, final Integer perPage, final CompletableFuture<StravaSegmentEffort[]> future) throws NotFoundException,
	BadRequestException {
		this.athleteAPI.listAthleteKOMs(athleteId, page, perPage, callback(future));
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAthletesBothFollowing(final Integer athleteId, final Integer page, final Integer perPage, final CompletableFuture<StravaAthlete[]> future)
			throws NotFoundException, BadRequestException {
		this.athleteAPI.listAthletesBothFollowing(athleteId, page, perPage, callback(future));
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
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAuthenticatedAthleteActivities(final Integer before, final Integer after, final Integer page, final Integer perPage, final CompletableFuture<StravaActivity[]> future)
			throws BadRequestException {
		this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage, callback(future));
	}

	/**
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#listAuthenticatedAthleteClubs(javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAuthenticatedAthleteClubs(final CompletableFuture<StravaClub[]> future) {
		this.clubAPI.listAuthenticatedAthleteClubs(callback(future));
	}

	/**
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAuthenticatedAthleteFriends(final Integer page, final Integer perPage, final CompletableFuture<StravaAthlete[]> future) throws BadRequestException {
		this.athleteAPI.listAuthenticatedAthleteFriends(page, perPage, callback(future));
	}

	/**
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listAuthenticatedAthleteStarredSegments(final Integer page, final Integer perPage, final CompletableFuture<StravaSegment[]> future) throws BadRequestException {
		this.segmentAPI.listAuthenticatedAthleteStarredSegments(page, perPage, callback(future));
	}

	/**
	 * @param clubId The club id for which announcements should be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id does not exist
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#listClubAnnouncements(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listClubAnnouncements(final Integer clubId, final CompletableFuture<StravaClubAnnouncement[]> future) throws NotFoundException {
		this.clubAPI.listClubAnnouncements(clubId, callback(future));
	}

	/**
	 * @param clubId CLub identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException  If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listClubMembers(final Integer clubId, final Integer page, final Integer perPage, final CompletableFuture<StravaAthlete[]> future) throws NotFoundException,
	BadRequestException {
		this.clubAPI.listClubMembers(clubId, page, perPage, callback(future));
	}

	/**
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listFriendsActivities(Integer, Integer, StravaAPICallback)
	 */
	public void listFriendsActivities(final Integer page, final Integer perPage, final CompletableFuture<StravaActivity[]> future) throws BadRequestException {
		this.activityAPI.listFriendsActivities(page, perPage, callback(future));
	}

	/**
	 * @param clubId Club identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listRecentClubActivities(final Integer clubId, final Integer page, final Integer perPage, final CompletableFuture<StravaActivity[]> future) throws NotFoundException,
	BadRequestException {
		this.clubAPI.listRecentClubActivities(clubId, page, perPage, callback(future));
	}

	/**
	 * @param activityId Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#listRelatedActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listRelatedActivities(final Integer activityId, final Integer page, final Integer perPage, final CompletableFuture<StravaActivity[]> future)
			throws NotFoundException, BadRequestException {
		this.activityAPI.listRelatedActivities(activityId, page, perPage, callback(future));
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
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the segment with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listSegmentEfforts(final Integer segmentId, final Integer athleteId, final String start, final String end, final Integer page, final Integer perPage,
			final CompletableFuture<StravaSegmentEffort[]> future) throws NotFoundException, BadRequestException {
		this.segmentAPI.listSegmentEfforts(segmentId, athleteId, start, end, page, perPage, callback(future));
	}

	/**
	 * @param athleteId The id of the athlete whose starred segments are to be retrieved
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the segment with the given id does not exist
	 * @throws UnauthorizedException If there is a security or privacy violation
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void listStarredSegments(final Integer athleteId, final Integer page, final Integer perPage, final CompletableFuture<StravaSegment[]> future)
			throws NotFoundException, BadRequestException {
		this.segmentAPI.listStarredSegments(athleteId, page, perPage, callback(future));
	}

	/**
	 * @param bounds Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory (Optional) Maximum climb category for which rides should be returned
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.async.AsyncSegmentAPI#segmentExplore(java.lang.String, javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType, javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void segmentExplore(final String bounds, final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCategory, final StravaClimbCategory maxCategory,
			final CompletableFuture<StravaSegmentExplorerResponse> future) {
		this.segmentAPI.segmentExplore(bounds, activityType, minCategory, maxCategory, callback(future));
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException If the identified athlete doesn't exist
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#statistics(java.lang.Integer, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void statistics(final Integer athleteId, final CompletableFuture<StravaStatistics> future) throws NotFoundException {
		this.athleteAPI.statistics(athleteId, callback(future));
	}

	/**
	 * <p>
	 * Update an activity that already exists on Strava
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @param activity
	 *            Update representation
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.api.v3.rest.async.AsyncActivityAPI#updateActivity(java.lang.Integer, javastrava.api.v3.model.StravaActivityUpdate, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void updateActivity(final Integer id, final StravaActivityUpdate activity, final CompletableFuture<StravaActivity> future) throws NotFoundException {
		this.activityAPI.updateActivity(id, activity, callback(future));
	}

	/**
	 * @param city City the athlete is from
	 * @param state State/county/territory/canton/departement/whatever the athlete is from
	 * @param country Country the athlete is from
	 * @param sex Gender of the athlete
	 * @param weight Weight in kilograms
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @see javastrava.api.v3.rest.async.AsyncAthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, javastrava.api.v3.model.reference.StravaGender, java.lang.Float, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight, final CompletableFuture<StravaAthlete> future) {
		this.athleteAPI.updateAuthenticatedAthlete(city, state, country, sex, weight, callback(future));
	}

	/**
	 * @param activityType Type of activity being uploaded
	 * @param name Name of the activity
	 * @param description (Optional) Description of the activity
	 * @param _private (Optional) Whether the activity should be flagged as private
	 * @param trainer (Optional) If <code>true</code> then the activity was done on a stationary trainer
	 * @param dataType Type of data file being uploaded
	 * @param externalId (Optional) External identifier generated by your application which Strava will later use as a reference when you're checking upload status
	 * @param file The file to be uploaded!
	 * @param future The {@link CompletableFuture} on which to call future.complete() when the API returns
	 * @throws BadRequestException If required elements of the call are missing
	 * @see javastrava.api.v3.rest.async.AsyncUploadAPI#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, retrofit.mime.TypedFile, javastrava.api.v3.rest.async.StravaAPICallback)
	 */
	public void upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer, final String dataType, final String externalId,
			final TypedFile file, final CompletableFuture<StravaUploadResponse> future) throws BadRequestException {
		this.uploadAPI.upload(activityType, name, description, _private, trainer, dataType, externalId, file, callback(future));
	}


}

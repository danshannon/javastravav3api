package javastrava.api.v3.rest;

import java.util.MissingResourceException;

import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
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
import javastrava.api.v3.rest.util.RetrofitClientResponseInterceptor;
import javastrava.api.v3.rest.util.RetrofitErrorHandler;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.JsonUtilImpl;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;
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
	}

	private static AuthorisationAPI authorisationAPI;

	private final ActivityAPI activityAPI;
	private final AthleteAPI athleteAPI;
	private final ClubAPI clubAPI;
	private final GearAPI gearAPI;
	private final SegmentAPI segmentAPI;
	private final SegmentEffortAPI effortAPI;
	private final StreamAPI streamAPI;
	private final TokenAPI tokenAPI;
	private final UploadAPI uploadAPI;

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
		.setRequestInterceptor(new RequestInterceptor() {
			@Override
			public void intercept(final RequestFacade request) {
				request.addHeader(StravaConfig.string("strava.authorization_header_name"), token.getTokenType() + " " + token.getToken()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		})
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
	 * <p>
	 * Get an instance of the authorisation API (cached)
	 * </p>
	 *
	 * @return Instance of the authorisation API
	 */
	public static AuthorisationAPI authorisationInstance() {
		if (authorisationAPI == null) {
			authorisationAPI = new RestAdapter.Builder().setClient(new RetrofitClientResponseInterceptor())
					.setConverter(new GsonConverter(new JsonUtilImpl().getGson())).setLogLevel(API.logLevel(AuthorisationServiceImpl.class))
					.setEndpoint(StravaConfig.AUTH_ENDPOINT).setErrorHandler(new RetrofitErrorHandler()).build().create(AuthorisationAPI.class);
		}
		return authorisationAPI;
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
	 * @see javastrava.api.v3.rest.ActivityAPI#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	public StravaActivity getActivity(final Integer id, final Boolean includeAllEfforts) throws NotFoundException {
		return this.activityAPI.getActivity(id, includeAllEfforts);
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
	 * <p>
	 * Update an activity that already exists on Strava
	 * </p>
	 *
	 * @param id
	 *            Activity identifier
	 * @param activity
	 *            Update representation
	 * @return The activity as updated on Strava
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.api.v3.rest.ActivityAPI#updateActivity(java.lang.Integer, javastrava.api.v3.model.StravaActivityUpdate)
	 */
	public StravaActivity updateActivity(final Integer id, final StravaActivityUpdate activity) throws NotFoundException {
		return this.activityAPI.updateActivity(id, activity);
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
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteActivity(java.lang.Integer)
	 */
	public StravaActivity deleteActivity(final Integer id) throws NotFoundException {
		return this.activityAPI.deleteActivity(id);
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
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listAuthenticatedAthleteActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listAuthenticatedAthleteActivities(final Integer before, final Integer after, final Integer page, final Integer perPage) throws BadRequestException {
		return this.activityAPI.listAuthenticatedAthleteActivities(before, after, page, perPage);
	}

	/**
	 *
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return List of Strava activities belonging to friends of the authenticated athlete
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listFriendsActivities(java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listFriendsActivities(final Integer page, final Integer perPage) throws BadRequestException {
		return this.activityAPI.listFriendsActivities(page, perPage);
	}

	/**
	 * @param activityId The activity identifier
	 * @return Array of activity zones for the activity
	 * @throws NotFoundException If the activity doesn't exist
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityZones(java.lang.Integer)
	 */
	public StravaActivityZone[] listActivityZones(final Integer activityId) throws NotFoundException {
		return this.activityAPI.listActivityZones(activityId);
	}

	/**
	 * @param id The activity identifier
	 * @return Array of laps belonging to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityLaps(java.lang.Integer)
	 */
	public StravaLap[] listActivityLaps(final Integer id) throws NotFoundException {
		return this.activityAPI.listActivityLaps(id);
	}

	/**
	 * @param activityId Activity identifier
	 * @param markdown Whether or not to return comments including markdown
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return Array of comments belonging to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityComments(java.lang.Integer, java.lang.Boolean, java.lang.Integer, java.lang.Integer)
	 */
	public StravaComment[] listActivityComments(final Integer activityId, final Boolean markdown, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listActivityComments(activityId, markdown, page, perPage);
	}

	/**
	 * @param activityId Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return Array of athletes who have kudoed the activity
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityKudoers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listActivityKudoers(final Integer activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listActivityKudoers(activityId, page, perPage);
	}

	/**
	 * @param activityId Activity identifier
	 * @return Array of photos attached to the activity
	 * @throws NotFoundException If the activity doesn't exist
	 * @see javastrava.api.v3.rest.ActivityAPI#listActivityPhotos(java.lang.Integer)
	 */
	public StravaPhoto[] listActivityPhotos(final Integer activityId) throws NotFoundException {
		return this.activityAPI.listActivityPhotos(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @param page Page number to be returned
	 * @param perPage Page size to be returned
	 * @return Array of activities that Strava judges was 'done with' the activity identified by the id
	 * @throws NotFoundException If the activity doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ActivityAPI#listRelatedActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRelatedActivities(final Integer activityId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.activityAPI.listRelatedActivities(activityId, page, perPage);
	}

	/**
	 * @param activityId Activity identifier
	 * @param text Text of the comment to create
	 * @return The comment as posted
	 * @throws NotFoundException If the activity does not exist
	 * @throws BadRequestException If the comment text is null or the empty string
	 * @see javastrava.api.v3.rest.ActivityAPI#createComment(java.lang.Integer, java.lang.String)
	 */
	public StravaComment createComment(final Integer activityId, final String text) throws BadRequestException, NotFoundException {
		return this.activityAPI.createComment(activityId, text);
	}

	/**
	 * @param activityId Id of the activity the comment was posted to
	 * @param commentId Id of the comment
	 * @return Strava response
	 * @throws NotFoundException If the comment does not exist
	 * @see javastrava.api.v3.rest.ActivityAPI#deleteComment(java.lang.Integer, java.lang.Integer)
	 */
	public StravaResponse deleteComment(final Integer activityId, final Integer commentId) throws NotFoundException {
		return this.activityAPI.deleteComment(activityId, commentId);
	}

	/**
	 * @param activityId Activity to be kudoed
	 * @return Strava response
	 * @throws NotFoundException if the activity does not exist
	 * @see javastrava.api.v3.rest.ActivityAPI#giveKudos(java.lang.Integer)
	 */
	public StravaResponse giveKudos(final Integer activityId) throws NotFoundException {
		return this.activityAPI.giveKudos(activityId);
	}

	/**
	 * @return Full details of the authenticated athlete
	 * @see javastrava.api.v3.rest.AthleteAPI#getAuthenticatedAthlete()
	 */
	public StravaAthlete getAuthenticatedAthlete() {
		return this.athleteAPI.getAuthenticatedAthlete();
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return Details of the athlete, will be somewhat anonymised if the athlete is private
	 * @throws NotFoundException If the athlete doesn't exist
	 * @see javastrava.api.v3.rest.AthleteAPI#getAthlete(java.lang.Integer)
	 */
	public StravaAthlete getAthlete(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.getAthlete(athleteId);
	}

	/**
	 * @param city City the athlete is from
	 * @param state State/county/territory/canton/departement/whatever the athlete is from
	 * @param country Country the athlete is from
	 * @param sex Gender of the athlete
	 * @param weight Weight in kilograms
	 * @return Athlete details as updated on Strava
	 * @see javastrava.api.v3.rest.AthleteAPI#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.athleteAPI.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of segment efforts which represent the athlete's KOM/QOM's
	 * @throws NotFoundException If the athlete doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteKOMs(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentEffort[] listAthleteKOMs(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthleteKOMs(athleteId, page, perPage);
	}

	/**
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the authenticated athlete is following
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.AthleteAPI#listAuthenticatedAthleteFriends(java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAuthenticatedAthleteFriends(final Integer page, final Integer perPage) throws BadRequestException {
		return this.athleteAPI.listAuthenticatedAthleteFriends(page, perPage);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who the identified athlete is following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthleteFriends(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthleteFriends(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthleteFriends(athleteId, page, perPage);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who both the identified athlete and the authenticated athlete are following
	 * @throws NotFoundException If the athlete with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.AthleteAPI#listAthletesBothFollowing(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listAthletesBothFollowing(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.athleteAPI.listAthletesBothFollowing(athleteId, page, perPage);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return Statistics summary for the identified athlete
	 * @throws NotFoundException If the identified athlete doesn't exist
	 * @see javastrava.api.v3.rest.AthleteAPI#statistics(java.lang.Integer)
	 */
	public StravaStatistics statistics(final Integer athleteId) throws NotFoundException {
		return this.athleteAPI.statistics(athleteId);
	}

	/**
	 * @param clubId Club identifier
	 * @return Club details
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#getClub(java.lang.Integer)
	 */
	public StravaClub getClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.getClub(clubId);
	}

	/**
	 * @return Array of clubs that the authenticated athlete belongs to
	 * @see javastrava.api.v3.rest.ClubAPI#listAuthenticatedAthleteClubs()
	 */
	public StravaClub[] listAuthenticatedAthleteClubs() {
		return this.clubAPI.listAuthenticatedAthleteClubs();
	}

	/**
	 * @param clubId CLub identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of athletes who are members of the identified club
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException  If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ClubAPI#listClubMembers(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaAthlete[] listClubMembers(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.clubAPI.listClubMembers(clubId, page, perPage);
	}

	/**
	 * @param clubId Club identifier
	 * @param page Page number to be returned (default is 1)
	 * @param perPage Page size to be returned (default is 50)
	 * @return Array of activities recently done by club members (maximum 200 will be returned)
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.ClubAPI#listRecentClubActivities(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaActivity[] listRecentClubActivities(final Integer clubId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.clubAPI.listRecentClubActivities(clubId, page, perPage);
	}

	/**
	 * @param clubId The club the authenticated athlete wishes to join
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#join(java.lang.Integer)
	 */
	public StravaClubMembershipResponse joinClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.join(clubId);
	}

	/**
	 * @param clubId The club the authenticated athlete wishes to leave
	 * @return Membership response indicating success/failure
	 * @throws NotFoundException If the club with the given id doesn't exist
	 * @see javastrava.api.v3.rest.ClubAPI#leave(java.lang.Integer)
	 */
	public StravaClubMembershipResponse leaveClub(final Integer clubId) throws NotFoundException {
		return this.clubAPI.leave(clubId);
	}

	/**
	 * @param gearId Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException If the gear with the given id doesn't exist
	 * @see javastrava.api.v3.rest.GearAPI#getGear(java.lang.String)
	 */
	public StravaGear getGear(final String gearId) throws NotFoundException {
		return this.gearAPI.getGear(gearId);
	}

	/**
	 * @param segmentId The unique identifier of the segment
	 * @return The Segment
	 * @throws NotFoundException If the segment with the given id does not exist
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegment(java.lang.Integer)
	 */
	public StravaSegment getSegment(final Integer segmentId) throws NotFoundException {
		return this.segmentAPI.getSegment(segmentId);
	}

	/**
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegment[] listAuthenticatedAthleteStarredSegments(final Integer page, final Integer perPage) throws BadRequestException {
		return this.segmentAPI.listAuthenticatedAthleteStarredSegments(page, perPage);
	}

	/**
	 * @param athleteId The id of the athlete whose starred segments are to be retrieved
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws NotFoundException If the segment with the given id does not exist
	 * @throws UnauthorizedException If there is a security or privacy violation
	 * @throws BadRequestException If the paging instructions are invalid
	 */
	public StravaSegment[] listStarredSegments(final Integer athleteId, final Integer page, final Integer perPage) throws NotFoundException, BadRequestException {
		return this.segmentAPI.listStarredSegments(athleteId, page, perPage);
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
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local
	 *         ascending or by elapsed_time if an athlete_id is provided.
	 * @param page (optional) Page number to be returned
	 * @param perPage (optional) Number of entries to return per page
	 * @throws NotFoundException If the segment with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public StravaSegmentEffort[] listSegmentEfforts(final Integer segmentId, final Integer athleteId, final String start, final String end, final Integer page,
			final Integer perPage) throws NotFoundException, BadRequestException {
		return this.segmentAPI.listSegmentEfforts(segmentId, athleteId, start, end, page, perPage);
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
	 * @return A Strava leaderboard
	 * @throws NotFoundException If the segment with the given id doesn't exist
	 * @throws BadRequestException If the paging instructions are invalid
	 * @see javastrava.api.v3.rest.SegmentAPI#getSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender,
	 *      javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Integer page,
			final Integer perPage, final Integer contextEntries) throws NotFoundException, BadRequestException {
		return this.segmentAPI.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, page, perPage, contextEntries);
	}

	/**
	 * @param bounds Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory (Optional) Maximum climb category for which rides should be returned
	 * @return A response full of slightly weird-looking segments
	 * @see javastrava.api.v3.rest.SegmentAPI#segmentExplore(java.lang.String, javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType,
	 *      javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.model.reference.StravaClimbCategory)
	 */
	public StravaSegmentExplorerResponse segmentExplore(final String bounds, final StravaSegmentExplorerActivityType activityType,
			final StravaClimbCategory minCategory, final StravaClimbCategory maxCategory) {
		return this.segmentAPI.segmentExplore(bounds, activityType, minCategory, maxCategory);
	}

	/**
	 * @param segmentEffortId Effort identifier
	 * @return Effort details
	 * @throws NotFoundException If the effort with the given id doesn't exist
	 * @see javastrava.api.v3.rest.SegmentEffortAPI#getSegmentEffort(java.lang.Long)
	 */
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId) throws NotFoundException {
		return this.effortAPI.getSegmentEffort(segmentEffortId);
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
	 * @see javastrava.api.v3.rest.StreamAPI#getActivityStreams(java.lang.Integer, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getActivityStreams(final Integer activityId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getActivityStreams(activityId, types, resolution, seriesType);
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
	 * @see javastrava.api.v3.rest.StreamAPI#getEffortStreams(java.lang.Long, java.lang.String, javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getEffortStreams(final Long segmentEffortId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getEffortStreams(segmentEffortId, types, resolution, seriesType);
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
	 *      javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	public StravaStream[] getSegmentStreams(final Integer segmentId, final String types, final StravaStreamResolutionType resolution,
			final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException {
		return this.streamAPI.getSegmentStreams(segmentId, types, resolution, seriesType);
	}

	/**
	 * @param accessToken
	 *            The access token for which the application is revoking its access.
	 * @return Responds with the access token submitted with the request.
	 * @throws UnauthorizedException
	 *             if the token is not allowed to be deauthorised
	 * @see javastrava.api.v3.rest.TokenAPI#deauthorise(java.lang.String)
	 */
	public TokenResponse deauthorise(final String accessToken) throws UnauthorizedException {
		return this.tokenAPI.deauthorise(accessToken);
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
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @throws BadRequestException If required elements of the call are missing
	 * @see javastrava.api.v3.rest.UploadAPI#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean,
	 *      java.lang.Boolean, java.lang.String, java.lang.String, retrofit.mime.TypedFile)
	 */
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private,
			final Boolean trainer, final String dataType, final String externalId, final TypedFile file) throws BadRequestException {
		return this.uploadAPI.upload(activityType, name, description, _private, trainer, dataType, externalId, file);
	}

	/**
	 * @param uploadId The upload id as given back in the response to {@link #upload(StravaActivityType, String, String, Boolean, Boolean, String, String, TypedFile)}
	 * @return Upload response containing the upload id and activity id and current status of the upload
	 * @see javastrava.api.v3.rest.UploadAPI#checkUploadStatus(java.lang.Integer)
	 */
	public StravaUploadResponse checkUploadStatus(final Integer uploadId) {
		return this.uploadAPI.checkUploadStatus(uploadId);
	}

}

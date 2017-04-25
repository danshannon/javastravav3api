package javastrava.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.auth.TokenService;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
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
import javastrava.model.StravaMapPoint;
import javastrava.model.StravaPhoto;
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
import javastrava.model.reference.StravaStreamType;
import javastrava.model.reference.StravaWeightClass;
import javastrava.model.webhook.StravaEventSubscription;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.service.impl.StravaServiceImpl;
import javastrava.util.Paging;

/**
 * <p>
 * Convenience class for simplicity of access to the services. Provides a facade which delegates all service methods to elsewhere
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class Strava implements ActivityService, AthleteService, ChallengeService, ClubService, ClubGroupEventService, GearService, RouteService, RunningRaceService, SegmentEffortService,
		SegmentService, StreamService, TokenService, UploadService, WebhookService {
	/**
	 * Daily request rate limit (default is 30,000)
	 */
	public static int RATE_LIMIT_DAILY = StravaConfig.integer("strava.rate_limit_daily").intValue(); //$NON-NLS-1$

	/**
	 * Request rate limit every 15 minutes (default is 600)
	 */
	public static int RATE_LIMIT_CURRENT = StravaConfig.integer("strava.rate_limit").intValue(); //$NON-NLS-1$

	/**
	 * Current request rate over the last day
	 */
	public static int REQUEST_RATE_DAILY = 0;

	/**
	 * Current request rate over the last 15 minutes
	 */
	public static int REQUEST_RATE_CURRENT = 0;

	/**
	 * Calculates the percentage of the per-15-minute request limit that has been used, issues a warning if required
	 *
	 * @return Percentage used.
	 */
	public static float requestRateCurrentPercentage() {
		final float percent = (RATE_LIMIT_CURRENT == 0 ? 0 : (100 * new Float(REQUEST_RATE_CURRENT).floatValue()) / new Float(RATE_LIMIT_CURRENT).floatValue());
		if (percent > 100) {
			StravaServiceImpl.log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimit"), Integer.valueOf(REQUEST_RATE_CURRENT), //$NON-NLS-1$
					Integer.valueOf(RATE_LIMIT_CURRENT), Float.valueOf(percent)));
		} else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			StravaServiceImpl.log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimit"), Integer.valueOf(REQUEST_RATE_CURRENT), //$NON-NLS-1$
					Integer.valueOf(RATE_LIMIT_CURRENT), Float.valueOf(percent)));
		}
		return percent;
	}

	/**
	 * Calculates the percentage of the daily request limit that has been used, issues a warning if required
	 *
	 * @return Percentage used.
	 */
	public static float requestRateDailyPercentage() {
		final float percent = (Strava.RATE_LIMIT_DAILY == 0 ? 0 : (100 * new Float(REQUEST_RATE_DAILY).floatValue()) / new Float(Strava.RATE_LIMIT_DAILY).floatValue());
		if (percent > 100) {
			StravaServiceImpl.log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimitDaily"), Integer.valueOf(REQUEST_RATE_DAILY), //$NON-NLS-1$
					Integer.valueOf(Strava.RATE_LIMIT_DAILY), Float.valueOf(percent)));
		} else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			StravaServiceImpl.log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimitDaily"), Integer.valueOf(REQUEST_RATE_DAILY), //$NON-NLS-1$
					Integer.valueOf(Strava.RATE_LIMIT_DAILY), Float.valueOf(percent)));
		}
		return percent;
	}

	/**
	 * Instance used for access to activity data
	 */
	private ActivityService activityService;

	/**
	 * instance used for access to athlete data
	 */
	private AthleteService athleteService;

	/**
	 * instance used for access to challenge data
	 */
	private ChallengeService challengeService;

	/**
	 * instance used for access to club data
	 */
	private ClubService clubService;

	/**
	 * instance used for access to club group event data
	 */
	private ClubGroupEventService clubGroupEventService;

	/**
	 * instance used for access to gear data
	 */
	private GearService gearService;

	/**
	 * instance used for access to route data
	 */
	private RouteService routeService;

	/**
	 * Instance used for access to running race data
	 */
	private RunningRaceService runningRaceService;

	/**
	 * instance used for access to segment effort data
	 */
	private SegmentEffortService segmentEffortService;

	/**
	 * instance used for access to segment data
	 */
	private SegmentService segmentService;

	/**
	 * instance used for access to streams data
	 */
	private StreamService streamService;

	/**
	 * instance used for token deauthorisation
	 */
	private TokenService tokenService;

	/**
	 * instance used for activity upload functionality
	 */
	private UploadService uploadService;

	/**
	 * instance used for management of webhook subscriptions
	 */
	private WebhookService webhookService;

	/**
	 * the access token associated with this implementation of the Strava functionality
	 */
	private final Token token;

	/**
	 * Constructor requires a token
	 *
	 * @param token
	 *            the access token to be used with calls to the Strava API
	 */
	public Strava(final Token token) {
		this.token = token;
		addServices();
	}

	private void addServices() {
		this.activityService = this.token.getService(ActivityService.class);
		this.athleteService = this.token.getService(AthleteService.class);
		this.challengeService = this.token.getService(ChallengeService.class);
		this.clubService = this.token.getService(ClubService.class);
		this.clubGroupEventService = this.token.getService(ClubGroupEventService.class);
		this.gearService = this.token.getService(GearService.class);
		this.routeService = this.token.getService(RouteService.class);
		this.runningRaceService = this.token.getService(RunningRaceService.class);
		this.segmentEffortService = this.token.getService(SegmentEffortService.class);
		this.segmentService = this.token.getService(SegmentService.class);
		this.streamService = this.token.getService(StreamService.class);
		this.tokenService = this.token.getService(TokenService.class);
		this.uploadService = this.token.getService(UploadService.class);
		this.webhookService = this.token.getService(WebhookService.class);
	}

	/**
	 * Constructor to use if you already have a known token with known auth scopes
	 *
	 * @param token
	 *            Token value (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>
	 * @param scopes
	 *            Authorisation scopes that the token has
	 */
	public Strava(final String token, final AuthorisationScope... scopes) {
		this.token = new Token();
		this.token.setScopes(Arrays.asList(scopes));
		this.token.setToken(token);
		this.token.setTokenType(null);
		addServices();
		this.token.setAthlete(this.athleteService.getAuthenticatedAthlete());
	}

	/**
	 * @param activityId
	 *            Upload identifier
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.service.UploadService#checkUploadStatus(java.lang.Long)
	 */
	@Override
	public StravaUploadResponse checkUploadStatus(final Long activityId) {
		return this.uploadService.checkUploadStatus(activityId);
	}

	/**
	 * @param uploadId
	 *            Upload identifier
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.service.UploadService#checkUploadStatusAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<StravaUploadResponse> checkUploadStatusAsync(final Long uploadId) {
		return this.uploadService.checkUploadStatusAsync(uploadId);
	}

	/**
	 * @see javastrava.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		// Clear all the component services' caches
		this.activityService.clearCache();
		this.athleteService.clearCache();
		this.challengeService.clearCache();
		this.clubService.clearCache();
		this.gearService.clearCache();
		this.routeService.clearCache();
		this.runningRaceService.clearCache();
		this.segmentEffortService.clearCache();
		this.segmentService.clearCache();
		this.streamService.clearCache();
		this.uploadService.clearCache();
	}

	/**
	 * @see javastrava.service.ActivityService#createComment(java.lang.Long, java.lang.String)
	 */
	@Override
	public StravaComment createComment(final Long activityId, final String text) throws NotFoundException, BadRequestException {
		return this.activityService.createComment(activityId, text);
	}

	/**
	 * @see javastrava.service.ActivityService#createComment(StravaComment)
	 */
	@Override
	public StravaComment createComment(final StravaComment comment) throws NotFoundException, BadRequestException {
		return this.activityService.createComment(comment.getActivityId(), comment.getText());
	}

	/**
	 * @see javastrava.service.ActivityService#createCommentAsync(java.lang.Long, java.lang.String)
	 */
	@Override
	public CompletableFuture<StravaComment> createCommentAsync(final Long activityId, final String text) throws NotFoundException, BadRequestException {
		return this.activityService.createCommentAsync(activityId, text);
	}

	/**
	 * @see javastrava.service.ActivityService#createManualActivity(javastrava.model.StravaActivity)
	 */
	@Override
	public StravaActivity createManualActivity(final StravaActivity activity) {
		return this.activityService.createManualActivity(activity);
	}

	/**
	 * @see javastrava.service.ActivityService#createManualActivityAsync(javastrava.model.StravaActivity)
	 */
	@Override
	public CompletableFuture<StravaActivity> createManualActivityAsync(final StravaActivity activity) {
		return this.activityService.createManualActivityAsync(activity);
	}

	@Override
	public StravaEventSubscription createSubscription(Integer clientId, String clientSecret, final StravaEventSubscription subscription, final String verifyToken) {
		return this.webhookService.createSubscription(clientId, clientSecret, subscription, verifyToken);
	}

	@Override
	public CompletableFuture<StravaEventSubscription> createSubscriptionAsync(Integer clientId, String clientSecret, final StravaEventSubscription subscription, final String verifyToken) {
		return this.webhookService.createSubscriptionAsync(clientId, clientSecret, subscription, verifyToken);
	}

	/**
	 * @see javastrava.auth.TokenService#deauthorise(javastrava.auth.model.Token)
	 */
	@Override
	public TokenResponse deauthorise(final Token accessToken) {
		return this.tokenService.deauthorise(accessToken);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteActivity(java.lang.Long)
	 */
	@Override
	public StravaActivity deleteActivity(final Long activityId) throws NotFoundException {
		return this.activityService.deleteActivity(activityId);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteActivity(StravaActivity)
	 */
	@Override
	public StravaActivity deleteActivity(final StravaActivity activity) throws NotFoundException {
		return this.activityService.deleteActivity(activity.getId());
	}

	/**
	 * @see javastrava.service.ActivityService#deleteActivityAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<StravaActivity> deleteActivityAsync(final Long activityId) throws NotFoundException {
		return this.activityService.deleteActivityAsync(activityId);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteComment(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void deleteComment(final Long activityId, final Integer commentId) throws NotFoundException {
		this.activityService.deleteComment(activityId, commentId);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteComment(javastrava.model.StravaComment)
	 */
	@Override
	public void deleteComment(final StravaComment comment) throws NotFoundException {
		this.activityService.deleteComment(comment);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteCommentAsync(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public CompletableFuture<Void> deleteCommentAsync(final Long activityId, final Integer commentId) throws NotFoundException {
		return this.activityService.deleteCommentAsync(activityId, commentId);
	}

	/**
	 * @see javastrava.service.ActivityService#deleteCommentAsync(javastrava.model.StravaComment)
	 */
	@Override
	public CompletableFuture<Void> deleteCommentAsync(final StravaComment comment) throws NotFoundException {
		return this.activityService.deleteCommentAsync(comment);
	}

	@Override
	public void deleteEvent(Integer id) throws NotFoundException, UnauthorizedException {
		this.clubGroupEventService.deleteEvent(id);
	}

	@Override
	public void deleteEvent(StravaClubEvent event) throws NotFoundException, UnauthorizedException {
		this.clubGroupEventService.deleteEvent(event);
	}

	@Override
	public CompletableFuture<Void> deleteEventAsync(Integer id) throws NotFoundException, UnauthorizedException {
		return this.clubGroupEventService.deleteEventAsync(id);
	}

	@Override
	public CompletableFuture<Void> deleteEventAsync(StravaClubEvent event) throws NotFoundException, UnauthorizedException {
		return this.clubGroupEventService.deleteEventAsync(event);
	}

	@Override
	public void deleteSubscription(final Integer clientId, final String clientSecret, final Integer id) {
		this.webhookService.deleteSubscription(clientId, clientSecret, id);
	}

	@Override
	public CompletableFuture<Void> deleteSubscriptionAsync(final Integer clientId, final String clientSecret, final Integer id) {
		return this.webhookService.deleteSubscriptionAsync(clientId, clientSecret, id);
	}

	/**
	 * @see javastrava.service.ActivityService#getActivity(java.lang.Long)
	 */
	@Override
	public StravaActivity getActivity(final Long activityId) {
		return this.activityService.getActivity(activityId);
	}

	/**
	 * @see javastrava.service.ActivityService#getActivity(java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public StravaActivity getActivity(final Long activityId, final Boolean includeAllEfforts) {
		return this.activityService.getActivity(activityId, includeAllEfforts);
	}

	/**
	 * @see javastrava.service.ActivityService#getActivityAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<StravaActivity> getActivityAsync(final Long activityId) {
		return this.activityService.getActivityAsync(activityId);
	}

	/**
	 * @param activityId
	 *            The activity identifier
	 * @param includeAllEfforts
	 *            Whether to return efforts that Strava does not consider "important"
	 * @return The activity, if it exists, or <code>null</code> if it does not
	 * @see javastrava.service.ActivityService#getActivityAsync(java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public CompletableFuture<StravaActivity> getActivityAsync(final Long activityId, final Boolean includeAllEfforts) {
		return this.activityService.getActivityAsync(activityId, includeAllEfforts);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.StreamService#getActivityStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Long activityId) {
		return this.streamService.getActivityStreams(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist.
	 * @see javastrava.service.StreamService#getActivityStreams(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType,
	 *      javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public List<StravaStream> getActivityStreams(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getActivityStreams(activityId, resolution, seriesType, types);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.StreamService#getActivityStreamsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId) {
		return this.streamService.getActivityStreamsAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist.
	 * @see javastrava.service.StreamService#getActivityStreamsAsync(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getActivityStreamsAsync(final Long activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getActivityStreamsAsync(activityId, resolution, seriesType, types);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            Segment identifier
	 * @return The WHOLE leaderboard for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId) {
		return this.segmentService.getAllSegmentLeaderboard(segmentId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @return The WHOLE leaderboard for the segment, filtered as required, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange)
	 */
	@Override
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		return this.segmentService.getAllSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            Segment identifier
	 * @return The WHOLE leaderboard for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboardAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId) {
		return this.segmentService.getAllSegmentLeaderboardAsync(segmentId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @return The WHOLE leaderboard for the segment, filtered as required, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getAllSegmentLeaderboardAsync(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		return this.segmentService.getAllSegmentLeaderboardAsync(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Athlete details, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#getAthlete(java.lang.Integer)
	 */
	@Override
	public StravaAthlete getAthlete(final Integer athleteId) {
		return this.athleteService.getAthlete(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Athlete details, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#getAthleteAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaAthlete> getAthleteAsync(final Integer athleteId) {
		return this.athleteService.getAthleteAsync(athleteId);
	}

	/**
	 * @return The authenticated athlete
	 * @see javastrava.service.AthleteService#getAuthenticatedAthlete()
	 */
	@Override
	public StravaAthlete getAuthenticatedAthlete() {
		return this.athleteService.getAuthenticatedAthlete();
	}

	/**
	 * @return The authenticated athlete
	 * @see javastrava.service.AthleteService#getAuthenticatedAthlete()
	 */
	@Override
	public CompletableFuture<StravaAthlete> getAuthenticatedAthleteAsync() {
		return this.athleteService.getAuthenticatedAthleteAsync();
	}

	/**
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1
	 *
	 * @return The athlete zones object
	 */
	@Override
	public StravaAthleteZones getAuthenticatedAthleteZones() {
		return this.athleteService.getAuthenticatedAthleteZones();
	}

	/**
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1
	 *
	 * @return The athlete zones object (via a {@link CompletableFuture})
	 */
	@Override
	public CompletableFuture<StravaAthleteZones> getAuthenticatedAthleteZonesAsync() {
		return this.athleteService.getAuthenticatedAthleteZonesAsync();
	}

	@Override
	public StravaChallenge getChallenge(Integer id) {
		return this.challengeService.getChallenge(id);
	}

	@Override
	public CompletableFuture<StravaChallenge> getChallengeAsync(Integer id) {
		return this.challengeService.getChallengeAsync(id);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Club details, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#getClub(java.lang.Integer)
	 */
	@Override
	public StravaClub getClub(final Integer clubId) {
		return this.clubService.getClub(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Club details, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#getClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClub> getClubAsync(final Integer clubId) {
		return this.clubService.getClubAsync(clubId);
	}

	/**
	 * @param segmentEffortId
	 *            Segment effort identifier
	 * @return List of streams for the effort, or <code>null</code> if the segment effort does not exist
	 * @see javastrava.service.StreamService#getEffortStreams(java.lang.Long)
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long segmentEffortId) {
		return this.streamService.getEffortStreams(segmentEffortId);
	}

	/**
	 * @param segmentEffortId
	 *            Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.service.StreamService#getEffortStreams(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType,
	 *      javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public List<StravaStream> getEffortStreams(final Long segmentEffortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getEffortStreams(segmentEffortId, resolution, seriesType, types);
	}

	/**
	 * @param effortId
	 *            Segment effort identifier
	 * @return List of streams for the effort, or <code>null</code> if the segment effort does not exist
	 * @see javastrava.service.StreamService#getEffortStreamsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId) {
		return this.streamService.getEffortStreamsAsync(effortId);
	}

	/**
	 * @param effortId
	 *            Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.service.StreamService#getEffortStreamsAsync(java.lang.Long, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType,
	 *      javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getEffortStreamsAsync(final Long effortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getEffortStreamsAsync(effortId, resolution, seriesType, types);
	}

	@Override
	public StravaClubEvent getEvent(Integer id) {
		return this.clubGroupEventService.getEvent(id);
	}

	@Override
	public CompletableFuture<StravaClubEvent> getEventAsync(Integer id) {
		return this.clubGroupEventService.getEventAsync(id);
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return Gear details, or <code>null</code> if the gear does not exist
	 * @see javastrava.service.GearService#getGear(java.lang.String)
	 */
	@Override
	public StravaGear getGear(final String gearId) {
		return this.gearService.getGear(gearId);
	}

	/**
	 * @param gearId
	 *            Gear identifier
	 * @return Gear details, or <code>null</code> if the gear does not exist
	 * @see javastrava.service.GearService#getGearAsync(java.lang.String)
	 */
	@Override
	public CompletableFuture<StravaGear> getGearAsync(final String gearId) {
		return this.gearService.getGearAsync(gearId);
	}

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A detailed representation of the running race
	 */
	@Override
	public StravaRunningRace getRace(Integer id) {
		return this.runningRaceService.getRace(id);
	}

	/**
	 * <p>
	 * Get details of a specific running race
	 * </p>
	 *
	 * @param id
	 *            The id of the race to be retrieved
	 * @return A future which will return a detailed representation of the running race
	 */
	@Override
	public CompletableFuture<StravaRunningRace> getRaceAsync(Integer id) {
		return this.runningRaceService.getRaceAsync(id);
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
	 */
	@Override
	public StravaRoute getRoute(Integer routeId) {
		return this.routeService.getRoute(routeId);
	}

	/**
	 * <p>
	 * This request is used to retrieve details about a route. Private routes can only be accessed if owned by the authenticating user and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions. For raw data associated with a route see route streams.
	 * </p>
	 *
	 * @param routeId
	 *            The identifier of the route to retrieve
	 * @return The future on which to execute get() to retrieve the route
	 */
	@Override
	public CompletableFuture<StravaRoute> getRouteAsync(Integer routeId) {
		return this.routeService.getRouteAsync(routeId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Segment details, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getSegment(java.lang.Integer)
	 */
	@Override
	public StravaSegment getSegment(final Integer segmentId) {
		return this.segmentService.getSegment(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Segment details, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#getSegmentAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegment> getSegmentAsync(final Integer segmentId) {
		return this.segmentService.getSegmentAsync(segmentId);
	}

	/**
	 * @param segmentEffortId
	 *            Segment effort identifier
	 * @return Segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 */
	@Override
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId) {
		return this.segmentEffortService.getSegmentEffort(segmentEffortId);
	}

	/**
	 * @param segmentEffortId
	 *            Segment effort identifier
	 * @return Segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.service.SegmentEffortService#getSegmentEffortAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<StravaSegmentEffort> getSegmentEffortAsync(final Long segmentEffortId) {
		return this.segmentEffortService.getSegmentEffortAsync(segmentEffortId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Leaderboard, with first page of entries
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId) {
		return this.segmentService.getSegmentLeaderboard(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return Segment leaderboard, with entries in accordance with the paging instruction
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.getSegmentLeaderboard(segmentId, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @param pagingInstruction
	 *            (Optional) Paging instruction
	 * @param contextEntries
	 *            (Optional) number of entries to return as athlete context either side of the athlete (default is 2, maximum is 15)
	 * @return Segment leaderboard, as per filters
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange, javastrava.util.Paging, java.lang.Integer)
	 */
	@Override
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction, final Integer contextEntries) {
		return this.segmentService.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, pagingInstruction, contextEntries);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Leaderboard, with first page of entries
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId) {
		return this.segmentService.getSegmentLeaderboardAsync(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return Segment leaderboard, with entries in accordance with the paging instruction
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.getSegmentLeaderboardAsync(segmentId, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @param pagingInstruction
	 *            (Optional) Paging instruction
	 * @param contextEntries
	 *            (Optional) number of entries to return as athlete context either side of the athlete (default is 2, maximum is 15)
	 * @return Segment leaderboard, as per filters
	 * @see javastrava.service.SegmentService#getSegmentLeaderboardAsync(java.lang.Integer, javastrava.model.reference.StravaGender, javastrava.model.reference.StravaAgeGroup,
	 *      javastrava.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer, javastrava.model.reference.StravaLeaderboardDateRange, javastrava.util.Paging, java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction,
			final Integer contextEntries) {
		return this.segmentService.getSegmentLeaderboardAsync(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, pagingInstruction, contextEntries);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.StreamService#getSegmentStreams(java.lang.Integer)
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer segmentId) {
		return this.streamService.getSegmentStreams(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.StreamService#getSegmentStreams(java.lang.Integer, javastrava.model.reference.StravaStreamResolutionType, javastrava.model.reference.StravaStreamSeriesDownsamplingType,
	 *      javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public List<StravaStream> getSegmentStreams(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getSegmentStreams(segmentId, resolution, seriesType, types);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.StreamService#getSegmentStreamsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId) {
		return this.streamService.getSegmentStreamsAsync(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.StreamService#getSegmentStreamsAsync(java.lang.Integer, javastrava.model.reference.StravaStreamResolutionType,
	 *      javastrava.model.reference.StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType[])
	 */
	@Override
	public CompletableFuture<List<StravaStream>> getSegmentStreamsAsync(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getSegmentStreamsAsync(segmentId, resolution, seriesType, types);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @throws NotFoundException
	 *             If the activity does not exist on Strava
	 * @see javastrava.service.ActivityService#giveKudos(java.lang.Long)
	 */
	@Override
	public void giveKudos(final Long activityId) throws NotFoundException {
		this.activityService.giveKudos(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @throws NotFoundException
	 *             If the activity does not exist on Strava
	 * @see javastrava.service.ActivityService#giveKudosAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<Void> giveKudosAsync(final Long activityId) throws NotFoundException {
		return this.activityService.giveKudosAsync(activityId);
	}

	/**
	 * @param scopes
	 *            Authorisation scopes to check are in the token
	 * @return <code>true</code> if the token has all the identified scopes, <code>false</code> otherwise
	 */
	public boolean hasAuthorisationScopes(final AuthorisationScope... scopes) {
		// Check all the scopes in the list are in the token
		for (final AuthorisationScope scope : scopes) {
			if (!this.token.getScopes().contains(scope)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param scopes
	 *            Authorisation scopes to check are in the token
	 * @return <code>true</code> if the token has all the identified scopes AND NO MORE, <code>false</code> otherwise
	 */
	public boolean hasExactAuthorisationScopes(final AuthorisationScope... scopes) {
		if (!hasAuthorisationScopes(scopes)) {
			return false;
		}

		// Check all the scopes in the token are in the list
		final List<AuthorisationScope> scopeList = Arrays.asList(scopes);
		for (final AuthorisationScope scope : this.token.getScopes()) {
			if (!scopeList.contains(scope)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void joinChallenge(Integer id) {
		this.challengeService.joinChallenge(id);
	}

	@Override
	public CompletableFuture<Void> joinChallengeAsync(Integer id) {
		return this.challengeService.joinChallengeAsync(id);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.service.ClubService#joinClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse joinClub(final Integer clubId) {
		return this.clubService.joinClub(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.service.ClubService#joinClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClubMembershipResponse> joinClubAsync(final Integer clubId) {
		return this.clubService.joinClubAsync(clubId);
	}

	@Override
	public StravaClubEventJoinResponse joinEvent(Integer id) {
		return this.clubGroupEventService.joinEvent(id);
	}

	@Override
	public CompletableFuture<StravaClubEventJoinResponse> joinEventAsync(Integer id) {
		return this.clubGroupEventService.joinEventAsync(id);
	}

	@Override
	public void leaveChallenge(Integer id) {
		this.challengeService.leaveChallenge(id);

	}

	@Override
	public CompletableFuture<Void> leaveChallengeAsync(Integer id) {
		return this.challengeService.leaveChallengeAsync(id);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.service.ClubService#leaveClub(java.lang.Integer)
	 */
	@Override
	public StravaClubMembershipResponse leaveClub(final Integer clubId) {
		return this.clubService.leaveClub(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.service.ClubService#leaveClubAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaClubMembershipResponse> leaveClubAsync(final Integer clubId) {
		return this.clubService.leaveClubAsync(clubId);
	}

	@Override
	public StravaClubEventJoinResponse leaveEvent(Integer id) {
		return this.clubGroupEventService.leaveEvent(id);
	}

	@Override
	public CompletableFuture<StravaClubEventJoinResponse> leaveEventAsync(Integer id) {
		return this.clubGroupEventService.leaveEventAsync(id);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityComments(java.lang.Long)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Long activityId) {
		return this.activityService.listActivityComments(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether to include markdown in comments
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityComments(java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Long activityId, final Boolean markdown) {
		return this.activityService.listActivityComments(activityId, markdown);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether to include markdown in comments
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityComments(java.lang.Long, java.lang.Boolean, javastrava.util.Paging)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Long activityId, final Boolean markdown, final Paging pagingInstruction) {
		return this.activityService.listActivityComments(activityId, markdown, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityComments(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public List<StravaComment> listActivityComments(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityComments(activityId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityCommentsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId) {
		return this.activityService.listActivityCommentsAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether to include markdown in comments
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityCommentsAsync(java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Boolean markdown) {
		return this.activityService.listActivityCommentsAsync(activityId, markdown);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param markdown
	 *            Whether to include markdown in comments
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityCommentsAsync(java.lang.Long, java.lang.Boolean, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Boolean markdown, final Paging pagingInstruction) {
		return this.activityService.listActivityCommentsAsync(activityId, markdown, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityCommentsAsync(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityCommentsAsync(activityId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of athletes who have given kudos to the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityKudoers(java.lang.Long)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Long activityId) {
		return this.activityService.listActivityKudoers(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes who have given kudos to the activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityKudoers(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listActivityKudoers(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityKudoers(activityId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of athletes who have given kudos to the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityKudoersAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listActivityKudoersAsync(final Long activityId) {
		return this.activityService.listActivityKudoersAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes who have given kudos to the activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityKudoersAsync(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listActivityKudoersAsync(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityKudoersAsync(activityId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of laps belonging to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityLaps(java.lang.Long)
	 */
	@Override
	public List<StravaLap> listActivityLaps(final Long activityId) {
		return this.activityService.listActivityLaps(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of laps belonging to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityLapsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaLap>> listActivityLapsAsync(final Long activityId) {
		return this.activityService.listActivityLapsAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of photos attached to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityPhotos(java.lang.Long)
	 */
	@Override
	public List<StravaPhoto> listActivityPhotos(final Long activityId) {
		return this.activityService.listActivityPhotos(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of photos attached to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityPhotosAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaPhoto>> listActivityPhotosAsync(final Long activityId) {
		return this.activityService.listActivityPhotosAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return The activity zones for the activity (if it exists), or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityZones(java.lang.Long)
	 */
	@Override
	public List<StravaActivityZone> listActivityZones(final Long activityId) {
		return this.activityService.listActivityZones(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return The activity zones for the activity (if it exists), or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listActivityZonesAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaActivityZone>> listActivityZonesAsync(final Long activityId) {
		return this.activityService.listActivityZonesAsync(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY COMMENTS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of all comments on the activity
	 * @see javastrava.service.ActivityService#listAllActivityComments(java.lang.Long)
	 */
	@Override
	public List<StravaComment> listAllActivityComments(final Long activityId) {
		return this.activityService.listAllActivityComments(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY COMMENTS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of all comments on the activity
	 * @see javastrava.service.ActivityService#listAllActivityCommentsAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaComment>> listAllActivityCommentsAsync(final Long activityId) {
		return this.activityService.listAllActivityCommentsAsync(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY KUDOERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of ALL athletes giving kudos to the activity
	 * @see javastrava.service.ActivityService#listAllActivityKudoers(java.lang.Long)
	 */
	@Override
	public List<StravaAthlete> listAllActivityKudoers(final Long activityId) {
		return this.activityService.listAllActivityKudoers(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY KUDOERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of ALL athletes giving kudos to the activity
	 * @see javastrava.service.ActivityService#listAllActivityKudoersAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllActivityKudoersAsync(final Long activityId) {
		return this.activityService.listAllActivityKudoersAsync(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL the athlete's friends, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#listAllAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllAthleteFriends(final Integer athleteId) {
		return this.athleteService.listAllAthleteFriends(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL the athlete's friends, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#listAllAthleteFriendsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllAthleteFriendsAsync(final Integer athleteId) {
		return this.athleteService.listAllAthleteFriendsAsync(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY KOMS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL segment efforts which represent a KOM for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#listAllAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllAthleteKOMs(final Integer athleteId) {
		return this.athleteService.listAllAthleteKOMs(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY KOMS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL segment efforts which represent a KOM for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#listAllAthleteKOMsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAllAthleteKOMsAsync(final Integer athleteId) {
		return this.athleteService.listAllAthleteKOMsAsync(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL athletes that both the identified athlete and the authenticated athlete are following
	 * @see javastrava.service.AthleteService#listAllAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllAthletesBothFollowing(final Integer athleteId) {
		return this.athleteService.listAllAthletesBothFollowing(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of ALL athletes that both the identified athlete and the authenticated athlete are following
	 * @see javastrava.service.AthleteService#listAllAthletesBothFollowingAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllAthletesBothFollowingAsync(final Integer athleteId) {
		return this.athleteService.listAllAthletesBothFollowingAsync(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return List of all the authenticated athlete's activities
	 * @see javastrava.service.ActivityService#listAllAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAllAuthenticatedAthleteActivities() {
		return this.activityService.listAllAuthenticatedAthleteActivities();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @return List of all the authenticated athlete's activities, filtered by dates
	 * @see javastrava.service.ActivityService#listAllAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaActivity> listAllAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAllAuthenticatedAthleteActivities(before, after);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return List of all the authenticated athlete's activities
	 * @see javastrava.service.ActivityService#listAllAuthenticatedAthleteActivitiesAsync()
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllAuthenticatedAthleteActivitiesAsync() {
		return this.activityService.listAllAuthenticatedAthleteActivitiesAsync();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @return List of all the authenticated athlete's activities, filtered by dates
	 * @see javastrava.service.ActivityService#listAllAuthenticatedAthleteActivitiesAsync(java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAllAuthenticatedAthleteActivitiesAsync(before, after);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return List of ALL the authenticated athlete's friends
	 * @see javastrava.service.AthleteService#listAllAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAllAuthenticatedAthleteFriends() {
		return this.athleteService.listAllAuthenticatedAthleteFriends();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return List of ALL the authenticated athlete's friends
	 * @see javastrava.service.AthleteService#listAllAuthenticatedAthleteFriendsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllAuthenticatedAthleteFriendsAsync() {
		return this.athleteService.listAllAuthenticatedAthleteFriendsAsync();
	}

	/**
	 * @return List of ALL segments starred by the authenticated athlete
	 * @see javastrava.service.SegmentService#listAllAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments() {
		return this.segmentService.listAllAuthenticatedAthleteStarredSegments();
	}

	/**
	 * @return List of ALL segments starred by the authenticated athlete
	 * @see javastrava.service.SegmentService#listAllAuthenticatedAthleteStarredSegmentsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAllAuthenticatedAthleteStarredSegmentsAsync() {
		return this.segmentService.listAllAuthenticatedAthleteStarredSegmentsAsync();
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @return List of administrators
	 */
	@Override
	public List<StravaAthlete> listAllClubAdmins(final Integer clubId) {
		return this.clubService.listAllClubAdmins(clubId);
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @return List of administrators - call {@link CompletableFuture#complete(Object)} when ready.
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllClubAdminsAsync(final Integer clubId) {
		return this.clubService.listAllClubAdminsAsync(clubId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - CLUBS WITH MANY MEMBERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param clubId
	 *            Club identifier
	 * @return List of ALL members of the club, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listAllClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAllClubMembers(final Integer clubId) {
		return this.clubService.listAllClubMembers(clubId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - CLUBS WITH MANY MEMBERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param clubId
	 *            Club identifier
	 * @return List of ALL members of the club, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listAllClubMembersAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAllClubMembersAsync(final Integer clubId) {
		return this.clubService.listAllClubMembersAsync(clubId);
	}

	@Override
	public List<StravaAthlete> listAllEventJoinedAthletes(Integer eventId) {
		return this.clubGroupEventService.listAllEventJoinedAthletes(eventId);
	}

	@Override
	public CompletableFuture<List<StravaAthlete>> listAllEventJoinedAthletesAsync(Integer eventId) {
		return this.clubGroupEventService.listAllEventJoinedAthletesAsync(eventId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS' ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return All activities by friends of the authenticated athlete
	 * @see javastrava.service.ActivityService#listAllFriendsActivities()
	 */
	@Override
	public List<StravaActivity> listAllFriendsActivities() {
		return this.activityService.listAllFriendsActivities();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS' ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @return All activities by friends of the authenticated athlete
	 * @see javastrava.service.ActivityService#listAllFriendsActivitiesAsync()
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllFriendsActivitiesAsync() {
		return this.activityService.listAllFriendsActivitiesAsync();
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of ALL recent activities by members of the club (note that Strava caps this at 200 activities), or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listAllRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId) {
		return this.clubService.listAllRecentClubActivities(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of ALL recent activities by members of the club (note that Strava caps this at 200 activities), or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listAllRecentClubActivitiesAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllRecentClubActivitiesAsync(final Integer clubId) {
		return this.clubService.listAllRecentClubActivitiesAsync(clubId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of ALL related activities
	 * @see javastrava.service.ActivityService#listAllRelatedActivities(java.lang.Long)
	 */
	@Override
	public List<StravaActivity> listAllRelatedActivities(final Long activityId) {
		return this.activityService.listAllRelatedActivities(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @return List of ALL related activities
	 * @see javastrava.service.ActivityService#listAllRelatedActivitiesAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAllRelatedActivitiesAsync(final Long activityId) {
		return this.activityService.listAllRelatedActivitiesAsync(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF EFFORTS, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            Segment identifier
	 * @return List of ALL efforts on the segment, ever, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#listAllSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId) {
		return this.segmentService.listAllSegmentEfforts(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param athleteId
	 *            (Optional) Athlete identifier
	 * @param startDate
	 *            (Optional) Do not return activities before this date/time
	 * @param endDate
	 *            (Optional) Do not return activities after this date/time
	 * @return List of ALL efforts on the segment, filtered as required, or <code>null</code> if the segment or athlete do not exist
	 * @see javastrava.service.SegmentService#listAllSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		return this.segmentService.listAllSegmentEfforts(segmentId, athleteId, startDate, endDate);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF EFFORTS, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param segmentId
	 *            Segment identifier
	 * @return List of ALL efforts on the segment, ever, or <code>null</code> if the segment does not exist
	 * @see javastrava.service.SegmentService#listAllSegmentEffortsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId) {
		return this.segmentService.listAllSegmentEffortsAsync(segmentId);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param athleteId
	 *            (Optional) Athlete identifier
	 * @param startDate
	 *            (Optional) Do not return activities before this date/time
	 * @param endDate
	 *            (Optional) Do not return activities after this date/time
	 * @return List of ALL efforts on the segment, filtered as required, or <code>null</code> if the segment or athlete do not exist
	 * @see javastrava.service.SegmentService#listAllSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		return this.segmentService.listAllSegmentEffortsAsync(segmentId, athleteId, startDate, endDate);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return list of ALL segments starred by the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listAllStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listAllStarredSegments(final Integer athleteId) {
		return this.segmentService.listAllStarredSegments(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return list of ALL segments starred by the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listAllStarredSegmentsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAllStarredSegmentsAsync(final Integer athleteId) {
		return this.segmentService.listAllStarredSegmentsAsync(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of athletes the identified athlete is following, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthleteFriends(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId) {
		return this.athleteService.listAthleteFriends(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes the identified athlete is following, according with the paging instruction, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthleteFriends(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteFriends(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of athletes the identified athlete is following, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthleteFriendsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAthleteFriendsAsync(final Integer athleteId) {
		return this.athleteService.listAthleteFriendsAsync(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes the identified athlete is following, according with the paging instruction, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthleteFriendsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAthleteFriendsAsync(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteFriendsAsync(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of segment efforts which represent KOM's for this athlete, first page only
	 * @see javastrava.service.AthleteService#listAthleteKOMs(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId) {
		return this.athleteService.listAthleteKOMs(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segment efforts which represent KOM's for this athlete, according with the paging instruction
	 * @see javastrava.service.AthleteService#listAthleteKOMs(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteKOMs(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of segment efforts which represent KOM's for this athlete, first page only
	 * @see javastrava.service.AthleteService#listAthleteKOMsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAthleteKOMsAsync(final Integer athleteId) {
		return this.athleteService.listAthleteKOMsAsync(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segment efforts which represent KOM's for this athlete, according with the paging instruction
	 * @see javastrava.service.AthleteService#listAthleteKOMsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listAthleteKOMsAsync(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteKOMsAsync(athleteId, pagingInstruction);
	}

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The route
	 */
	@Override
	public List<StravaRoute> listAthleteRoutes(Integer id) {
		return this.routeService.listAthleteRoutes(id);
	}

	/**
	 * <p>
	 * Lists a specific athlete’s routes. Private routes will only be included if the authenticating user is viewing their own routes and the token has {@link AuthorisationScope#VIEW_PRIVATE
	 * view_private} permissions.
	 * </p>
	 *
	 * @param id
	 *            The athlete id whose routes should be listed
	 * @return The future to execute get() on to return the routes
	 */
	@Override
	public CompletableFuture<List<StravaRoute>> listAthleteRoutesAsync(Integer id) {
		return this.routeService.listAthleteRoutesAsync(id);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthletesBothFollowing(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId) {
		return this.athleteService.listAthletesBothFollowing(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, according with the paging instruction, or <code>null</code> if the identified athlete does
	 *         not exist
	 * @see javastrava.service.AthleteService#listAthletesBothFollowing(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthletesBothFollowing(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.service.AthleteService#listAthletesBothFollowingAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAthletesBothFollowingAsync(final Integer athleteId) {
		return this.athleteService.listAthletesBothFollowingAsync(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, according with the paging instruction, or <code>null</code> if the identified athlete does
	 *         not exist
	 * @see javastrava.service.AthleteService#listAthletesBothFollowingAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAthletesBothFollowingAsync(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthletesBothFollowingAsync(athleteId, pagingInstruction);
	}

	/**
	 * @return First page of authenticated athlete's activities, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivities()
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities() {
		return this.activityService.listAuthenticatedAthleteActivities();
	}

	/**
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @return First page of authenticated athlete's activities, filtered by dates
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAuthenticatedAthleteActivities(before, after);
	}

	/**
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of authenticated athlete's activities, filtered by dates, according to the paging instruction
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivities(before, after, pagingInstruction);
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of authenticated athlete's activities corresponding to the paging instruction
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivities(javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivities(pagingInstruction);
	}

	/**
	 * @return First page of authenticated athlete's activities, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivitiesAsync()
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync() {
		return this.activityService.listAuthenticatedAthleteActivitiesAsync();
	}

	/**
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @return First page of authenticated athlete's activities, filtered by dates
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivitiesAsync(java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAuthenticatedAthleteActivitiesAsync(before, after);
	}

	/**
	 * @param before
	 *            Only return activities before this date/time
	 * @param after
	 *            Only return activities after this date/time
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of authenticated athlete's activities, filtered by dates, according to the paging instruction
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivitiesAsync(java.time.LocalDateTime, java.time.LocalDateTime, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivitiesAsync(before, after, pagingInstruction);
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of authenticated athlete's activities corresponding to the paging instruction
	 * @see javastrava.service.ActivityService#listAuthenticatedAthleteActivitiesAsync(javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivitiesAsync(pagingInstruction);
	}

	/**
	 * @return List of all clubs that the authenticated athlete is a member of
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubs()
	 */
	@Override
	public List<StravaClub> listAuthenticatedAthleteClubs() {
		return this.clubService.listAuthenticatedAthleteClubs();
	}

	/**
	 * @return List of all clubs that the authenticated athlete is a member of
	 * @see javastrava.service.ClubService#listAuthenticatedAthleteClubsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaClub>> listAuthenticatedAthleteClubsAsync() {
		return this.clubService.listAuthenticatedAthleteClubsAsync();
	}

	/**
	 * @return List of athletes the authenticated athlete is following, first page only
	 * @see javastrava.service.AthleteService#listAuthenticatedAthleteFriends()
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends() {
		return this.athleteService.listAuthenticatedAthleteFriends();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes the authenticated athlete is following, according with the paging instruction
	 * @see javastrava.service.AthleteService#listAuthenticatedAthleteFriends(javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction) {
		return this.athleteService.listAuthenticatedAthleteFriends(pagingInstruction);
	}

	/**
	 * @return List of athletes the authenticated athlete is following, first page only
	 * @see javastrava.service.AthleteService#listAuthenticatedAthleteFriendsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAuthenticatedAthleteFriendsAsync() {
		return this.athleteService.listAuthenticatedAthleteFriendsAsync();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes the authenticated athlete is following, according with the paging instruction
	 * @see javastrava.service.AthleteService#listAuthenticatedAthleteFriendsAsync(javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listAuthenticatedAthleteFriendsAsync(final Paging pagingInstruction) {
		return this.athleteService.listAuthenticatedAthleteFriendsAsync(pagingInstruction);
	}

	/**
	 * @return List of segments starred by the authenticated athlete, first page only
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments()
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return this.segmentService.listAuthenticatedAthleteStarredSegments();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segments starred by the authenticated athlete, in accordance with the paging instruction
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments(javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		return this.segmentService.listAuthenticatedAthleteStarredSegments(pagingInstruction);
	}

	/**
	 * @return List of segments starred by the authenticated athlete, first page only
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegmentsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync() {
		return this.segmentService.listAuthenticatedAthleteStarredSegmentsAsync();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segments starred by the authenticated athlete, in accordance with the paging instruction
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegmentsAsync(javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync(final Paging pagingInstruction) {
		return this.segmentService.listAuthenticatedAthleteStarredSegmentsAsync(pagingInstruction);
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @return List of administrators
	 */
	@Override
	public List<StravaAthlete> listClubAdmins(final Integer clubId) {
		return this.clubService.listClubAdmins(clubId);
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @param paging
	 *            Paging instruction
	 * @return List of administrators
	 */
	@Override
	public List<StravaAthlete> listClubAdmins(final Integer clubId, final Paging paging) {
		return this.clubService.listClubAdmins(clubId, paging);
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @return {@link CompletableFuture} which will return the List of administrators
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId) {
		return this.clubService.listClubAdminsAsync(clubId);
	}

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>
	 * The athletes are returned in summary representation
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the club with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private and the authorised athlete is not a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported
	 * </p>
	 *
	 * @param clubId
	 *            The club whose administrators should be listed
	 * @param paging
	 *            Paging instruction
	 * @return {@link CompletableFuture} which will return the List of administrators
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId, final Paging paging) {
		return this.clubService.listClubAdminsAsync(clubId, paging);
	}

	/**
	 * @param clubId
	 *            The club id for which announcements should be returned
	 * @return Array of {@link StravaClubAnnouncement} for the given {@link StravaClub club}
	 * @see javastrava.service.ClubService#listClubAnnouncements(java.lang.Integer)
	 */
	@Override
	public List<StravaClubAnnouncement> listClubAnnouncements(final Integer clubId) {
		return this.clubService.listClubAnnouncements(clubId);

	}

	/**
	 * @param clubId
	 *            The club id for which announcements should be returned
	 * @return Array of {@link StravaClubAnnouncement} for the given {@link StravaClub club}
	 * @see javastrava.service.ClubService#listClubAnnouncementsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaClubAnnouncement>> listClubAnnouncementsAsync(final Integer clubId) {
		return this.clubService.listClubAnnouncementsAsync(clubId);
	}

	/**
	 * <p>
	 * Group Events are optionally recurring events for club members.
	 * </p>
	 * <p>
	 * Only club members can access private club events.
	 * </p>
	 * <p>
	 * The objects are returned in summary representation.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @see <a href= "http://strava.github.io/api/partner/v3/clubs/#get-group-events">http://strava.github.io/api/partner/v3/clubs/#get-group-events</a>
	 * @param clubId
	 *            Club identifier
	 * @return List of all club events
	 *
	 * @see javastrava.service.ClubService#listClubGroupEvents(java.lang.Integer)
	 */
	@Override
	public List<StravaClubEvent> listClubGroupEvents(final Integer clubId) {
		return this.clubService.listClubGroupEvents(clubId);
	}

	/**
	 * <p>
	 * Group Events are optionally recurring events for club members.
	 * </p>
	 * <p>
	 * Only club members can access private club events.
	 * </p>
	 * <p>
	 * The objects are returned in summary representation.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported
	 * </p>
	 *
	 * @see <a href= "http://strava.github.io/api/partner/v3/clubs/#get-group-events">http://strava.github.io/api/partner/v3/clubs/#get-group-events</a>
	 * @param clubId
	 *            Club identifier
	 * @return List of all club events
	 *
	 * @see javastrava.service.ClubService#listClubGroupEvents(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaClubEvent>> listClubGroupEventsAsync(final Integer clubId) {
		return this.clubService.listClubGroupEventsAsync(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of athletes who are members of the club, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listClubMembers(java.lang.Integer)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer clubId) {
		return this.clubService.listClubMembers(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes who are members of the club, according with the paging instruction
	 * @see javastrava.service.ClubService#listClubMembers(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaAthlete> listClubMembers(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listClubMembers(clubId, pagingInstruction);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of athletes who are members of the club, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listClubMembersAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId) {
		return this.clubService.listClubMembersAsync(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of athletes who are members of the club, according with the paging instruction
	 * @see javastrava.service.ClubService#listClubMembersAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listClubMembersAsync(clubId, pagingInstruction);
	}

	@Override
	public List<StravaAthlete> listEventJoinedAthletes(Integer eventId, Paging pagingInstruction) {
		return this.clubGroupEventService.listEventJoinedAthletes(eventId, pagingInstruction);
	}

	@Override
	public CompletableFuture<List<StravaAthlete>> listEventJoinedAthletesAsync(Integer eventId, Paging pagingInstruction) {
		return this.clubGroupEventService.listEventJoinedAthletesAsync(eventId, pagingInstruction);
	}

	/**
	 * @return First page of the list of activities by friends of the authenticated athlete, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listFriendsActivities()
	 */
	@Override
	public List<StravaActivity> listFriendsActivities() {
		return this.activityService.listFriendsActivities();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of activities by friends of the authenticated athlete, according to the paging instruction, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listFriendsActivities(javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction) {
		return this.activityService.listFriendsActivities(pagingInstruction);
	}

	/**
	 * @return First page of the list of activities by friends of the authenticated athlete, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listFriendsActivitiesAsync()
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listFriendsActivitiesAsync() {
		return this.activityService.listFriendsActivitiesAsync();
	}

	/**
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of activities by friends of the authenticated athlete, according to the paging instruction, sorted by start date (descending)
	 * @see javastrava.service.ActivityService#listFriendsActivitiesAsync(javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listFriendsActivitiesAsync(final Paging pagingInstruction) {
		return this.activityService.listFriendsActivitiesAsync(pagingInstruction);
	}

	@Override
	public List<StravaChallenge> listJoinedChallenges() {
		return this.challengeService.listJoinedChallenges();
	}

	@Override
	public CompletableFuture<List<StravaChallenge>> listJoinedChallengesAsync() {
		return this.challengeService.listJoinedChallengesAsync();
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
	@Override
	public List<StravaRunningRace> listRaces(Integer year) {
		return this.runningRaceService.listRaces(year);
	}

	/**
	 * <p>
	 * List Strava's featured running races
	 * </p>
	 *
	 * @param year
	 *            (Optional) restrict results to the given year
	 * @return Future containing list of running races as summary representations
	 */
	@Override
	public CompletableFuture<List<StravaRunningRace>> listRacesAsync(Integer year) {
		return this.runningRaceService.listRacesAsync(year);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of activities done by members of the club, in reverse order of start date, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listRecentClubActivities(java.lang.Integer)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer clubId) {
		return this.clubService.listRecentClubActivities(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return list of activities done by members of the club, in reverse order of start date, according with the paging instruction, or <code>null</code> if the club does not exist. Note that Strava
	 *         returns a maximum of 200 recent activities.
	 * @see javastrava.service.ClubService#listRecentClubActivities(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listRecentClubActivities(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listRecentClubActivities(clubId, pagingInstruction);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @return List of activities done by members of the club, in reverse order of start date, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.service.ClubService#listRecentClubActivitiesAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId) {
		return this.clubService.listRecentClubActivitiesAsync(clubId);
	}

	/**
	 * @param clubId
	 *            Club identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return list of activities done by members of the club, in reverse order of start date, according with the paging instruction, or <code>null</code> if the club does not exist. Note that Strava
	 *         returns a maximum of 200 recent activities.
	 * @see javastrava.service.ClubService#listRecentClubActivitiesAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listRecentClubActivitiesAsync(clubId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of activities that Strava has determined were done 'with' the identified activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listRelatedActivities(java.lang.Long)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Long activityId) {
		return this.activityService.listRelatedActivities(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of activities that Strava has determined were done 'with' the identified activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listRelatedActivities(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public List<StravaActivity> listRelatedActivities(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listRelatedActivities(activityId, pagingInstruction);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @return List of activities that Strava has determined were done 'with' the identified activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listRelatedActivitiesAsync(java.lang.Long)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRelatedActivitiesAsync(final Long activityId) {
		return this.activityService.listRelatedActivitiesAsync(activityId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of activities that Strava has determined were done 'with' the identified activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.service.ActivityService#listRelatedActivitiesAsync(java.lang.Long, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaActivity>> listRelatedActivitiesAsync(final Long activityId, final Paging pagingInstruction) {
		return this.activityService.listRelatedActivitiesAsync(activityId, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending. If the segment does not exist,
	 *         then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId) {
		return this.segmentService.listSegmentEfforts(segmentId);
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) Return only efforts after this date/time
	 * @param endDateLocal
	 *            (Optional) Return only efforts before this date/time
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed time if an athleteId
	 *         is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal) {
		return this.segmentService.listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal);
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) Return only efforts after this date/time
	 * @param endDateLocal
	 *            (Optional) Return only efforts before this date/time
	 * @param pagingInstruction
	 *            (Optional) Paging instruction
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed time if an athleteId
	 *         is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal,
			final Paging pagingInstruction) {
		return this.segmentService.listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending. If the segment does not exist,
	 *         then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEfforts(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.listSegmentEfforts(segmentId, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending. If the segment does not exist,
	 *         then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId) {
		return this.segmentService.listSegmentEffortsAsync(segmentId);
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) Return only efforts after this date/time
	 * @param endDateLocal
	 *            (Optional) Return only efforts before this date/time
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed time if an athleteId
	 *         is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal,
			final LocalDateTime endDateLocal) {
		return this.segmentService.listSegmentEffortsAsync(segmentId, athleteId, startDateLocal, endDateLocal);
	}

	/**
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) Return only efforts after this date/time
	 * @param endDateLocal
	 *            (Optional) Return only efforts before this date/time
	 * @param pagingInstruction
	 *            (Optional) Paging instruction
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed time if an athleteId
	 *         is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, java.lang.Integer, java.time.LocalDateTime, java.time.LocalDateTime, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal,
			final Paging pagingInstruction) {
		return this.segmentService.listSegmentEffortsAsync(segmentId, athleteId, startDateLocal, endDateLocal, pagingInstruction);
	}

	/**
	 * @param segmentId
	 *            Segment identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending. If the segment does not exist,
	 *         then returns <code>null</code>
	 * @see javastrava.service.SegmentService#listSegmentEffortsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.listSegmentEffortsAsync(segmentId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of segments starred by the identified athlete, first page only, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer athleteId) {
		return this.segmentService.listStarredSegments(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segments starred by the identified athlete, in accordance with the paging instruction, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public List<StravaSegment> listStarredSegments(final Integer athleteId, final Paging pagingInstruction) {
		return this.segmentService.listStarredSegments(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return List of segments starred by the identified athlete, first page only, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listStarredSegmentsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId) {
		return this.segmentService.listStarredSegmentsAsync(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @param pagingInstruction
	 *            Paging instruction
	 * @return List of segments starred by the identified athlete, in accordance with the paging instruction, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.SegmentService#listStarredSegmentsAsync(java.lang.Integer, javastrava.util.Paging)
	 */
	@Override
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId, final Paging pagingInstruction) {
		return this.segmentService.listStarredSegmentsAsync(athleteId, pagingInstruction);
	}

	@Override
	public List<StravaEventSubscription> listSubscriptions(final Integer clientId, final String clientSecret) {
		return this.webhookService.listSubscriptions(clientId, clientSecret);
	}

	@Override
	public CompletableFuture<List<StravaEventSubscription>> listSubscriptionsAsync(final Integer clientId, final String clientSecret) {
		return this.webhookService.listSubscriptionsAsync(clientId, clientSecret);
	}

	/**
	 * @param southwestCorner
	 *            Location of the southwest corner of the area to be explored
	 * @param northeastCorner
	 *            Location of the northeast corner of the area to be explored
	 * @param activityType
	 *            Activity type
	 * @param minCat
	 *            (Rides only) Minimum climb category to return
	 * @param maxCat
	 *            (Rides only) Maximum climb category to return
	 * @return A list of up to 10 segments within the area being explored
	 * @see javastrava.service.SegmentService#segmentExplore(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint, javastrava.model.reference.StravaSegmentExplorerActivityType,
	 *      javastrava.model.reference.StravaClimbCategory, javastrava.model.reference.StravaClimbCategory)
	 */
	@Override
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner, final StravaSegmentExplorerActivityType activityType,
			final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		return this.segmentService.segmentExplore(southwestCorner, northeastCorner, activityType, minCat, maxCat);
	}

	/**
	 * @param southwestCorner
	 *            Location of the southwest corner of the area to be explored
	 * @param northeastCorner
	 *            Location of the northeast corner of the area to be explored
	 * @param activityType
	 *            Activity type
	 * @param minCat
	 *            (Rides only) Minimum climb category to return
	 * @param maxCat
	 *            (Rides only) Maximum climb category to return
	 * @return A list of up to 10 segments within the area being explored
	 * @see javastrava.service.SegmentService#segmentExploreAsync(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint, javastrava.model.reference.StravaSegmentExplorerActivityType,
	 *      javastrava.model.reference.StravaClimbCategory, javastrava.model.reference.StravaClimbCategory)
	 */
	@Override
	public CompletableFuture<StravaSegmentExplorerResponse> segmentExploreAsync(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		return this.segmentService.segmentExploreAsync(southwestCorner, northeastCorner, activityType, minCat, maxCat);
	}

	/**
	 * <p>
	 * Star or unstar a segment
	 * </p>
	 *
	 * @param segmentId
	 *            The id of the segment to be starred
	 * @param starred
	 *            <code>true</code> if segment is to be starred, <code>false</code> if segment is to be unstarred
	 * @return Detailed representation of the segment
	 */
	@Override
	public StravaSegment starSegment(Integer segmentId, Boolean starred) {
		return this.segmentService.starSegment(segmentId, starred);
	}

	/**
	 * <p>
	 * Star or unstar a segment
	 * </p>
	 *
	 * @param segmentId
	 *            The id of the segment to be starred
	 * @param starred
	 *            <code>true</code> if segment is to be starred, <code>false</code> if segment is to be unstarred
	 * @return CompletableFuture which returns the detailed representation of the segment
	 */
	@Override
	public CompletableFuture<StravaSegment> starSegmentAsync(Integer segmentId, Boolean starred) {
		return this.segmentService.starSegmentAsync(segmentId, starred);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Statistics for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#statistics(java.lang.Integer)
	 */
	@Override
	public StravaStatistics statistics(final Integer athleteId) {
		return this.athleteService.statistics(athleteId);
	}

	/**
	 * @param athleteId
	 *            Athlete identifier
	 * @return Statistics for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.service.AthleteService#statisticsAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<StravaStatistics> statisticsAsync(final Integer athleteId) {
		return this.athleteService.statisticsAsync(athleteId);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param activity
	 *            Representation of fields to be updated on the activity
	 * @return The activity as updated on Strava
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.service.ActivityService#updateActivity(java.lang.Long, javastrava.model.StravaActivityUpdate)
	 */
	@Override
	public StravaActivity updateActivity(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException {
		return this.activityService.updateActivity(activityId, activity);
	}

	/**
	 * @param activityId
	 *            Activity identifier
	 * @param activity
	 *            Representation of fields to be updated on the activity
	 * @return The activity as updated on Strava
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 * @see javastrava.service.ActivityService#updateActivityAsync(java.lang.Long, javastrava.model.StravaActivityUpdate)
	 */
	@Override
	public CompletableFuture<StravaActivity> updateActivityAsync(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException {
		return this.activityService.updateActivityAsync(activityId, activity);
	}

	/**
	 * @param city
	 *            The city where the athlete wants Strava to think they live
	 * @param state
	 *            The state, county or whatever the athlete wants Strava to think they live
	 * @param country
	 *            The country where the athlete wants Strava to think they live
	 * @param sex
	 *            The gender the athlete wants Strava to think they identify with
	 * @param weight
	 *            The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 * @see javastrava.service.AthleteService#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String, javastrava.model.reference.StravaGender, java.lang.Float)
	 */
	@Override
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.athleteService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @param city
	 *            The city where the athlete wants Strava to think they live
	 * @param state
	 *            The state, county or whatever the athlete wants Strava to think they live
	 * @param country
	 *            The country where the athlete wants Strava to think they live
	 * @param sex
	 *            The gender the athlete wants Strava to think they identify with
	 * @param weight
	 *            The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 * @see javastrava.service.AthleteService#updateAuthenticatedAthleteAsync(java.lang.String, java.lang.String, java.lang.String, javastrava.model.reference.StravaGender, java.lang.Float)
	 */
	@Override
	public CompletableFuture<StravaAthlete> updateAuthenticatedAthleteAsync(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.athleteService.updateAuthenticatedAthleteAsync(city, state, country, sex, weight);
	}

	/**
	 * @param activityType
	 *            (Optional) Type of activity being uploaded
	 * @param name
	 *            (Optional) if not provided, will be populated using start date and location, if available
	 * @param description
	 *            (Optional)
	 * @param _private
	 *            (Optional) set to 1 to mark the resulting activity as private, 'view_private' permissions will be necessary to view the activity
	 * @param trainer
	 *            (Optional) activities without lat/lng info in the file are auto marked as stationary, set to 1 to force
	 * @param commute
	 *            (Optional) set to 1 to mark as commute
	 * @param dataType
	 *            possible values: fit, fit.gz, tcx, tcx.gz, gpx, gpx.gz
	 * @param externalId
	 *            (Optional) data filename will be used by default but should be a unique identifier
	 * @param file
	 *            the actual activity data, if gzipped the data_type must end with .gz
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.service.UploadService#upload(javastrava.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean,
	 *      java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer, final Boolean commute,
			final String dataType, final String externalId, final File file) {
		return this.uploadService.upload(activityType, name, description, _private, trainer, commute, dataType, externalId, file);
	}

	/**
	 * @param activityType
	 *            (Optional) Type of activity being uploaded
	 * @param name
	 *            (Optional) if not provided, will be populated using start date and location, if available
	 * @param description
	 *            (Optional)
	 * @param _private
	 *            (Optional) set to 1 to mark the resulting activity as private, 'view_private' permissions will be necessary to view the activity
	 * @param trainer
	 *            (Optional) activities without lat/lng info in the file are auto marked as stationary, set to 1 to force
	 * @param commute
	 *            (Optional) set to 1 to mark as commute
	 * @param dataType
	 *            possible values: fit, fit.gz, tcx, tcx.gz, gpx, gpx.gz
	 * @param externalId
	 *            (Optional) data filename will be used by default but should be a unique identifier
	 * @param file
	 *            the actual activity data, if gzipped the data_type must end with .gz
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.service.UploadService#uploadAsync(javastrava.model.reference.StravaActivityType, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean,
	 *      java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public CompletableFuture<StravaUploadResponse> uploadAsync(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer,
			final Boolean commute, final String dataType, final String externalId, final File file) {
		return this.uploadService.uploadAsync(activityType, name, description, _private, trainer, commute, dataType, externalId, file);
	}
}

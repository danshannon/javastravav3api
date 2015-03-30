package javastrava.api.v3.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javastrava.api.v3.auth.TokenService;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaActivityUpdate;
import javastrava.api.v3.model.StravaActivityZone;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.model.StravaComment;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.model.StravaLap;
import javastrava.api.v3.model.StravaMapPoint;
import javastrava.api.v3.model.StravaPhoto;
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
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.util.Paging;

/**
 * <p>
 * Convenience class for simplicity of access to the services. Provides a facade which delegates all service methods to elsewhere
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class Strava {
	/**
	 * Constructor requires a token
	 * 
	 * @param token
	 *            the access token to be used with calls to the Strava API
	 */
	public Strava(final Token token) {
		this.token = token;
		this.activityService = token.getService(ActivityService.class);
		this.athleteService = token.getService(AthleteService.class);
		this.clubService = token.getService(ClubService.class);
		this.gearService = token.getService(GearService.class);
		this.segmentEffortService = token.getService(SegmentEffortService.class);
		this.segmentService = token.getService(SegmentService.class);
		this.streamService = token.getService(StreamService.class);
		this.tokenService = token.getService(TokenService.class);
		this.uploadService = token.getService(UploadService.class);
	}

	private final ActivityService activityService;
	private final AthleteService athleteService;
	private final ClubService clubService;
	private final GearService gearService;
	private final SegmentEffortService segmentEffortService;
	private final SegmentService segmentService;
	private final StreamService streamService;
	private final TokenService tokenService;
	private final UploadService uploadService;
	private final Token token;

	/**
	 * @param scopes Authorisation scopes to check are in the token
	 * @return <code>true</code> if the token has all the identified scopes, <code>false</code> otherwise
	 */
	public boolean hasAuthorisationScopes(final AuthorisationScope... scopes) {	
		// Check all the scopes in the list are in the token
		for (AuthorisationScope scope : scopes) {
			if (!this.token.getScopes().contains(scope)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @param scopes Authorisation scopes to check are in the token
	 * @return <code>true</code> if the token has all the identified scopes AND NO MORE, <code>false</code> otherwise
	 */
	public boolean hasExactAuthorisationScopes(final AuthorisationScope... scopes) {
		if (!hasAuthorisationScopes(scopes)) {
			return false;
		}
		
		// Check all the scopes in the token are in the list
		List<AuthorisationScope> scopeList = Arrays.asList(scopes);
		for (AuthorisationScope scope : this.token.getScopes()) {
			if (!scopeList.contains(scope)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @param activityId The activity identifier
	 * @return The activity, if it exists, or <code>null</code> if it does not.
	 * @see javastrava.api.v3.service.ActivityService#getActivity(java.lang.Integer)
	 */
	public StravaActivity getActivity(final Integer activityId) {
		return this.activityService.getActivity(activityId);
	}

	/**
	 * @param activityId The activity identifier
	 * @param includeAllEfforts Whether to return efforts that Strava does not consider "important"
	 * @return The activity, if it exists, or <code>null</code> if it does not
	 * @see javastrava.api.v3.service.ActivityService#getActivity(java.lang.Integer, java.lang.Boolean)
	 */
	public StravaActivity getActivity(final Integer activityId, final Boolean includeAllEfforts) {
		return this.activityService.getActivity(activityId, includeAllEfforts);
	}

	/**
	 * @param activity The activity to be created on Strava
	 * @return The activity as has been created on Strava
	 * @see javastrava.api.v3.service.ActivityService#createManualActivity(javastrava.api.v3.model.StravaActivity)
	 */
	public StravaActivity createManualActivity(final StravaActivity activity) {
		return this.activityService.createManualActivity(activity);
	}

	/**
	 * @param activityId Activity identifier
	 * @param activity Representation of fields to be updated on the activity
	 * @return The activity as updated on Strava
	 * @throws NotFoundException If the activity with the given id does not exist
	 * @see javastrava.api.v3.service.ActivityService#updateActivity(java.lang.Integer, javastrava.api.v3.model.StravaActivityUpdate)
	 */
	public StravaActivity updateActivity(final Integer activityId, final StravaActivityUpdate activity) throws NotFoundException {
		return this.activityService.updateActivity(activityId, activity);
	}

	/**
	 * @param activityId The identifier of the activity to be deleted
	 * @return The activity that was deleted
	 * @throws NotFoundException If the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#deleteActivity(java.lang.Integer)
	 */
	public StravaActivity deleteActivity(final Integer activityId) throws NotFoundException {
		return this.activityService.deleteActivity(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @return List of all the authenticated athlete's activities
	 * @see javastrava.api.v3.service.ActivityService#listAllAuthenticatedAthleteActivities()
	 */
	public List<StravaActivity> listAllAuthenticatedAthleteActivities() {
		return this.activityService.listAllAuthenticatedAthleteActivities();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param before Only return activities before this date/time
	 * @param after Only return activities after this date/time
	 * @return List of all the authenticated athlete's activities, filtered by dates
	 * @see javastrava.api.v3.service.ActivityService#listAllAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	public List<StravaActivity> listAllAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAllAuthenticatedAthleteActivities(before, after);
	}

	/**
	 * @return First page of authenticated athlete's activities, sorted by start date (descending)
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities()
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities() {
		return this.activityService.listAuthenticatedAthleteActivities();
	}

	/**
	 * @param pagingInstruction Paging instruction
	 * @return List of authenticated athlete's activities corresponding to the paging instruction
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(javastrava.util.Paging)
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivities(pagingInstruction);
	}

	/**
	 * @param before Only return activities before this date/time
	 * @param after Only return activities after this date/time
	 * @return First page of authenticated athlete's activities, filtered by dates
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime)
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after) {
		return this.activityService.listAuthenticatedAthleteActivities(before, after);
	}

	/**
	 * @param before Only return activities before this date/time
	 * @param after Only return activities after this date/time
	 * @param pagingInstruction Paging instruction
	 * @return List of authenticated athlete's activities, filtered by dates, according to the paging instruction
	 * @see javastrava.api.v3.service.ActivityService#listAuthenticatedAthleteActivities(LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction) {
		return this.activityService.listAuthenticatedAthleteActivities(before, after, pagingInstruction);
	}

	/**
	 * @param pagingInstruction Paging instruction
	 * @return List of activities by friends of the authenticated athlete, according to the paging instruction, sorted by start date (descending)
	 * @see javastrava.api.v3.service.ActivityService#listFriendsActivities(javastrava.util.Paging)
	 */
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction) {
		return this.activityService.listFriendsActivities(pagingInstruction);
	}

	/**
	 * @return First page of the list of activities by friends of the authenticated athlete, sorted by start date (descending)
	 * @see javastrava.api.v3.service.ActivityService#listFriendsActivities()
	 */
	public List<StravaActivity> listFriendsActivities() {
		return this.activityService.listFriendsActivities();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS' ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @return All activities by friends of the authenticated athlete
	 * @see javastrava.api.v3.service.ActivityService#listAllFriendsActivities()
	 */
	public List<StravaActivity> listAllFriendsActivities() {
		return this.activityService.listAllFriendsActivities();
	}

	/**
	 * @param activityId Activity identifier
	 * @return The activity zones for the activity (if it exists), or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityZones(java.lang.Integer)
	 */
	public List<StravaActivityZone> listActivityZones(final Integer activityId) {
		return this.activityService.listActivityZones(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of laps belonging to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityLaps(java.lang.Integer)
	 */
	public List<StravaLap> listActivityLaps(final Integer activityId) {
		return this.activityService.listActivityLaps(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @param markdown Whether to include markdown in comments
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer, java.lang.Boolean)
	 */
	public List<StravaComment> listActivityComments(final Integer activityId, final Boolean markdown) {
		return this.activityService.listActivityComments(activityId, markdown);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of comments on the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer)
	 */
	public List<StravaComment> listActivityComments(final Integer activityId) {
		return this.activityService.listActivityComments(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaComment> listActivityComments(final Integer activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityComments(activityId, pagingInstruction);
	}

	/**
	 * @param activityId Activity identifier
	 * @param markdown Whether to include markdown in comments
	 * @param pagingInstruction Paging instruction
	 * @return List of comments on the activity, according to the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityComments(java.lang.Integer, java.lang.Boolean, javastrava.util.Paging)
	 */
	public List<StravaComment> listActivityComments(final Integer activityId, final Boolean markdown, final Paging pagingInstruction) {
		return this.activityService.listActivityComments(activityId, markdown, pagingInstruction);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of athletes who have given kudos to the activity, first page only, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityKudoers(java.lang.Integer)
	 */
	public List<StravaAthlete> listActivityKudoers(final Integer activityId) {
		return this.activityService.listActivityKudoers(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of athletes who have given kudos to the activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityKudoers(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaAthlete> listActivityKudoers(final Integer activityId, final Paging pagingInstruction) {
		return this.activityService.listActivityKudoers(activityId, pagingInstruction);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of photos attached to the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listActivityPhotos(java.lang.Integer)
	 */
	public List<StravaPhoto> listActivityPhotos(final Integer activityId) {
		return this.activityService.listActivityPhotos(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of activities that Strava has determined were done 'with' the identified activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listRelatedActivities(java.lang.Integer)
	 */
	public List<StravaActivity> listRelatedActivities(final Integer activityId) {
		return this.activityService.listRelatedActivities(activityId);
	}

	/**
	 * @param activityId Activity identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of activities that Strava has determined were done 'with' the identified activity, according with the paging instruction, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.ActivityService#listRelatedActivities(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaActivity> listRelatedActivities(final Integer activityId, final Paging pagingInstruction) {
		return this.activityService.listRelatedActivities(activityId, pagingInstruction);
	}

	/**
	 * @param activityId Activity identifier
	 * @param text Text of the comment (which may include markdown)
	 * @return The comment as stored on Strava
	 * @throws NotFoundException If the activity does not exist
	 * @throws BadRequestException If the comment is invalid (of zero length)
	 * @see javastrava.api.v3.service.ActivityService#createComment(java.lang.Integer, java.lang.String)
	 */
	public StravaComment createComment(final Integer activityId, final String text) throws NotFoundException, BadRequestException {
		return this.activityService.createComment(activityId, text);
	}

	/**
	 * @param activityId Activity identifier
	 * @param commentId Comment identifier
	 * @throws NotFoundException If the comment does not exist
	 * @see javastrava.api.v3.service.ActivityService#deleteComment(java.lang.Integer, java.lang.Integer)
	 */
	public void deleteComment(final Integer activityId, final Integer commentId) throws NotFoundException {
		this.activityService.deleteComment(activityId, commentId);
	}

	/**
	 * @param comment Comment to be deleted
	 * @throws NotFoundException If the comment does not exist on Strava
	 * @see javastrava.api.v3.service.ActivityService#deleteComment(javastrava.api.v3.model.StravaComment)
	 */
	public void deleteComment(final StravaComment comment) throws NotFoundException {
		this.activityService.deleteComment(comment);
	}

	/**
	 * @param activityId Activity identifier
	 * @throws NotFoundException If the activity does not exist on Strava
	 * @see javastrava.api.v3.service.ActivityService#giveKudos(java.lang.Integer)
	 */
	public void giveKudos(final Integer activityId) throws NotFoundException {
		this.activityService.giveKudos(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY COMMENTS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param activityId Activity identifier
	 * @return List of all comments on the activity
	 * @see javastrava.api.v3.service.ActivityService#listAllActivityComments(java.lang.Integer)
	 */
	public List<StravaComment> listAllActivityComments(final Integer activityId) {
		return this.activityService.listAllActivityComments(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY KUDOERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param activityId Activity identifier
	 * @return List of ALL athletes giving kudos to the activity
	 * @see javastrava.api.v3.service.ActivityService#listAllActivityKudoers(java.lang.Integer)
	 */
	public List<StravaAthlete> listAllActivityKudoers(final Integer activityId) {
		return this.activityService.listAllActivityKudoers(activityId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param activityId Activity identifier
	 * @return List of ALL related activities
	 * @see javastrava.api.v3.service.ActivityService#listAllRelatedActivities(java.lang.Integer)
	 */
	public List<StravaActivity> listAllRelatedActivities(final Integer activityId) {
		return this.activityService.listAllRelatedActivities(activityId);
	}

	/**
	 * @return The authenticated athlete
	 * @see javastrava.api.v3.service.AthleteService#getAuthenticatedAthlete()
	 */
	public StravaAthlete getAuthenticatedAthlete() {
		return this.athleteService.getAuthenticatedAthlete();
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return Athlete details, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#getAthlete(java.lang.Integer)
	 */
	public StravaAthlete getAthlete(final Integer athleteId) {
		return this.athleteService.getAthlete(athleteId);
	}

	/**
	 * @param city The city where the athlete wants Strava to think they live
	 * @param state The state, county or whatever the athlete wants Strava to think they live
	 * @param country The country where the athlete wants Strava to think they live
	 * @param sex The gender the athlete wants Strava to think they identify with
	 * @param weight The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 * @see javastrava.api.v3.service.AthleteService#updateAuthenticatedAthlete(java.lang.String, java.lang.String, java.lang.String,
	 *      javastrava.api.v3.model.reference.StravaGender, java.lang.Float)
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight) {
		return this.athleteService.updateAuthenticatedAthlete(city, state, country, sex, weight);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return List of segment efforts which represent KOM's for this athlete, first page only
	 * @see javastrava.api.v3.service.AthleteService#listAthleteKOMs(java.lang.Integer)
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId) {
		return this.athleteService.listAthleteKOMs(athleteId);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of segment efforts which represent KOM's for this athlete, according with the paging instruction
	 * @see javastrava.api.v3.service.AthleteService#listAthleteKOMs(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteKOMs(athleteId, pagingInstruction);
	}

	/**
	 * @return List of athletes the authenticated athlete is following, first page only
	 * @see javastrava.api.v3.service.AthleteService#listAuthenticatedAthleteFriends()
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends() {
		return this.athleteService.listAuthenticatedAthleteFriends();
	}

	/**
	 * @param pagingInstruction Paging instruction
	 * @return List of athletes the authenticated athlete is following, according with the paging instruction
	 * @see javastrava.api.v3.service.AthleteService#listAuthenticatedAthleteFriends(javastrava.util.Paging)
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction) {
		return this.athleteService.listAuthenticatedAthleteFriends(pagingInstruction);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of athletes the identified athlete is following, according with the paging instruction, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAthleteFriends(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthleteFriends(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return List of athletes the identified athlete is following, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAthleteFriends(java.lang.Integer)
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId) {
		return this.athleteService.listAthleteFriends(athleteId);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, first page only, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAthletesBothFollowing(java.lang.Integer)
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId) {
		return this.athleteService.listAthletesBothFollowing(athleteId);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of athletes being followed by both the authenticated athlete and the identified athlete, according with the paging instruction, or <code>null</code> if the identified athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAthletesBothFollowing(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId, final Paging pagingInstruction) {
		return this.athleteService.listAthletesBothFollowing(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return Statistics for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#statistics(java.lang.Integer)
	 */
	public StravaStatistics statistics(final Integer athleteId) {
		return this.athleteService.statistics(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param athleteId Athlete identifier
	 * @return List of ALL the athlete's friends, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAllAthleteFriends(java.lang.Integer)
	 */
	public List<StravaAthlete> listAllAthleteFriends(final Integer athleteId) {
		return this.athleteService.listAllAthleteFriends(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @return List of ALL the authenticated athlete's friends
	 * @see javastrava.api.v3.service.AthleteService#listAllAuthenticatedAthleteFriends()
	 */
	public List<StravaAthlete> listAllAuthenticatedAthleteFriends() {
		return this.athleteService.listAllAuthenticatedAthleteFriends();
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY KOMS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param athleteId Athlete identifier
	 * @return List of ALL segment efforts which represent a KOM for the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.AthleteService#listAllAthleteKOMs(java.lang.Integer)
	 */
	public List<StravaSegmentEffort> listAllAthleteKOMs(final Integer athleteId) {
		return this.athleteService.listAllAthleteKOMs(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param athleteId Athlete identifier
	 * @return List of ALL athletes that both the identified athlete and the authenticated athlete are following
	 * @see javastrava.api.v3.service.AthleteService#listAllAthletesBothFollowing(java.lang.Integer)
	 */
	public List<StravaAthlete> listAllAthletesBothFollowing(final Integer athleteId) {
		return this.athleteService.listAllAthletesBothFollowing(athleteId);
	}

	/**
	 * @param clubId Club identifier
	 * @return Club details, or <code>null</code> if the club does not exist
	 * @see javastrava.api.v3.service.ClubService#getClub(java.lang.Integer)
	 */
	public StravaClub getClub(final Integer clubId) {
		return this.clubService.getClub(clubId);
	}

	/**
	 * @return List of all clubs that the authenticated athlete is a member of
	 * @see javastrava.api.v3.service.ClubService#listAuthenticatedAthleteClubs()
	 */
	public List<StravaClub> listAuthenticatedAthleteClubs() {
		return this.clubService.listAuthenticatedAthleteClubs();
	}

	/**
	 * @param clubId Club identifier
	 * @return List of athletes who are members of the club, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(java.lang.Integer)
	 */
	public List<StravaAthlete> listClubMembers(final Integer clubId) {
		return this.clubService.listClubMembers(clubId);
	}

	/**
	 * @param clubId Club identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of athletes who are members of the club, according with the paging instruction
	 * @see javastrava.api.v3.service.ClubService#listClubMembers(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaAthlete> listClubMembers(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listClubMembers(clubId, pagingInstruction);
	}

	/**
	 * @param clubId Club identifier
	 * @return List of activities done by members of the club, in reverse order of start date, first page only, or <code>null</code> if the club does not exist
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(java.lang.Integer)
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer clubId) {
		return this.clubService.listRecentClubActivities(clubId);
	}

	/**
	 * @param clubId Club identifier
	 * @param pagingInstruction Paging instruction
	 * @return list of activities done by members of the club, in reverse order of start date, according with the paging instruction, or <code>null</code> if the club does not exist. Note that Strava returns a maximum of 200 recent activities.
	 * @see javastrava.api.v3.service.ClubService#listRecentClubActivities(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer clubId, final Paging pagingInstruction) {
		return this.clubService.listRecentClubActivities(clubId, pagingInstruction);
	}

	/**
	 * @param clubId Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.api.v3.service.ClubService#joinClub(java.lang.Integer)
	 */
	public StravaClubMembershipResponse joinClub(final Integer clubId) {
		return this.clubService.joinClub(clubId);
	}

	/**
	 * @param clubId Club identifier
	 * @return Response from Strava indicating success/failure
	 * @see javastrava.api.v3.service.ClubService#leaveClub(java.lang.Integer)
	 */
	public StravaClubMembershipResponse leaveClub(final Integer clubId) {
		return this.clubService.leaveClub(clubId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - CLUBS WITH MANY MEMBERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 * @param clubId Club identifier
	 * @return List of ALL members of the club, or <code>null</code> if the club does not exist
	 * @see javastrava.api.v3.service.ClubService#listAllClubMembers(java.lang.Integer)
	 */
	public List<StravaAthlete> listAllClubMembers(final Integer clubId) {
		return this.clubService.listAllClubMembers(clubId);
	}

	/**
	 * @param clubId Club identifier
	 * @return List of ALL recent activities by members of the club (note that Strava caps this at 200 activities), or <code>null</code> if the club does not exist
	 * @see javastrava.api.v3.service.ClubService#listAllRecentClubActivities(java.lang.Integer)
	 */
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId) {
		return this.clubService.listAllRecentClubActivities(clubId);
	}

	/**
	 * @param gearId Gear identifier
	 * @return Gear details, or <code>null</code> if the gear does not exist
	 * @see javastrava.api.v3.service.GearService#getGear(java.lang.String)
	 */
	public StravaGear getGear(final String gearId) {
		return this.gearService.getGear(gearId);
	}

	/**
	 * @param segmentEffortId Segment effort identifier
	 * @return Segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.api.v3.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 */
	public StravaSegmentEffort getSegmentEffort(final Long segmentEffortId) {
		return this.segmentEffortService.getSegmentEffort(segmentEffortId);
	}

	/**
	 * @param segmentId Segment identifier
	 * @return Segment details, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.SegmentService#getSegment(java.lang.Integer)
	 */
	public StravaSegment getSegment(final Integer segmentId) {
		return this.segmentService.getSegment(segmentId);
	}

	/**
	 * @param pagingInstruction Paging instruction
	 * @return List of segments starred by the authenticated athlete, in accordance with the paging instruction
	 * @see javastrava.api.v3.service.SegmentService#listAuthenticatedAthleteStarredSegments(javastrava.util.Paging)
	 */
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction) {
		return this.segmentService.listAuthenticatedAthleteStarredSegments(pagingInstruction);
	}

	/**
	 * @return List of segments starred by the authenticated athlete, first page only
	 * @see javastrava.api.v3.service.SegmentService#listAuthenticatedAthleteStarredSegments()
	 */
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments() {
		return this.segmentService.listAuthenticatedAthleteStarredSegments();
	}

	/**
	 * @param athleteId Athlete identifier
	 * @param pagingInstruction Paging instruction
	 * @return List of segments starred by the identified athlete, in accordance with the paging instruction, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.SegmentService#listStarredSegments(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaSegment> listStarredSegments(final Integer athleteId, final Paging pagingInstruction) {
		return this.segmentService.listStarredSegments(athleteId, pagingInstruction);
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return List of segments starred by the identified athlete, first page only, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.SegmentService#listStarredSegments(java.lang.Integer)
	 */
	public List<StravaSegment> listStarredSegments(final Integer athleteId) {
		return this.segmentService.listStarredSegments(athleteId);
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
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local
	 *         ascending or by elapsed time if an athleteId is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime,
	 *      javastrava.util.Paging)
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal, final Paging pagingInstruction) {
		return this.segmentService.listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal, pagingInstruction);
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
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local
	 *         ascending or by elapsed time if an athleteId is provided. If the segment or athlete do not exist, then returns <code>null</code>
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal) {
		return this.segmentService.listSegmentEfforts(segmentId, athleteId, startDateLocal, endDateLocal);
	}

	/**
	 * @param segmentId Segment identifier
	 * @param pagingInstruction Paging instruction
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local
	 *         ascending. If the segment does not exist, then returns <code>null</code>
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer, javastrava.util.Paging)
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.listSegmentEfforts(segmentId, pagingInstruction);
	}

	/**
	 * @param segmentId Segment identifier
	 * @return Returns a list of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local
	 *         ascending. If the segment does not exist, then returns <code>null</code>
	 * @see javastrava.api.v3.service.SegmentService#listSegmentEfforts(java.lang.Integer)
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId) {
		return this.segmentService.listSegmentEfforts(segmentId);
	}

	/**
	 * @param segmentId Segment identifier
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is
	 *            following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @param pagingInstruction
	 *            (Optional) Paging instruction
	 * @param contextEntries (Optional) number of entries to return as athlete context either side of the athlete (default is 2, maximum is 15)
	 * @return Segment leaderboard, as per filters
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender,
	 *      javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaLeaderboardDateRange, javastrava.util.Paging, java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction, final Integer contextEntries) {
		return this.segmentService.getSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange, pagingInstruction, contextEntries);
	}

	/**
	 * @param segmentId Segment identifier
	 * @param pagingInstruction Paging instruction
	 * @return Segment leaderboard, with entries in accordance with the paging instruction
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(java.lang.Integer, javastrava.util.Paging)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final Paging pagingInstruction) {
		return this.segmentService.getSegmentLeaderboard(segmentId, pagingInstruction);
	}

	/**
	 * @param segmentId Segment identifier
	 * @return Leaderboard, with first page of entries
	 * @see javastrava.api.v3.service.SegmentService#getSegmentLeaderboard(java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId) {
		return this.segmentService.getSegmentLeaderboard(segmentId);
	}

	/**
	 * @param southwestCorner Location of the southwest corner of the area to be explored
	 * @param northeastCorner Location of the northeast corner of the area to be explored
	 * @param activityType Activity type
	 * @param minCat (Rides only) Minimum climb category to return 
	 * @param maxCat (Rides only) Maximum climb categort to return
	 * @return A list of up to 10 segments within the area being explored
	 * @see javastrava.api.v3.service.SegmentService#segmentExplore(javastrava.api.v3.model.StravaMapPoint, javastrava.api.v3.model.StravaMapPoint,
	 *      javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType, javastrava.api.v3.model.reference.StravaClimbCategory,
	 *      javastrava.api.v3.model.reference.StravaClimbCategory)
	 */
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat) {
		return this.segmentService.segmentExplore(southwestCorner, northeastCorner, activityType, minCat, maxCat);
	}

	/**
	 * @return List of ALL segments starred by the authenticated athlete
	 * @see javastrava.api.v3.service.SegmentService#listAllAuthenticatedAthleteStarredSegments()
	 */
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments() {
		return this.segmentService.listAllAuthenticatedAthleteStarredSegments();
	}

	/**
	 * @param athleteId Athlete identifier
	 * @return list of ALL segments starred by the identified athlete, or <code>null</code> if the athlete does not exist
	 * @see javastrava.api.v3.service.SegmentService#listAllStarredSegments(java.lang.Integer)
	 */
	public List<StravaSegment> listAllStarredSegments(final Integer athleteId) {
		return this.segmentService.listAllStarredSegments(athleteId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 * @param segmentId Segment identifier
	 * @return The WHOLE leaderboard for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer)
	 */
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId) {
		return this.segmentService.getAllSegmentLeaderboard(segmentId);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @param gender
	 *            (Optional) {@link StravaGender StravaGender} to filter results by
	 * @param ageGroup
	 *            (Optional) {@link StravaAgeGroup Age group} to filter results by
	 * @param weightClass
	 *            (Optional) {@link StravaWeightClass Weight class} to filter results by
	 * @param following
	 *            (Optional) If <code>true</code> then will return only results for {@link StravaAthlete athletes} that the currently authenticated athlete is
	 *            following
	 * @param clubId
	 *            (Optional) Id of {@link StravaClub} to filter results by
	 * @param dateRange
	 *            (Optional) Use to set to return results for this year, this month, this week etc.
	 * @return The WHOLE leaderboard for the segment, filtered as required, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.SegmentService#getAllSegmentLeaderboard(java.lang.Integer, javastrava.api.v3.model.reference.StravaGender,
	 *      javastrava.api.v3.model.reference.StravaAgeGroup, javastrava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      javastrava.api.v3.model.reference.StravaLeaderboardDateRange)
	 */
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange) {
		return this.segmentService.getAllSegmentLeaderboard(segmentId, gender, ageGroup, weightClass, following, clubId, dateRange);
	}

	/**
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF EFFORTS, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 * @param segmentId Segment identifier
	 * @return List of ALL efforts on the segment, ever, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.SegmentService#listAllSegmentEfforts(java.lang.Integer)
	 */
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId) {
		return this.segmentService.listAllSegmentEfforts(segmentId);
	}

	/**
	 * @param segmentId Segment identifier
	 * @param athleteId (Optional) Athlete identifier
	 * @param startDate (Optional) Do not return activities before this date/time
	 * @param endDate (Optional) Do not return activities after this date/time
	 * @return List of ALL efforts on the segment, filtered as required, or <code>null</code> if the segment or athlete do not exist
	 * @see javastrava.api.v3.service.SegmentService#listAllSegmentEfforts(java.lang.Integer, java.lang.Integer, LocalDateTime, LocalDateTime)
	 */
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate) {
		return this.segmentService.listAllSegmentEfforts(segmentId, athleteId, startDate, endDate);
	}

	/**
	 * @param activityId Activity identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist.
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(java.lang.Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType[])
	 */
	public List<StravaStream> getActivityStreams(final Integer activityId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getActivityStreams(activityId, resolution, seriesType, types);
	}

	/**
	 * @param activityId Activity identifier
	 * @return List of streams for the activity, or <code>null</code> if the activity does not exist
	 * @see javastrava.api.v3.service.StreamService#getActivityStreams(java.lang.Integer)
	 */
	public List<StravaStream> getActivityStreams(final Integer activityId) {
		return this.streamService.getActivityStreams(activityId);
	}

	/**
	 * @param segmentEffortId Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return List of streams for the segment effort, or <code>null</code> if the effort does not exist
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(java.lang.Long, javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType[])
	 */
	public List<StravaStream> getEffortStreams(final Long segmentEffortId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getEffortStreams(segmentEffortId, resolution, seriesType, types);
	}

	/**
	 * @param segmentEffortId Segment effort identifier
	 * @return List of streams for the effort, or <code>null</code> if the segment effort does not exist
	 * @see javastrava.api.v3.service.StreamService#getEffortStreams(java.lang.Long)
	 */
	public List<StravaStream> getEffortStreams(final Long segmentEffortId) {
		return this.streamService.getEffortStreams(segmentEffortId);
	}

	/**
	 * @param segmentId Segment identifier
	 * @param types
	 *            List of types, if the activity does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down
	 *            sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is
	 *            being reduced
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(java.lang.Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType,
	 *      javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType[])
	 */
	public List<StravaStream> getSegmentStreams(final Integer segmentId, final StravaStreamResolutionType resolution, final StravaStreamSeriesDownsamplingType seriesType,
			final StravaStreamType... types) {
		return this.streamService.getSegmentStreams(segmentId, resolution, seriesType, types);
	}

	/**
	 * @param segmentId Segment identifier
	 * @return List of streams for the segment, or <code>null</code> if the segment does not exist
	 * @see javastrava.api.v3.service.StreamService#getSegmentStreams(java.lang.Integer)
	 */
	public List<StravaStream> getSegmentStreams(final Integer segmentId) {
		return this.streamService.getSegmentStreams(segmentId);
	}

	/**
	 * @param accessToken token to be deauthorised
	 * @return Response from Strava
	 * @see javastrava.api.v3.auth.TokenService#deauthorise(javastrava.api.v3.auth.model.Token)
	 */
	public TokenResponse deauthorise(final Token accessToken) {
		return this.tokenService.deauthorise(accessToken);
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
	 * @param dataType
	 *            possible values: fit, fit.gz, tcx, tcx.gz, gpx, gpx.gz
	 * @param externalId
	 *            (Optional) data filename will be used by default but should be a unique identifier
	 * @param file
	 *            the actual activity data, if gzipped the data_type must end with .gz
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.api.v3.service.UploadService#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String,
	 *      java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private, final Boolean trainer, final String dataType,
			final String externalId, final File file) {
		return this.uploadService.upload(activityType, name, description, _private, trainer, dataType, externalId, file);
	}

	/**
	 * @param uploadId Upload identifier
	 * @return Returns an Upload response object which includes the status of the upload and the upload id
	 * @see javastrava.api.v3.service.UploadService#checkUploadStatus(java.lang.Integer)
	 */
	public StravaUploadResponse checkUploadStatus(final Integer uploadId) {
		return this.uploadService.checkUploadStatus(uploadId);
	}
}

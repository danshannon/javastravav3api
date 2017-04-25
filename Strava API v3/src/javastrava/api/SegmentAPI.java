package javastrava.api;

import java.time.LocalDateTime;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaSegmentExplorerResponse;
import javastrava.model.StravaSegmentLeaderboard;
import javastrava.model.reference.StravaAgeGroup;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaLeaderboardDateRange;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSegmentExplorerActivityType;
import javastrava.model.reference.StravaWeightClass;
import javastrava.service.SegmentService;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definitions for the {@link SegmentService} Strava endpoints
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface SegmentAPI {
	/**
	 * @see javastrava.service.SegmentService#getSegment(java.lang.Integer)
	 *
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return The Segment
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 */
	@GET("/segments/{id}")
	public StravaSegment getSegment(@Path("id") final Integer segmentId) throws NotFoundException;

	/**
	 * @see javastrava.service.SegmentService#getSegment(java.lang.Integer)
	 *
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 */
	@GET("/segments/{id}")
	public void getSegment(@Path("id") final Integer segmentId, final StravaAPICallback<StravaSegment> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer, StravaLeaderboardDateRange,
	 *      javastrava.util.Paging, Integer)
	 *
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
	 */
	@GET("/segments/{id}/leaderboard")
	public StravaSegmentLeaderboard getSegmentLeaderboard(@Path("id") final Integer segmentId, @Query("gender") final StravaGender gender, @Query("age_group") final StravaAgeGroup ageGroup,
			@Query("weight_class") final StravaWeightClass weightClass, @Query("following") final Boolean following, @Query("club_id") final Integer clubId,
			@Query("date_range") final StravaLeaderboardDateRange dateRange, @Query("page") final Integer page, @Query("per_page") final Integer perPage,
			@Query("context_entries") final Integer contextEntries) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer, StravaLeaderboardDateRange,
	 *      javastrava.util.Paging, Integer)
	 *
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
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/segments/{id}/leaderboard")
	public void getSegmentLeaderboard(@Path("id") final Integer segmentId, @Query("gender") final StravaGender gender, @Query("age_group") final StravaAgeGroup ageGroup,
			@Query("weight_class") final StravaWeightClass weightClass, @Query("following") final Boolean following, @Query("club_id") final Integer clubId,
			@Query("date_range") final StravaLeaderboardDateRange dateRange, @Query("page") final Integer page, @Query("per_page") final Integer perPage,
			@Query("context_entries") final Integer contextEntries, final StravaAPICallback<StravaSegmentLeaderboard> callback) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#getSegmentLeaderboard(Integer, StravaGender, StravaAgeGroup, StravaWeightClass, Boolean, Integer, StravaLeaderboardDateRange,
	 *      javastrava.util.Paging, Integer)
	 *
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
	 */
	@GET("/segments/{id}/leaderboard")
	public Response getSegmentLeaderboardRaw(@Path("id") final Integer segmentId, @Query("gender") final StravaGender gender, @Query("age_group") final StravaAgeGroup ageGroup,
			@Query("weight_class") final StravaWeightClass weightClass, @Query("following") final Boolean following, @Query("club_id") final Integer clubId,
			@Query("date_range") final StravaLeaderboardDateRange dateRange, @Query("page") final Integer page, @Query("per_page") final Integer perPage,
			@Query("context_entries") final Integer contextEntries) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#getSegment(java.lang.Integer)
	 *
	 * @param segmentId
	 *            The unique identifier of the segment
	 * @return The Segment
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 */
	@GET("/segments/{id}")
	public Response getSegmentRaw(@Path("id") final Integer segmentId) throws NotFoundException;

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments(javastrava.util.Paging)
	 *
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/segments/starred")
	public StravaSegment[] listAuthenticatedAthleteStarredSegments(@Query("page") final Integer page, @Query("per_page") final Integer perPage) throws BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments(javastrava.util.Paging)
	 *
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @param callback
	 *            The callback to execute on completion
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/segments/starred")
	public void listAuthenticatedAthleteStarredSegments(@Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaSegment[]> callback)
			throws BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listAuthenticatedAthleteStarredSegments(javastrava.util.Paging)
	 *
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @return An array of segments
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/segments/starred")
	public Response listAuthenticatedAthleteStarredSegmentsRaw(@Query("page") final Integer page, @Query("per_page") final Integer perPage) throws BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(Integer, Integer, LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 *
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
	 */
	@GET("/segments/{id}/all_efforts")
	public StravaSegmentEffort[] listSegmentEfforts(@Path("id") final Integer segmentId, @Query("athlete_id") final Integer athleteId, @Query("start_date_local") final String start,
			@Query("end_date_local") final String end, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(Integer, Integer, LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 *
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
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the segment with the given id doesn't exist
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/segments/{id}/all_efforts")
	public void listSegmentEfforts(@Path("id") final Integer segmentId, @Query("athlete_id") final Integer athleteId, @Query("start_date_local") final String start,
			@Query("end_date_local") final String end, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaSegmentEffort[]> callback)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listSegmentEfforts(Integer, Integer, LocalDateTime, LocalDateTime, javastrava.util.Paging)
	 *
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
	 */
	@GET("/segments/{id}/all_efforts")
	public Response listSegmentEffortsRaw(@Path("id") final Integer segmentId, @Query("athlete_id") final Integer athleteId, @Query("start_date_local") final String start,
			@Query("end_date_local") final String end, @Query("page") final Integer page, @Query("per_page") final Integer perPage) throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer, Paging)
	 *
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
	@GET("/athletes/{id}/segments/starred")
	public StravaSegment[] listStarredSegments(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer, Paging)
	 *
	 * @param athleteId
	 *            The id of the athlete whose starred segments are to be retrieved
	 * @param page
	 *            (optional) Page number to be returned
	 * @param perPage
	 *            (optional) Number of entries to return per page
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the segment with the given id does not exist
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 * @throws BadRequestException
	 *             If the paging instructions are invalid
	 */
	@GET("/athletes/{id}/segments/starred")
	public void listStarredSegments(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage, final StravaAPICallback<StravaSegment[]> callback)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#listStarredSegments(java.lang.Integer, Paging)
	 *
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
	@GET("/athletes/{id}/segments/starred")
	public Response listStarredSegmentsRaw(@Path("id") final Integer athleteId, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.SegmentService#segmentExplore(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint, StravaSegmentExplorerActivityType,
	 *      StravaClimbCategory, StravaClimbCategory)
	 *
	 * @param bounds
	 *            Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType
	 *            (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory
	 *            (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory
	 *            (Optional) Maximum climb category for which rides should be returned
	 * @return A response full of slightly weird-looking segments
	 */
	@GET("/segments/explore")
	public StravaSegmentExplorerResponse segmentExplore(@Query("bounds") final String bounds, @Query("activity_type") final StravaSegmentExplorerActivityType activityType,
			@Query("min_cat") final StravaClimbCategory minCategory, @Query("max_cat") final StravaClimbCategory maxCategory);

	/**
	 * @see javastrava.service.SegmentService#segmentExplore(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint, StravaSegmentExplorerActivityType,
	 *      StravaClimbCategory, StravaClimbCategory)
	 *
	 * @param bounds
	 *            Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType
	 *            (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory
	 *            (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory
	 *            (Optional) Maximum climb category for which rides should be returned
	 * @param callback
	 *            The callback to execute on completion
	 */
	@GET("/segments/explore")
	public void segmentExplore(@Query("bounds") final String bounds, @Query("activity_type") final StravaSegmentExplorerActivityType activityType,
			@Query("min_cat") final StravaClimbCategory minCategory, @Query("max_cat") final StravaClimbCategory maxCategory, final StravaAPICallback<StravaSegmentExplorerResponse> callback);

	/**
	 * @see javastrava.service.SegmentService#segmentExplore(javastrava.model.StravaMapPoint, javastrava.model.StravaMapPoint, StravaSegmentExplorerActivityType,
	 *      StravaClimbCategory, StravaClimbCategory)
	 *
	 * @param bounds
	 *            Pair of co-ordinates defining a box which is to be searched for segments
	 * @param activityType
	 *            (Optional) Activity type to filter by (default is 'ride')
	 * @param minCategory
	 *            (Optional) Minimum climb category for which rides should be returned
	 * @param maxCategory
	 *            (Optional) Maximum climb category for which rides should be returned
	 * @return A response full of slightly weird-looking segments
	 */
	@GET("/segments/explore")
	public Response segmentExploreRaw(@Query("bounds") final String bounds, @Query("activity_type") final StravaSegmentExplorerActivityType activityType,
			@Query("min_cat") final StravaClimbCategory minCategory, @Query("max_cat") final StravaClimbCategory maxCategory);

	/**
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
	@PUT("/segments/{id}/starred")
	public StravaSegment starSegment(@Path("id") final Integer segmentId, @Query("starred") final Boolean starred) throws NotFoundException, BadRequestException, UnauthorizedException;

	/**
	 * @param segmentId
	 *            Identifier of the segment to be starred or unstarred
	 * @param starred
	 *            <code>true</code> if the segment is to be starred, <code>false</code> if to be unstarred
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If required parameters are not provided
	 * @throws UnauthorizedException
	 *             If there is a security or privacy violation
	 */
	@PUT("/segments/{id}/starred")
	public void starSegment(@Path("id") final Integer segmentId, @Query("starred") final Boolean starred, final StravaAPICallback<StravaSegment> callback);

}

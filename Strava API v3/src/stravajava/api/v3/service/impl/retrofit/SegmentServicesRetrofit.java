/**
 * 
 */
package stravajava.api.v3.service.impl.retrofit;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaSegment;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.StravaSegmentExplorerResponse;
import stravajava.api.v3.model.StravaSegmentLeaderboard;
import stravajava.api.v3.model.reference.StravaAgeGroup;
import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.model.reference.StravaLeaderboardDateRange;
import stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import stravajava.api.v3.model.reference.StravaWeightClass;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * @author Dan Shannon
 *
 */
public interface SegmentServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegment(java.lang.Integer)
	 */
	@GET("/segments/{id}")
	public StravaSegment getSegment(@Path("id") final Integer id) throws NotFoundException, UnauthorizedException;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listAuthenticatedAthleteStarredSegments(java.lang.Integer, java.lang.Integer)
	 */
	@GET("/segments/starred")
	public StravaSegment[] listAuthenticatedAthleteStarredSegments(@Query("page") final Integer page, @Query("per_page") final Integer perPage);

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listStarredSegments(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/athletes/{id}/segments/starred")
	public StravaSegment[] listStarredSegments(@Path("id") final Integer id, @Query("page") final Integer page, @Query("per_page") final Integer perPage)
			throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#listSegmentEfforts(java.lang.Integer, java.lang.Integer, java.util.Calendar, java.util.Calendar,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@GET("/segments/{id}/all_efforts")
	public StravaSegmentEffort[] listSegmentEfforts(@Path("id") final Integer id, @Query("athlete_id") final Integer athleteId,
			@Query("start_date_local") final Date startDateLocal, @Query("end_date_local") final Date endDateLocal, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#getSegmentLeaderboard(java.lang.Integer, stravajava.api.v3.model.reference.StravaGender,
	 *      stravajava.api.v3.model.reference.StravaAgeGroup, stravajava.api.v3.model.reference.StravaWeightClass, java.lang.Boolean, java.lang.Integer,
	 *      stravajava.api.v3.model.reference.StravaLeaderboardDateRange, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/segments/{id}/leaderboard")
	public StravaSegmentLeaderboard getSegmentLeaderboard(@Path("id") final Integer id, @Query("gender") final StravaGender gender,
			@Query("age_group") final StravaAgeGroup ageGroup, @Query("weight_class") final StravaWeightClass weightClass, @Query("following") final Boolean following,
			@Query("club_id") final Integer clubId, @Query("date_range") final StravaLeaderboardDateRange dateRange, @Query("page") final Integer page,
			@Query("per_page") final Integer perPage) throws NotFoundException;

	/**
	 * @see stravajava.api.v3.service.SegmentServices#segmentExplore(stravajava.api.v3.model.StravaMapPoint, stravajava.api.v3.model.StravaMapPoint,
	 *      stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType, java.lang.Integer, java.lang.Integer)
	 */
	@GET("/segments/explore")
	public StravaSegmentExplorerResponse segmentExplore(@Query("bounds") final String bounds, @Query("activity_type") final StravaSegmentExplorerActivityType activityType,
			@Query("min_cat") final StravaClimbCategory minCategory, @Query("max_cat") final StravaClimbCategory maxCategory);

}

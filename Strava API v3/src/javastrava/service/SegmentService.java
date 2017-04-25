package javastrava.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaAthlete;
import javastrava.model.StravaClub;
import javastrava.model.StravaMapPoint;
import javastrava.model.StravaSegment;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaSegmentExplorerResponse;
import javastrava.model.StravaSegmentLeaderboard;
import javastrava.model.StravaSegmentLeaderboardEntry;
import javastrava.model.reference.StravaAgeGroup;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaLeaderboardDateRange;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSegmentExplorerActivityType;
import javastrava.model.reference.StravaWeightClass;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;

/**
 * <p>
 * {@link StravaSegment Segments} are specific sections of road.
 * </p>
 *
 * <p>
 * {@link StravaAthlete Athletes'} {@link StravaSegmentEffort efforts} are compared on these segments and {@link StravaSegmentLeaderboard leaderboards} are created.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface SegmentService extends StravaService {
	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns THE ENTIRE leaderboard.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns the ENTIRE leaderboard.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
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
	 * @return Returns an the entire set of {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is deprecated,
	 *         use entry_count instead.
	 */
	public StravaSegmentLeaderboard getAllSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - POPULAR SEGMENTS CAN HAVE TENS OF THOUSANDS OF ATHLETES ON THE LEADERBOARD, REQUIRING A VERY LARGE NUMBER OF CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns THE ENTIRE leaderboard.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns the ENTIRE leaderboard.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
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
	 * @return Returns an the entire set of {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is deprecated,
	 *         use entry_count instead.
	 */
	public CompletableFuture<StravaSegmentLeaderboard> getAllSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange);

	/**
	 * <p>
	 * Retrieve details about a specific {@link StravaSegment segment}.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#retrieve">http://strava.github.io/api/v3/segments/#retrieve</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} to be retrieved
	 * @return Returns a {@link StravaResourceState#DETAILED detailed representation} of the {@link StravaSegment}.
	 * @throws UnauthorizedException
	 *             If authorisation fails
	 */
	public StravaSegment getSegment(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve details about a specific {@link StravaSegment segment}.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#retrieve">http://strava.github.io/api/v3/segments/#retrieve</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} to be retrieved
	 * @return Returns a {@link StravaResourceState#DETAILED detailed representation} of the {@link StravaSegment}.
	 * @throws UnauthorizedException
	 *             If authorisation fails
	 */
	public CompletableFuture<StravaSegment> getSegmentAsync(final Integer segmentId);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of leaderboard entries.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @param pagingInstruction
	 *            (Optional) Page number, Number of results per page (max 200)
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final Paging pagingInstruction);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
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
	 * @param pagingInstruction
	 *            (Optional) Page to start at for pagination
	 * @param contextEntries
	 *            (Optional) number of entries to return as athlete context either side of the athlete (default is 2, maximum is 15)
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public StravaSegmentLeaderboard getSegmentLeaderboard(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup, final StravaWeightClass weightClass,
			final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction, final Integer contextEntries);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of leaderboard entries.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
	 *
	 * @param segmentId
	 *            The id of the segment to return a leaderboard for
	 * @param pagingInstruction
	 *            (Optional) Page number, Number of results per page (max 200)
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final Paging pagingInstruction);

	/**
	 * <p>
	 * {@link StravaSegmentLeaderboard Leaderboards} represent the ranking of {@link StravaAthlete athletes} on specific {@link StravaSegment segments}.
	 * </p>
	 *
	 * <p>
	 * Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/leaderboard
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#leaderboard">http://strava.github.io/api/v3/segments/#leaderboard</a>
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
	 * @param pagingInstruction
	 *            (Optional) Page to start at for pagination
	 * @param contextEntries
	 *            (Optional) number of entries to return as athlete context either side of the athlete (default is 2, maximum is 15)
	 * @return Returns an array of up to 10, by default, {@link StravaSegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is
	 *         deprecated, use entry_count instead.
	 */
	public CompletableFuture<StravaSegmentLeaderboard> getSegmentLeaderboardAsync(final Integer segmentId, final StravaGender gender, final StravaAgeGroup ageGroup,
			final StravaWeightClass weightClass, final Boolean following, final Integer clubId, final StravaLeaderboardDateRange dateRange, final Paging pagingInstruction,
			final Integer contextEntries);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of ALL the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public List<StravaSegment> listAllAuthenticatedAthleteStarredSegments();

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of ALL the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listAllAuthenticatedAthleteStarredSegmentsAsync();

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns ALL segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. All segment efforts will be returned.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDate
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDate
	 *            (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listAllSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns ALL segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. All segment efforts will be returned.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDate
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDate
	 *            (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listAllSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDate, final LocalDateTime endDate);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of ALL the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns ALL starred segments.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public List<StravaSegment> listAllStarredSegments(final Integer athleteId);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of ALL the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns ALL starred segments.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listAllStarredSegmentsAsync(final Integer athleteId);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segments.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments();

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param pagingInstruction
	 *            (Optional) paging instructions
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public List<StravaSegment> listAuthenticatedAthleteStarredSegments(final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segments.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync();

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param pagingInstruction
	 *            (Optional) paging instructions
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the authenticated {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listAuthenticatedAthleteStarredSegmentsAsync(final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param pagingInstruction
	 *            (Optional) Page to start at for pagination / number of results per page
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal,
			final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param pagingInstruction
	 *            (Optional) paging parameters
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public List<StravaSegmentEffort> listSegmentEfforts(final Integer segmentId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segment efforts.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}, filtered by {@link StravaAthlete} and/or a date range.
	 * </p>
	 *
	 * <p>
	 * Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.
	 * </p>
	 *
	 * <p>
	 * Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the
	 * past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA
	 * can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param athleteId
	 *            (Optional) id of the {@link StravaAthlete} to filter results by
	 * @param startDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param endDateLocal
	 *            (Optional) ISO 8601 formatted date time
	 * @param pagingInstruction
	 *            (Optional) Page to start at for pagination / number of results per page
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Integer athleteId, final LocalDateTime startDateLocal, final LocalDateTime endDateLocal,
			final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve an array of {@link StravaSegmentEffort segment efforts}, for a given {@link StravaSegment}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the segment does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/:id/all_efforts
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#efforts">http://strava.github.io/api/v3/segments/#efforts</a>
	 *
	 * @param segmentId
	 *            The id of the {@link StravaSegment} for which {@link StravaSegmentEffort segment efforts} are to be returned
	 * @param pagingInstruction
	 *            (Optional) paging parameters
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary {@link StravaResourceState representations} sorted by start_date_local ascending or by elapsed_time if an
	 *         athlete_id is provided.
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listSegmentEffortsAsync(final Integer segmentId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segments
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public List<StravaSegment> listStarredSegments(final Integer athleteId);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @param pagingInstruction
	 *            (Optional) paging instructions
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public List<StravaSegment> listStarredSegments(final Integer athleteId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of segments
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId);

	/**
	 * <p>
	 * Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete athlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the athlete with the given id does not exist.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/starred
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a>
	 *
	 * @param athleteId
	 *            Identifier of the {@link StravaAthlete} for which starred {@link StravaSegment segments} are to be returned
	 * @param pagingInstruction
	 *            (Optional) paging instructions
	 * @return Returns a {@link StravaResourceState#SUMMARY summary representation} of the {@link StravaSegment segments} starred by the identified {@link StravaAthlete}.
	 */
	public CompletableFuture<List<StravaSegment>> listStarredSegmentsAsync(final Integer athleteId, final Paging pagingInstruction);

	/**
	 * <p>
	 * This endpoint can be used to find popular segments within a given area (defined by the southwest and northeast corners of the area).
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns up to 10 segments only.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/explore
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#explore">http://strava.github.io/api/v3/segments/#explore</a>
	 *
	 * @param southwestCorner
	 *            The southwest corner of the area to be explored
	 * @param northeastCorner
	 *            The northeast corner of the area to be explored
	 * @param activityType
	 *            (Optional) "running" or "riding", default is riding
	 * @param minCat
	 *            (Optional) Minimum climb category filter
	 * @param maxCat
	 *            (Optional) Maximum climb category filter
	 * @return Returns an array of up to 10 segment objects
	 */
	public StravaSegmentExplorerResponse segmentExplore(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner, final StravaSegmentExplorerActivityType activityType,
			final StravaClimbCategory minCat, final StravaClimbCategory maxCat);

	/**
	 * <p>
	 * This endpoint can be used to find popular segments within a given area (defined by the southwest and northeast corners of the area).
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns up to 10 segments only.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/segments/explore
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/segments/#explore">http://strava.github.io/api/v3/segments/#explore</a>
	 *
	 * @param southwestCorner
	 *            The southwest corner of the area to be explored
	 * @param northeastCorner
	 *            The northeast corner of the area to be explored
	 * @param activityType
	 *            (Optional) "running" or "riding", default is riding
	 * @param minCat
	 *            (Optional) Minimum climb category filter
	 * @param maxCat
	 *            (Optional) Maximum climb category filter
	 * @return Returns an array of up to 10 segment objects
	 */
	public CompletableFuture<StravaSegmentExplorerResponse> segmentExploreAsync(final StravaMapPoint southwestCorner, final StravaMapPoint northeastCorner,
			final StravaSegmentExplorerActivityType activityType, final StravaClimbCategory minCat, final StravaClimbCategory maxCat);

	/**
	 * <p>
	 * Star or unstar a segment
	 * </p>
	 *
	 * @param segmentId
	 *            The id of the segment to be starred
	 * @param starred
	 *            <code>true</code> if segment is to be starred, <code>false</code> if segment is to be unstarred
	 * @return Detailed representation of the segment, will be <code>null</code> if the segment does not exist, or will be one with PRIVATE resource state if the segment is flagged as private and
	 *         cannot be accessed
	 */
	public StravaSegment starSegment(final Integer segmentId, final Boolean starred);

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
	public CompletableFuture<StravaSegment> starSegmentAsync(final Integer segmentId, final Boolean starred);
}

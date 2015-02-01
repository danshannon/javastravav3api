package com.danshannon.strava.api.service;

import java.util.Calendar;
import java.util.List;

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.MapPoint;
import com.danshannon.strava.api.model.Segment;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.SegmentExplorer;
import com.danshannon.strava.api.model.SegmentLeaderboard;
import com.danshannon.strava.api.model.SegmentLeaderboardEntry;
import com.danshannon.strava.api.model.reference.AgeGroup;
import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.model.reference.LeaderboardDateRange;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SegmentExplorerActivityType;
import com.danshannon.strava.api.model.reference.WeightClass;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;

/**
 * <p>{@link Segment Segments} are specific sections of road.</p>
 * 
 * <p>{@link Athlete Athletes�} {@link SegmentEffort efforts} are compared on these segments and {@link SegmentLeaderboard leaderboards} are created.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface SegmentServices {
	/**
	 * <p>Retrieve details about a specific {@link Segment segment}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/segments/#retrieve">http://strava.github.io/api/v3/segments/#retrieve</a>
	 * 
	 * @param id The id of the {@link Segment} to be retrieved
	 * @return Returns a {@link ResourceState#DETAILED detailed representation} of the {@link Segment}.
	 * @throws UnauthorizedException If authorisation fails
	 */
	public Segment getSegment(Integer id) throws UnauthorizedException;
	
	/**
	 * <p>Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the authenticated {@link Athlete athlete}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/starred</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a> 
	 * 
	 * @param page  (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the authenticated {@link Athlete}.
	 */
	public List<Segment> listAuthenticatedAthleteStarredSegments(Paging pagingInstruction);
	
	/**
	 * <p>Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the authenticated {@link Athlete athlete}.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/starred</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a> 
	 * 
	 * @return Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the authenticated {@link Athlete}.
	 */
	public List<Segment> listAuthenticatedAthleteStarredSegments();
	
	/**
	 * <p>Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the identified {@link Athlete athlete}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the athlete with the given id does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/starred</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a> 
	 * 
	 * @param id Identifier of the {@link Athlete} for which starred {@link Segment segments} are to be returned
	 * @param page  (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the identified {@link Athlete}.
	 */
	public List<Segment> listStarredSegments(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the identified {@link Athlete athlete}.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the athlete with the given id does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/starred</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/segments/#starred">http://strava.github.io/api/v3/segments/#starred</a> 
	 * 
	 * @param id Identifier of the {@link Athlete} for which starred {@link Segment segments} are to be returned
	 * @return Returns a {@link ResourceState#SUMMARY summary representation} of the {@link Segment segments} starred by the identified {@link Athlete}.
	 */
	public List<Segment> listStarredSegments(Integer id);
	
	/**
	 * <p>Retrieve an array of {@link SegmentEffort segment efforts}, for a given {@link Segment}, filtered by {@link Athlete} and/or a date range.</p>
	 * 
	 * <p>Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.</p>
	 * 
	 * <p>Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/all_efforts</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#efforts
	 * 
	 * @param id The id of the {@link Segment} for which {@link SegmentEffort segment efforts} are to be returned 
	 * @param athleteId (Optional) id of the {@link Athlete} to filter results by
	 * @param startDateLocal (Optional) ISO 8601 formatted date time
	 * @param endDateLocal (Optional) ISO 8601 formatted date time
	 * @param pagingInstruction (Optional) Page to start at for pagination / number of results per page
	 * @return Returns an array of {@link SegmentEffort segment effort} summary {@link ResourceState representations} sorted by start_date_local ascending or by elapsed_time if an athlete_id is provided.
	 */
	public List<SegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal, Calendar endDateLocal, Paging pagingInstruction);
	
	/**
	 * <p>Retrieve an array of {@link SegmentEffort segment efforts}, for a given {@link Segment}, filtered by {@link Athlete} and/or a date range.</p>
	 * 
	 * <p>Filtering parameters, like athlete_id, start_date_local and end_date_local, are optional. If they are not provided all efforts for the segment will be returned.</p>
	 * 
	 * <p>Date range filtering is accomplished using an inclusive start and end time, thus start_date_local and end_date_local must be sent together. For open ended ranges pick dates significantly in the past or future. The filtering is done over local time for the segment, so there is no need for timezone conversion. For example, all efforts on Jan. 1st, 2014 for a segment in San Francisco, CA can be fetched using 2014-01-01T00:00:00Z and 2014-01-01T23:59:59Z.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/all_efforts</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#efforts
	 * 
	 * @param id The id of the {@link Segment} for which {@link SegmentEffort segment efforts} are to be returned 
	 * @param athleteId (Optional) id of the {@link Athlete} to filter results by
	 * @param startDateLocal (Optional) ISO 8601 formatted date time
	 * @param endDateLocal (Optional) ISO 8601 formatted date time
	 * @return Returns an array of {@link SegmentEffort segment effort} summary {@link ResourceState representations} sorted by start_date_local ascending or by elapsed_time if an athlete_id is provided.
	 */
	public List<SegmentEffort> listSegmentEfforts(Integer id, Integer athleteId, Calendar startDateLocal, Calendar endDateLocal);
	
	/**
	 * <p>Retrieve an array of {@link SegmentEffort segment efforts}, for a given {@link Segment}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/all_efforts</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#efforts
	 * 
	 * @param id The id of the {@link Segment} for which {@link SegmentEffort segment efforts} are to be returned 
	 * @param pagingInstruction (Optional) paging parameters
	 * @return Returns an array of {@link SegmentEffort segment effort} summary {@link ResourceState representations} sorted by start_date_local ascending or by elapsed_time if an athlete_id is provided.
	 */
	public List<SegmentEffort> listSegmentEfforts(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Retrieve an array of {@link SegmentEffort segment efforts}, for a given {@link Segment}.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/all_efforts</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#efforts
	 * 
	 * @param id The id of the {@link Segment} for which {@link SegmentEffort segment efforts} are to be returned 
	 * @return Returns an array of {@link SegmentEffort segment effort} summary {@link ResourceState representations} sorted by start_date_local ascending or by elapsed_time if an athlete_id is provided.
	 */
	public List<SegmentEffort> listSegmentEfforts(Integer id);
	
	/**
	 * <p>{@link SegmentLeaderboard Leaderboards} represent the ranking of {@link Athlete athletes} on specific {@link Segment segments}.</p>
	 * 
	 * <p>Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/leaderboard</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#leaderboard
	 * 
	 * @param id The id of the segment to return a leaderboard for
	 * @param gender (Optional) {@link Gender Gender} to filter results by
	 * @param ageGroup (Optional) {@link AgeGroup Age group} to filter results by 
	 * @param weightClass (Optional) {@link WeightClass Weight class} to filter results by
	 * @param following (Optional) If <code>true</code> then will return only results for {@link Athlete athletes} that the currently authenticated athlete is following
	 * @param clubId (Optional) Id of {@link Club} to filter results by
	 * @param dateRange (Optional) Use to set to return results for this year, this month, this week etc.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of up to 10, by default, {@link SegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is deprecated, use entry_count instead.
	 */
	public SegmentLeaderboard getSegmentLeaderboard(Integer id, Gender gender, AgeGroup ageGroup, WeightClass weightClass, Boolean following, Integer clubId, LeaderboardDateRange dateRange, Paging pagingInstruction);
	
	/**
	 * <p>{@link SegmentLeaderboard Leaderboards} represent the ranking of {@link Athlete athletes} on specific {@link Segment segments}.</p>
	 * 
	 * <p>Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/leaderboard</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#leaderboard
	 * 
	 * @param id The id of the segment to return a leaderboard for
	 * @param pagingInstruction (Optional) Page number, Number of results per page (max 200)
	 * @return Returns an array of up to 10, by default, {@link SegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is deprecated, use entry_count instead.
	 */
	public SegmentLeaderboard getSegmentLeaderboard(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>{@link SegmentLeaderboard Leaderboards} represent the ranking of {@link Athlete athletes} on specific {@link Segment segments}.</p>
	 * 
	 * <p>Filter by age_group and weight_class is only allowed if the authenticated athlete is a Strava premium member.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the segment does not exist.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/:id/leaderboard</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#leaderboard
	 * 
	 * @param id The id of the segment to return a leaderboard for
	 * @return Returns an array of up to 10, by default, {@link SegmentLeaderboardEntry leaderboard entry} objects. Note that effort ids should be considered 64-bit integers and effort_count is deprecated, use entry_count instead.
	 */
	public SegmentLeaderboard getSegmentLeaderboard(Integer id);
	
	/**
	 * <p>This endpoint can be used to find popular segments within a given area (defined by the southwest and northeast corners of the area).</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/segments/explore</p>
	 * 
	 * @see http://strava.github.io/api/v3/segments/#explore
	 * @param southwestCorner The southwest corner of the area to be explored
	 * @param northeastCorner The northeast corner of the area to be explored 
	 * @param activityType (Optional) "running" or "riding", default is riding
	 * @param minCat (Optional) Minimum climb category filter
	 * @param maxCat (Optional) Maximum climb category filter
	 * @return Returns an array of up to 10 segment objects
	 */
	public SegmentExplorer segmentExplore(MapPoint southwestCorner, MapPoint northeastCorner, SegmentExplorerActivityType activityType, ClimbCategory minCat, ClimbCategory maxCat);
	
	
}

package com.danshannon.strava.api.service;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;

/**
 * Activity related services
 * 
 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
 * @author Dan Shannon
 *
 */
public interface ActivityServices {
	public static final String ENDPOINT = "https://www.strava.com/api/v3";
	
	/**
	 * <p>Activity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.</p>
	 * 
	 * <p>By default, only “important” efforts are included. “Importance” is based on a number of factors and its value may change over time. Factors considered include: segment age, views and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is “important” but not for the other.</p>
	 * 
	 * <p>Note that effort ids may exceed the max value for 32-bit integers. A long integer type should be used.</p>
	 * 
	 * <p>Each segment effort will have a hidden attribute indicating if it is “important” or not.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id The id of the {@link Activity activity} to be returned
	 * @param includeAllEfforts (Optional) Used to include all segment efforts in the result
	 * @return Returns a detailed representation if the {@link Activity activity} is owned by the requesting athlete. Returns a summary representation for all other requests.
	 */
	public Activity getActivity(Integer id, Boolean includeAllEfforts);
	
	/**
	 * <p>This API endpoint is for creating manually entered {@link Activity activities}. To upload a FIT, TCX or GPX file see the Upload Documentation.</p>
	 * 
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Only updates name, activity type, startDateLocal, elapsedTime, description and distance</p>
	 * 
	 * <p>If successful, returns a 201 status code along with a detailed representation of the created activity.</p>
	 * 
	 * <p>URL POST https://www.strava.com/api/v3/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param activity The {@link Activity activity} to be uploaded</p>
	 */
	public void createManualActivity(Activity activity);
	
	/**
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Only updates name, type, private, commute, trainer, gear_id and description</p>
	 * 
	 * <p>URL PUT https://www.strava.com/api/v3/activities/:id</p> 
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param activity The {@link Activity} to be updated
	 * @return Returns a detailed representation of the updated {@link Activity}.
	 */
	public Activity updateActivity(Activity activity);
	
	/**
	 * <p>Deletes the identified {@link Activity}</p>
	 * 
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Returns 204 No Content on success.</p>
	 * 
	 * <p>URL DELETE https://www.strava.com/api/v3/activities/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id The id of the {@link Activity} to be deleted.
	 */
	public void deleteActivity(Integer id);
	
	/**
	 * <p>This endpoint returns a list of {@link Activity activities} for the authenticated {@link Athlete}.</p>
	 * 
	 * <p>Should be used with before, after or page/per_page. Using a combination will result in an error or unexpected results.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param before (Optional) seconds since UNIX epoch, result will start with activities whose start_date is before this value
	 * @param after (Optional) seconds since UNIX epoch, result will start with activities whose start_date is after this value, sorted oldest first
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (maximum 200)
	 * @return Returns an array of {@link Activity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 */
	public Activity[] listAuthenticatedAthleteActivities(Integer before, Integer after, Integer page, Integer perPage);
	
	/**
	 * <p>List the recent activities performed by those the current authenticated {@link Athlete} is following.</p>
	 * 
	 * <p>Pagination is supported. However, results are limited to the last 200 total activities.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (maximum 200)
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public Activity[] listFriendsActivities(Integer page, Integer perPage);
	
	/**
	 * <p>Heartrate and power zones are set by the {@link Athlete athlete}. This endpoint returns the time (seconds) in each zone for the {@link Activity activity}.</p>
	 * 
	 * <p>The distribution is not customizable.</p>
	 * 
	 * <p>Requires an access token associated with the owner of the activity and the owner must be a premium user.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/zones</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id The id of the {@link Activity activity} for which zones should be returned
	 * @return Returns an array of {@link ActivityZone activity zones} for the {@link Activity} identified
	 */
	public ActivityZone[] listActivityZones(Integer id);
	
	/**
	 * <p>This resource will return all laps for an activity. Laps are triggered by athletes using their respective devices, such as Garmin watches.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/laps</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id The id of the {@link Activity} for which laps should be returned
	 * @return Returns an array of {@link Lap lap} effort summaries
	 */
	public Lap[] listActivityLaps(Integer id);
	
	/**
	 * <p>Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.</p>
	 * 
	 * <p>Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/comments</p>
	 * 
	 * @see <a herf="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id The id of the {@link Activity} for which {@link Comment comments} should be returned
	 * @param markdown (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (maximum 200)
	 */
	public Comment[] listActivityComments(Integer id, Boolean markdown, Integer page, Integer perPage);
	
	/**
	 * <p>A kudos is Strava’s version of a ‘Like’ or a ‘+1’. The number of kudos on an activity is returned with the activity summary.</p>
	 * 
	 * <p>Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who’ve left kudos and can only be accessed by the owner of the activity.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 * 
	 * @param id The id of the {@link Activity} for which kudoers are to be listed
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (maximum 200)
	 * @return Returns an array of {@link Athlete athlete} summary objects.
	 */
	public Athlete[] listActivityKudoers(Integer id, Integer page, Integer perPage);
	
	/**
	 * <p>Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.</p>
	 * 
	 * <p>Note that Instagram does not provide taken_at information.</p>
	 * 
	 * <p>The number of photos is included in the activity summary and detail responses. Use this endpoint to retrieve a list of photos associated with this activity. This endpoint can only be accessed by the owner of the activity.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/photos</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/photos/">http://strava.github.io/api/v3/photos/</a>
	 * 
	 * @param id The id of the {@link Activity} for which photos are to be listed
	 * @return Returns an array of {@link Photo photo} objects.
	 */
	public Photo[] listActivityPhotos(Integer id);
}

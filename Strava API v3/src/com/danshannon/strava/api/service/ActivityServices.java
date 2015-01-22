package com.danshannon.strava.api.service;

import java.util.Calendar;
import java.util.List;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.ActivityZone;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Comment;
import com.danshannon.strava.api.model.Lap;
import com.danshannon.strava.api.model.Photo;
import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;

/**
 * Activity related services
 * 
 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
 * @author Dan Shannon
 *
 */
public interface ActivityServices {
	/**
	 * <p>Activity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.</p>
	 * 
	 * <p>By default, only "important" efforts are included. "Importance" is based on a number of factors and its value may change over time. Factors considered include: segment age, views and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is "important" but not for the other.</p>
	 * 
	 * <p>Note that effort ids may exceed the max value for 32-bit integers. A long integer type should be used.</p>
	 * 
	 * <p>Each segment effort will have a hidden attribute indicating if it is "important" or not.</p>
	 * 
	 * <p>Returns <code>null</code> if the activity does not exist
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id The id of the {@link Activity activity} to be returned
	 * @param includeAllEfforts (Optional) Used to include all segment efforts in the result (if omitted or <code>false</code> then only "important" efforts are returned).
	 * @return Returns a detailed representation if the {@link Activity activity} is owned by the requesting athlete. Returns a summary representation for all other requests.
	 */
	public Activity getActivity(Integer id, Boolean includeAllEfforts);
	
	/**
	 * <p>This API endpoint is for creating manually entered {@link Activity activities}. To upload a FIT, TCX or GPX file see the Upload Documentation.</p>
	 * 
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Only updates name, activity type, startDateLocal, elapsedTime, description and distance.</p>
	 * 
	 * <p>If any other activity attributes are passed to the API, they are ignored when creating the activity.</p>
	 * 
	 * <p>If successful, returns a 201 status code along with a detailed representation of the created activity.</p>
	 * 
	 * <p>URL POST https://www.strava.com/api/v3/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param activity The {@link Activity activity} to be uploaded</p>
	 * @throws UnauthorizedException If user's security token does not grant write access to the activity
	 * @throws BadRequestException If activity doesn't include all required fields
	 */
	public Activity createManualActivity(Activity activity) throws UnauthorizedException, BadRequestException;
	
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
	 * 
	 * @throws UnauthorizedException If the service's security token doesn't grant write access to this activity
	 * @throws NotFoundException If the activity does not exist on Strava
	 */
	public Activity updateActivity(Activity activity) throws UnauthorizedException, NotFoundException;
	
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
	 * 
	 * @throws UnauthorizedException If the service's security token doesn't grant write access to this activity
	 * @throws NotFoundException If the {@link Activity} does not exist on Strava
	 * @return Should return <code>null</code>
	 */
	public Activity deleteActivity(Integer id) throws UnauthorizedException, NotFoundException;
	
	/**
	 * <p>This endpoint returns a list of {@link Activity activities} for the authenticated {@link Athlete}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @return Returns an array of {@link Activity} summary representations sorted newest first by default.
	 * @throws UnauthorizedException thrown if service returns a 401 Unauthorized status
	 */
	public List<Activity> listAuthenticatedAthleteActivities() throws UnauthorizedException;
	
	/**
	 * <p>This endpoint returns a list of {@link Activity activities} for the authenticated {@link Athlete}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link Activity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 * @throws UnauthorizedException thrown if service returns a 401 Unauthorized status
	 */
	public List<Activity> listAuthenticatedAthleteActivities(Paging pagingInstruction) throws UnauthorizedException;
	
	/**
	 * <p>This endpoint returns a list of {@link Activity activities} for the authenticated {@link Athlete}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param before (Optional) result will start with activities whose start_date is before this value
	 * @param after (Optional) result will start with activities whose start_date is after this value, sorted oldest first
	 * @return Returns an array of {@link Activity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 * @throws UnauthorizedException thrown if service returns a 401 Unauthorized status
	 */
	public List<Activity> listAuthenticatedAthleteActivities(Calendar before, Calendar after) throws UnauthorizedException;
	
	/**
	 * <p>This endpoint returns a list of {@link Activity activities} for the authenticated {@link Athlete}.</p>
	 * 
	 * <p>Should be used with before, after or page/per_page. Using a combination will result in an error or unexpected results.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/activities</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param before (Optional) result will start with activities whose start_date is before this value
	 * @param after (Optional) result will start with activities whose start_date is after this value, sorted oldest first
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link Activity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 * @throws UnauthorizedException thrown if service returns a 401 Unauthorized status
	 */
	public List<Activity> listAuthenticatedAthleteActivities(Calendar before, Calendar after, Paging pagingInstruction) throws UnauthorizedException;
	
	/**
	 * <p>List the recent activities performed by those the current authenticated {@link Athlete} is following.</p>
	 * 
	 * <p>Pagination is supported. However, results are limited to the last 200 total activities.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public List<Activity> listFriendsActivities(Paging pagingInstruction);
	
	/**
	 * <p>List the recent activities performed by those the current authenticated {@link Athlete} is following.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public List<Activity> listFriendsActivities();
	
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
	public List<ActivityZone> listActivityZones(Integer id);
	
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
	public List<Lap> listActivityLaps(Integer id);
	
	/**
	 * <p>Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.</p>
	 * 
	 * <p>Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if the {@link Activity} does not exist</p>
	 * 
	 * <p>Returns an empty array if the {@link Activity} does not contain any {@link Comment comments}</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/comments</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id The id of the {@link Activity} for which {@link Comment comments} should be returned
	 * @param markdown (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @return List of comments
	 * @throws NotFoundException If the activity does not exist
	 */
	public List<Comment> listActivityComments(Integer id, Boolean markdown) throws NotFoundException;
	
	/**
	 * <p>Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.</p>
	 * 
	 * <p>Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns an empty array if the {@link Activity} does not contain any {@link Comment comments}</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/activities/:id/comments</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id The id of the {@link Activity} for which {@link Comment comments} should be returned
	 * @param markdown (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return List of comments
	 * @throws NotFoundException If the activity does not exist
	 */
	public List<Comment> listActivityComments(Integer id, Boolean markdown, Paging pagingInstructions) throws NotFoundException;
	
	/**
	 * <p>A kudos is Strava's version of a 'like' or '+1'. The number of kudos on an activity is returned with the activity summary.</p>
	 * 
	 * <p>Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who�ve left kudos and can only be accessed by the owner of the activity.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 * 
	 * @param id The id of the {@link Activity} for which kudoers are to be listed
	 * @return Returns an array of {@link Athlete athlete} summary objects.
	 * @throws NotFoundException If the activity doesn't exist
	 */
	public List<Athlete> listActivityKudoers(Integer id) throws NotFoundException;
	
	/**
	 * <p>A kudos is Strava's version of a 'like' or '+1'. The number of kudos on an activity is returned with the activity summary.</p>
	 * 
	 * <p>Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.</p>
	 * 
	 * <p>The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who�ve left kudos and can only be accessed by the owner of the activity.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 * 
	 * @param id The id of the {@link Activity} for which kudoers are to be listed
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link Athlete athlete} summary objects.
	 * @throws NotFoundException If the activity doesn't exist
	 */
	public List<Athlete> listActivityKudoers(Integer id, Paging pagingInstruction) throws NotFoundException;
	
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
	public List<Photo> listActivityPhotos(Integer id);
	
	/**
	 * <p>Returns the activities that were matched as “with this group”. The number equals activity.athlete_count-1.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * @param id Activity id for which related activities should be listed
	 * @return List of related activities (not including the main activity)
	 * @throws NotFoundException If base activity doesn't exist.
	 */
	public List<Activity> listRelatedActivities(Integer id) throws NotFoundException;
	
	/**
	 * <p>Returns the activities that were matched as “with this group”. The number equals activity.athlete_count-1.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * @param id Activity id for which related activities should be listed
	 * @param pagingInstruction Paging instructions
	 * @return List of related activities (not including the main activity)
	 * @throws NotFoundException If base activity doesn't exist.
	 */
	public List<Activity> listRelatedActivities(Integer id, Paging pagingInstruction) throws NotFoundException;
}

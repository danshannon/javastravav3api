package stravajava.api.v3.service;

import java.util.Calendar;
import java.util.List;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaActivityZone;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaComment;
import stravajava.api.v3.model.StravaLap;
import stravajava.api.v3.model.StravaPhoto;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * StravaActivity related services
 * 
 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
 * @author Dan Shannon
 *
 */
public interface ActivityServices {
	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 * 
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of factors and its value may change over time. Factors considered
	 * include: segment age, views and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same
	 * segment, it is possible that for one activity the associated effort is "important" but not for the other.
	 * </p>
	 * 
	 * <p>
	 * Note that effort ids may exceed the max value for 32-bit integers. A long integer type should be used.
	 * </p>
	 * 
	 * <p>
	 * Each segment effort will have a hidden attribute indicating if it is "important" or not.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @return Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation for
	 *         all other requests.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public StravaActivity getActivity(final Integer id);

	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 * 
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of factors and its value may change over time. Factors considered
	 * include: segment age, views and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same
	 * segment, it is possible that for one activity the associated effort is "important" but not for the other.
	 * </p>
	 * 
	 * <p>
	 * Note that effort ids may exceed the max value for 32-bit integers. A long integer type should be used.
	 * </p>
	 * 
	 * <p>
	 * Each segment effort will have a hidden attribute indicating if it is "important" or not.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @param includeAllEfforts
	 *            (Optional) Used to include all segment efforts in the result (if omitted or <code>false</code> then only "important" efforts are returned).
	 * @return Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation for
	 *         all other requests.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public StravaActivity getActivity(final Integer id, final Boolean includeAllEfforts);

	/**
	 * <p>
	 * This API endpoint is for creating manually entered {@link StravaActivity activities}. To upload a FIT, TCX or GPX file see the Upload Documentation.
	 * </p>
	 * 
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 * 
	 * <p>
	 * Only updates name, activity type, startDateLocal, elapsedTime, description and distance.
	 * </p>
	 * 
	 * <p>
	 * If any other activity attributes are passed to the API, they are ignored when creating the activity.
	 * </p>
	 * 
	 * <p>
	 * If successful, returns a 201 status code along with a detailed representation of the created activity.
	 * </p>
	 * 
	 * <p>
	 * URL POST https://www.strava.com/api/v3/activities
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param activity
	 *            The {@link StravaActivity activity} to be uploaded</p>
	 * @throws UnauthorizedException
	 *             If user's security token is invalid or does not grant write access to the activity
	 */
	public StravaActivity createManualActivity(final StravaActivity activity);

	/**
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 * 
	 * <p>
	 * Only updates name, type, private, commute, trainer, gear_id and description
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity doesn't exist on Strava
	 * </p>
	 * 
	 * <p>
	 * URL PUT https://www.strava.com/api/v3/activities/:id
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param activity
	 *            The {@link StravaActivity} to be updated
	 * @return Returns a detailed representation of the updated {@link StravaActivity}.
	 * 
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid or doesn't grant write access to this activity
	 */
	public StravaActivity updateActivity(final StravaActivity activity);

	/**
	 * <p>
	 * Deletes the identified {@link StravaActivity}
	 * </p>
	 * 
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity does not exist on Strava
	 * </p>
	 * 
	 * <p>
	 * URL DELETE https://www.strava.com/api/v3/activities/:id
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} to be deleted.
	 * 
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid or doesn't grant write access to this activity
	 * @return Should return <code>null</code>
	 */
	public StravaActivity deleteActivity(final Integer id);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 * @throws UnauthorizedException
	 *             thrown if service's security token is invalid
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities();

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 * @throws UnauthorizedException
	 *             thrown if service's security token is invalid
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Paging pagingInstruction);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param before
	 *            (Optional) result will start with activities whose start_date is before this value
	 * @param after
	 *            (Optional) result will start with activities whose start_date is after this value, sorted oldest first
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Calendar before, final Calendar after);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 * 
	 * <p>
	 * Should be used with before, after or page/per_page. Using a combination will result in an error or unexpected results.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param before
	 *            (Optional) result will start with activities whose start_date is before this value
	 * @param after
	 *            (Optional) result will start with activities whose start_date is after this value, sorted oldest first
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final Calendar before, final Calendar after, final Paging pagingInstruction);

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported. However, results are limited to the last 200 total activities.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction);

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listFriendsActivities();

	/**
	 * <p>
	 * Heartrate and power zones are set by the {@link StravaAthlete athlete}. This endpoint returns the time (seconds) in each zone for the
	 * {@link StravaActivity activity}.
	 * </p>
	 * 
	 * <p>
	 * The distribution is not customizable.
	 * </p>
	 * 
	 * <p>
	 * Requires an access token associated with the owner of the activity and the owner must be a premium user.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/zones
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity activity} for which zones should be returned
	 * @return Returns an array of {@link StravaActivityZone activity zones} for the {@link StravaActivity} identified
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivityZone> listActivityZones(final Integer id);

	/**
	 * <p>
	 * This resource will return all laps for an activity. Laps are triggered by athletes using their respective devices, such as Garmin watches.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/laps
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which laps should be returned
	 * @return Returns an array of {@link StravaLap lap} effort summaries
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaLap> listActivityLaps(final Integer id);

	/**
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 * 
	 * <p>
	 * StravaComment posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given
	 * activity.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Returns an empty array if the {@link StravaActivity} does not contain any {@link StravaComment comments}
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @return List of comments
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown);

	/**
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 * 
	 * <p>
	 * {@link StravaComment Comment} posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given
	 * activity.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Returns an empty array if the {@link StravaActivity} does not contain any {@link StravaComment comments}
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @return List of comments
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaComment> listActivityComments(final Integer id);

	/**
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 * 
	 * <p>
	 * {@link StravaComment Comment} posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given
	 * activity.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Returns an empty array if the {@link StravaActivity} does not contain any {@link StravaComment comments}
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @return List of comments
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaComment> listActivityComments(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 * 
	 * <p>
	 * StravaComment posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given
	 * activity.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Returns an empty array if the {@link StravaActivity} does not contain any {@link StravaComment comments}
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return List of comments
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaComment> listActivityComments(final Integer id, final Boolean markdown, final Paging pagingInstructions);

	/**
	 * <p>
	 * A kudos is Strava's version of a 'like' or '+1'. The number of kudos on an activity is returned with the activity summary.
	 * </p>
	 * 
	 * <p>
	 * Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the
	 * athletes who�ve left kudos and can only be accessed by the owner of the activity.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @return Returns an array of {@link StravaAthlete athlete} summary objects.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaAthlete> listActivityKudoers(final Integer id);

	/**
	 * <p>
	 * A kudos is Strava's version of a 'like' or '+1'. The number of kudos on an activity is returned with the activity summary.
	 * </p>
	 * 
	 * <p>
	 * Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 * 
	 * <p>
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the
	 * athletes who�ve left kudos and can only be accessed by the owner of the activity.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary objects.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaAthlete> listActivityKudoers(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.
	 * </p>
	 * 
	 * <p>
	 * Note that Instagram does not provide taken_at information.
	 * </p>
	 * 
	 * <p>
	 * The number of photos is included in the activity summary and detail responses. Use this endpoint to retrieve a list of photos associated with this
	 * activity. This endpoint can only be accessed by the owner of the activity.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/photos
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/photos/">http://strava.github.io/api/v3/photos/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaActivity} for which photos are to be listed
	 * @return Returns an array of {@link StravaPhoto photo} objects.
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaPhoto> listActivityPhotos(final Integer id);

	/**
	 * <p>
	 * Returns the activities that were matched as “with this group”. The number equals activity.athlete_count-1.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * @param id
	 *            StravaActivity id for which related activities should be listed
	 * @return List of related activities (not including the main activity)
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listRelatedActivities(final Integer id);

	/**
	 * <p>
	 * Returns the activities that were matched as “with this group”. The number equals activity.athlete_count-1.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * @param id
	 *            StravaActivity id for which related activities should be listed
	 * @param pagingInstruction
	 *            Paging instructions
	 * @return List of related activities (not including the main activity)
	 * @throws UnauthorizedException
	 *             If the service's security token is invalid
	 */
	public List<StravaActivity> listRelatedActivities(final Integer id, final Paging pagingInstruction);

}

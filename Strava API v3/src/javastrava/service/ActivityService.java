package javastrava.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaActivity;
import javastrava.model.StravaActivityUpdate;
import javastrava.model.StravaActivityZone;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaComment;
import javastrava.model.StravaLap;
import javastrava.model.StravaPhoto;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.util.Paging;

/**
 * StravaActivity related services
 *
 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
 * @author Dan Shannon
 *
 */
public interface ActivityService extends StravaService {
	/**
	 * <p>
	 * Create a comment on an activity.
	 * </p>
	 *
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 *
	 * <p>
	 * Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 *
	 * <p>
	 * The comment will be made by the user associated with the access_token.
	 * </p>
	 *
	 * <p>
	 * URL POST https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @param text
	 *            Comment text
	 * @return The comment as created on Strava
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @throws BadRequestException
	 *             if the comment is invalid
	 */
	public StravaComment createComment(final Long activityId, final String text) throws NotFoundException, BadRequestException;

	/**
	 * <p>
	 * Create a comment on an activity.
	 * </p>
	 *
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 *
	 * <p>
	 * Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 *
	 * <p>
	 * The comment will be made by the user associated with the access_token.
	 * </p>
	 *
	 * <p>
	 * URL POST https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 *
	 * @param comment
	 *            Comment to be created
	 * @return The comment as created on Strava
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @throws BadRequestException
	 *             if the comment is invalid
	 */
	public StravaComment createComment(final StravaComment comment) throws NotFoundException, BadRequestException;

	/**
	 * <p>
	 * Create a comment on an activity.
	 * </p>
	 *
	 * <p>
	 * Comments on an activity can be viewed by any user. However, only internal applications are allowed to create or delete them.
	 * </p>
	 *
	 * <p>
	 * Comment posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 *
	 * <p>
	 * The comment will be made by the user associated with the access_token.
	 * </p>
	 *
	 * <p>
	 * URL POST https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 *
	 * @param activityId
	 *            Activity identifier
	 * @param text
	 *            Comment text
	 * @return (A {@link CompletableFuture} which returns) The comment as created on Strava
	 * @throws NotFoundException
	 *             if the activity does not exist
	 * @throws BadRequestException
	 *             if the comment is invalid
	 */
	public CompletableFuture<StravaComment> createCommentAsync(final Long activityId, final String text) throws NotFoundException, BadRequestException;

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
	 *            The {@link StravaActivity activity} to be uploaded
	 * @return The activity as it was uploaded on Strava
	 */
	public StravaActivity createManualActivity(final StravaActivity activity);

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
	 *            The {@link StravaActivity activity} to be uploaded
	 * @return (A {@link CompletableFuture} which returns) The activity as it was uploaded on Strava
	 */
	public CompletableFuture<StravaActivity> createManualActivityAsync(final StravaActivity activity);

	/**
	 * <p>
	 * Deletes the identified {@link StravaActivity}
	 * </p>
	 *
	 * <p>
	 * Activity deletion can be enabled on a per application basis, email developers -at- strava.com for more information. It also requires write permissions, as requested during the authorization
	 * process.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} to be deleted.
	 *
	 * @return Should return <code>null</code>
	 * @throws NotFoundException
	 *             If the activity does not exist
	 */
	public StravaActivity deleteActivity(final Long activityId) throws NotFoundException;

	/**
	 * <p>
	 * Deletes the identified {@link StravaActivity}
	 * </p>
	 *
	 * <p>
	 * Activity deletion can be enabled on a per application basis, email developers -at- strava.com for more information. It also requires write permissions, as requested during the authorization
	 * process.
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
	 * @param activity
	 *            The {@link StravaActivity} to be deleted.
	 *
	 * @return Should return <code>null</code>
	 * @throws NotFoundException
	 *             If the activity does not exist
	 */
	public StravaActivity deleteActivity(final StravaActivity activity) throws NotFoundException;

	/**
	 * <p>
	 * Deletes the identified {@link StravaActivity}
	 * </p>
	 *
	 * <p>
	 * Activity deletion can be enabled on a per application basis, email developers -at- strava.com for more information. It also requires write permissions, as requested during the authorization
	 * process.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} to be deleted.
	 *
	 * @return (A {@link CompletableFuture} which returns) Should return <code>null</code>
	 * @throws NotFoundException
	 *             If the activity does not exist
	 */
	public CompletableFuture<StravaActivity> deleteActivityAsync(final Long activityId) throws NotFoundException;

	/**
	 * <p>
	 * Delete a comment belonging to the authenticated user
	 * </p>
	 *
	 * @param commentId
	 *            Identifier of the comment to be deleted
	 * @param activityId
	 *            Identifier of the activity the comment was posted to
	 * @throws NotFoundException
	 *             if the activity or the comment does exist
	 */
	public void deleteComment(final Long activityId, final Integer commentId) throws NotFoundException;

	/**
	 * <p>
	 * Delete a comment belonging to the authenticated user
	 * </p>
	 *
	 * @param comment
	 *            The comment to be deleted
	 * @throws NotFoundException
	 *             If the comment does not exist on Strava
	 */
	public void deleteComment(final StravaComment comment) throws NotFoundException;

	/**
	 * <p>
	 * Delete a comment belonging to the authenticated user
	 * </p>
	 *
	 * @param commentId
	 *            Identifier of the comment to be deleted
	 * @param activityId
	 *            Identifier of the activity the comment was posted to
	 * @return A {@link CompletableFuture} which indicates when the deletion is complete
	 * @throws NotFoundException
	 *             if the activity or the comment does exist
	 */
	public CompletableFuture<Void> deleteCommentAsync(final Long activityId, final Integer commentId) throws NotFoundException;

	/**
	 * <p>
	 * Delete a comment belonging to the authenticated user
	 * </p>
	 *
	 * @param comment
	 *            The comment to be deleted
	 * @return A {@link CompletableFuture} which indicates when the deletion is complete
	 * @throws NotFoundException
	 *             If the comment does not exist on Strava
	 */
	public CompletableFuture<Void> deleteCommentAsync(final StravaComment comment) throws NotFoundException;

	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of undocumented factors and its value may change over time. Factors considered include: segment age, views
	 * and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is
	 * "important" but not for the other.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @return Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation for all other requests.
	 */
	public StravaActivity getActivity(final Long activityId);

	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of factors and its value may change over time. Factors considered include: segment age, views and stars, if
	 * the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is "important" but
	 * not for the other.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @param includeAllEfforts
	 *            (Optional) Used to include all segment efforts in the result (if omitted or <code>false</code> then only "important" efforts are returned).
	 * @return Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation for all other requests.
	 */
	public StravaActivity getActivity(final Long activityId, final Boolean includeAllEfforts);

	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of undocumented factors and its value may change over time. Factors considered include: segment age, views
	 * and stars, if the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is
	 * "important" but not for the other.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @return (A {@link CompletableFuture} which returns) Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation
	 *         for all other requests.
	 */
	public CompletableFuture<StravaActivity> getActivityAsync(final Long activityId);

	/**
	 * <p>
	 * StravaActivity details, including segment efforts, splits and best efforts, are only available to the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * By default, only "important" efforts are included. "Importance" is based on a number of factors and its value may change over time. Factors considered include: segment age, views and stars, if
	 * the user has hidden/shown the segment and if the effort was a PR. Note, if two activities cover the same segment, it is possible that for one activity the associated effort is "important" but
	 * not for the other.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} to be returned
	 * @param includeAllEfforts
	 *            (Optional) Used to include all segment efforts in the result (if omitted or <code>false</code> then only "important" efforts are returned).
	 * @return (A {@link CompletableFuture} which returns) Returns a detailed representation if the {@link StravaActivity activity} is owned by the requesting athlete. Returns a summary representation
	 *         for all other requests.
	 */
	public CompletableFuture<StravaActivity> getActivityAsync(final Long activityId, final Boolean includeAllEfforts);

	/**
	 * <p>
	 * Kudo an activity (kudo is given by the authenticated athlete). You can do this multiple times, but the activity only receives one kudos.
	 * </p>
	 *
	 * <p>
	 * Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 *
	 * @param activityId
	 *            Identifier of the activity to be kudoed.
	 * @throws NotFoundException
	 *             If the activity does not exist on Strava
	 */
	public void giveKudos(final Long activityId) throws NotFoundException;

	/**
	 * <p>
	 * Kudo an activity (kudo is given by the authenticated athlete). You can do this multiple times, but the activity only receives one kudos.
	 * </p>
	 *
	 * <p>
	 * Kudos posting can be enabled on a per application basis, email developers -at- strava.com for more information.
	 * </p>
	 *
	 * @param activityId
	 *            Identifier of the activity to be kudoed.
	 * @return A {@link CompletableFuture} which indicates when the kudos has been given
	 * @throws NotFoundException
	 *             If the activity does not exist on Strava
	 */
	public CompletableFuture<Void> giveKudosAsync(final Long activityId) throws NotFoundException;

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @return List of comments
	 */
	public List<StravaComment> listActivityComments(final Long activityId);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
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
	 * Pagination is not supported. Returns only the first page of activities.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @return List of comments
	 */
	public List<StravaComment> listActivityComments(final Long activityId, final Boolean markdown);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return List of comments
	 */
	public List<StravaComment> listActivityComments(final Long activityId, final Boolean markdown, final Paging pagingInstruction);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param pagingInstruction
	 *            (Optional) Paging instructions. If not provided then the first page is returned.
	 * @return List of comments
	 */
	public List<StravaComment> listActivityComments(final Long activityId, final Paging pagingInstruction);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @return (A {@link CompletableFuture} which returns) List of comments
	 */
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
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
	 * Pagination is not supported. Returns only the first page of activities.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/comments
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/comments/">http://strava.github.io/api/v3/comments/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @return (A {@link CompletableFuture} which returns) List of comments
	 */
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Boolean markdown);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param markdown
	 *            (Optional) Include markdown in comments (default is <code>false</code> - i.e. filter out
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return (A {@link CompletableFuture} which returns) List of comments
	 */
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Boolean markdown, final Paging pagingInstruction);

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
	 * The number of comments is included in the activity summary and detail responses. Use this endpoint to retrieve a list of comments left on a given activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which {@link StravaComment comments} should be returned
	 * @param pagingInstruction
	 *            (Optional) Paging instructions. If not provided then the first page is returned.
	 * @return (A {@link CompletableFuture} which returns) List of comments
	 */
	public CompletableFuture<List<StravaComment>> listActivityCommentsAsync(final Long activityId, final Paging pagingInstruction);

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
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who have left kudos and can only
	 * be accessed by the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @return Returns an array of {@link StravaAthlete athlete} summary objects.
	 */
	public List<StravaAthlete> listActivityKudoers(final Long activityId);

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
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who have left kudos and can only
	 * be accessed by the owner of the activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary objects.
	 */
	public List<StravaAthlete> listActivityKudoers(final Long activityId, final Paging pagingInstruction);

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
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who have left kudos and can only
	 * be accessed by the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/kudos/">http://strava.github.io/api/v3/kudos/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaAthlete athlete} summary objects.
	 */
	public CompletableFuture<List<StravaAthlete>> listActivityKudoersAsync(final Long activityId);

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
	 * The number of kudos is included in the activity summary and detailed representations. This endpoint is for retrieving more detailed information on the athletes who have left kudos and can only
	 * be accessed by the owner of the activity.
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
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which kudoers are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaAthlete athlete} summary objects.
	 */
	public CompletableFuture<List<StravaAthlete>> listActivityKudoersAsync(final Long activityId, final Paging pagingInstruction);

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
	 * Pagination is not supported. Returns all the laps for the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/laps
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which laps should be returned
	 * @return Returns an array of {@link StravaLap lap} effort summaries
	 */
	public List<StravaLap> listActivityLaps(final Long activityId);

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
	 * Pagination is not supported. Returns all the laps for the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/laps
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which laps should be returned
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaLap lap} effort summaries
	 */
	public CompletableFuture<List<StravaLap>> listActivityLapsAsync(final Long activityId);

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
	 * The number of photos is included in the activity summary and detail responses. Use this endpoint to retrieve a list of photos associated with this activity. This endpoint can only be accessed
	 * by the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all photos associated with the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/photos
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/photos/">http://strava.github.io/api/v3/photos/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which photos are to be listed
	 * @return Returns an array of {@link StravaPhoto photo} objects.
	 */
	public List<StravaPhoto> listActivityPhotos(final Long activityId);

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
	 * The number of photos is included in the activity summary and detail responses. Use this endpoint to retrieve a list of photos associated with this activity. This endpoint can only be accessed
	 * by the owner of the activity.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all photos associated with the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/photos
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/photos/">http://strava.github.io/api/v3/photos/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity} for which photos are to be listed
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaPhoto photo} objects.
	 */
	public CompletableFuture<List<StravaPhoto>> listActivityPhotosAsync(final Long activityId);

	/**
	 * <p>
	 * Heartrate and power zones are set by the {@link StravaAthlete athlete}. This endpoint returns the time (seconds) in each zone for the {@link StravaActivity activity}.
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
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all activity zones for the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/zones
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} for which zones should be returned
	 * @return Returns an array of {@link StravaActivityZone activity zones} for the {@link StravaActivity} identified
	 */
	public List<StravaActivityZone> listActivityZones(final Long activityId);

	/**
	 * <p>
	 * Heartrate and power zones are set by the {@link StravaAthlete athlete}. This endpoint returns the time (seconds) in each zone for the {@link StravaActivity activity}.
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
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all activity zones for the activity.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/:id/zones
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param activityId
	 *            The id of the {@link StravaActivity activity} for which zones should be returned
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivityZone activity zones} for the {@link StravaActivity} identified
	 */
	public CompletableFuture<List<StravaActivityZone>> listActivityZonesAsync(final Long activityId);

	/**
	 * <p>
	 * List ALL comments on an activity, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL comments
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY COMMENTS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity whose comments should be listed
	 * @return All comments on the activity
	 */
	public List<StravaComment> listAllActivityComments(final Long activityId);

	/**
	 * <p>
	 * List ALL comments on an activity, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL comments
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY COMMENTS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity whose comments should be listed
	 * @return (A {@link CompletableFuture} which returns) All comments on the activity
	 */
	public CompletableFuture<List<StravaComment>> listAllActivityCommentsAsync(final Long activityId);

	/**
	 * <p>
	 * List ALL kudoers on an activity, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL kudoers
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY KUDOS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity whose kudoers should be listed
	 * @return All athletes who have kudoed the activity
	 */
	public List<StravaAthlete> listAllActivityKudoers(final Long activityId);

	/**
	 * <p>
	 * List ALL kudoers on an activity, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL kudoers
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY KUDOS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity whose kudoers should be listed
	 * @return (A {@link CompletableFuture} which returns) All athletes who have kudoed the activity
	 */
	public CompletableFuture<List<StravaAthlete>> listAllActivityKudoersAsync(final Long activityId);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. USE WITH CAUTION. ALL activities for the athlete will be returned, regardless of how many calls to the Strava API are required to achieve this.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public List<StravaActivity> listAllAuthenticatedAthleteActivities();

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. USE WITH CAUTION. ALL activities for the athlete will be returned, regardless of how many calls to the Strava API are required to achieve this.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param before
	 *            Return only rides started BEFORE this date/time
	 * @param after
	 *            Return only rides started AFTER this data/time
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public List<StravaActivity> listAllAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. USE WITH CAUTION. ALL activities for the athlete will be returned, regardless of how many calls to the Strava API are required to achieve this.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public CompletableFuture<List<StravaActivity>> listAllAuthenticatedAthleteActivitiesAsync();

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. USE WITH CAUTION. ALL activities for the athlete will be returned, regardless of how many calls to the Strava API are required to achieve this.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param before
	 *            Return only rides started BEFORE this date/time
	 * @param after
	 *            Return only rides started AFTER this data/time
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public CompletableFuture<List<StravaActivity>> listAllAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after);

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL friends' activities (although it is restricted by Strava to the last 200)
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public List<StravaActivity> listAllFriendsActivities();

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL friends' activities (although it is restricted by Strava to the last 200)
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return (A {@link CompletableFuture} which returns) Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public CompletableFuture<List<StravaActivity>> listAllFriendsActivitiesAsync();

	/**
	 * <p>
	 * List ALL related activities, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL related activities
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity identifier
	 * @return List of Strava activities that Strava has determined are related to this one
	 */
	public List<StravaActivity> listAllRelatedActivities(final Long activityId);

	/**
	 * <p>
	 * List ALL related activities, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the activity does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported - this method just returns ALL related activities
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ACTIVITIES WITH MANY RELATED ACTIVITIES WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * @param activityId
	 *            The activity identifier
	 * @return (A {@link CompletableFuture} which returns) List of Strava activities that Strava has determined are related to this one
	 */
	public CompletableFuture<List<StravaActivity>> listAllRelatedActivitiesAsync(final Long activityId);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns only the first page of activities.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities();

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns only the first page of activities.
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
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after);

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
	 * Pagination is supported.
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
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
	 */
	public List<StravaActivity> listAuthenticatedAthleteActivities(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * <p>
	 * Pagination is supported
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after parameter is used.
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
	 * <p>
	 * Pagination is not supported. Returns only the first page of activities.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default.
	 */
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync();

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns only the first page of activities.
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
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 */
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after);

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
	 * Pagination is supported.
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
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 */
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final LocalDateTime before, final LocalDateTime after, final Paging pagingInstruction);

	/**
	 * <p>
	 * This endpoint returns a list of {@link StravaActivity activities} for the authenticated {@link StravaAthlete}.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/activities
	 * </p>
	 *
	 * <p>
	 * Pagination is supported
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return (A {@link CompletableFuture} which returns) Returns an array of {@link StravaActivity} summary representations sorted newest first by default. Will be sorted oldest first if the after
	 *         parameter is used.
	 */
	public CompletableFuture<List<StravaActivity>> listAuthenticatedAthleteActivitiesAsync(final Paging pagingInstruction);

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public List<StravaActivity> listFriendsActivities();

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
	 */
	public List<StravaActivity> listFriendsActivities(final Paging pagingInstruction);

	/**
	 * <p>
	 * List the recent activities performed by those the current authenticated {@link StravaAthlete} is following.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/activities/following
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/activities/">http://strava.github.io/api/v3/activities/</a>
	 *
	 * @return (A {@link CompletableFuture} which returns) Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public CompletableFuture<List<StravaActivity>> listFriendsActivitiesAsync();

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
	 * @return (A {@link CompletableFuture} which returns) Returns an array of activity summary representations sorted newest first by start_date.
	 */
	public CompletableFuture<List<StravaActivity>> listFriendsActivitiesAsync(final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns the activities that were matched as "with this group". The number equals activity.athlete_count-1.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * @param activityId
	 *            StravaActivity id for which related activities should be listed
	 * @return List of related activities (not including the main activity)
	 */
	public List<StravaActivity> listRelatedActivities(final Long activityId);

	/**
	 * <p>
	 * Returns the activities that were matched as "with this group". The number equals activity.athlete_count-1.
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
	 * @param activityId
	 *            StravaActivity id for which related activities should be listed
	 * @param pagingInstruction
	 *            Paging instructions
	 * @return List of related activities (not including the main activity)
	 */
	public List<StravaActivity> listRelatedActivities(final Long activityId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns the activities that were matched as "with this group". The number equals activity.athlete_count-1.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if the {@link StravaActivity} does not exist
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Only the first page is returned by Strava.
	 * </p>
	 *
	 * @param activityId
	 *            StravaActivity id for which related activities should be listed
	 * @return (A {@link CompletableFuture} which returns) List of related activities (not including the main activity)
	 */
	public CompletableFuture<List<StravaActivity>> listRelatedActivitiesAsync(final Long activityId);

	/**
	 * <p>
	 * Returns the activities that were matched as "with this group". The number equals activity.athlete_count-1.
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
	 * @param activityId
	 *            StravaActivity id for which related activities should be listed
	 * @param pagingInstruction
	 *            Paging instructions
	 * @return (A {@link CompletableFuture} which returns) List of related activities (not including the main activity)
	 */
	public CompletableFuture<List<StravaActivity>> listRelatedActivitiesAsync(final Long activityId, final Paging pagingInstruction);

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
	 * @param activityId
	 *            The identifier of the activity to be updated
	 * @param activity
	 *            The {@link StravaActivityUpdate} to be updated
	 * @return Returns a detailed representation of the updated {@link StravaActivity}.
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 *
	 */
	public StravaActivity updateActivity(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException;

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
	 * @param activityId
	 *            The identifier of the activity to be updated
	 * @param activity
	 *            The {@link StravaActivityUpdate} to be updated
	 * @return (A {@link CompletableFuture} which returns) Returns a detailed representation of the updated {@link StravaActivity}.
	 * @throws NotFoundException
	 *             If the activity with the given id does not exist
	 *
	 */
	public CompletableFuture<StravaActivity> updateActivityAsync(final Long activityId, final StravaActivityUpdate activity) throws NotFoundException;
}

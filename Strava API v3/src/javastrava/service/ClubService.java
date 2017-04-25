package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaActivity;
import javastrava.model.StravaAthlete;
import javastrava.model.StravaClub;
import javastrava.model.StravaClubAnnouncement;
import javastrava.model.StravaClubEvent;
import javastrava.model.StravaClubMembershipResponse;
import javastrava.model.reference.StravaResourceState;
import javastrava.util.Paging;

/**
 * <p>
 * {@link StravaClub} related services
 * </p>
 *
 * <p>
 * {@link StravaClub Clubs} represent groups of {@link StravaAthlete athletes} on Strava. They can be public or private.
 * </p>
 *
 * <p>
 * Only members of private clubs can access their details.
 * </p>
 *
 * <p>
 * The object is returned in summary or detailed {@link StravaResourceState representations}.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface ClubService extends StravaService {
	/**
	 * <p>
	 * Retrieve details about a specific {@link StravaClub club}. The club must be public or the current {@link StravaAthlete athlete} must be a member.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty club (with only the id set) if the club is private and the authenticated athlete is not a member
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-details">http://strava.github.io/api/v3/clubs/#get-details</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} to be retrieved
	 * @return Returns a detailed club representation.
	 */
	public StravaClub getClub(final Integer clubId);

	/**
	 * <p>
	 * Retrieve details about a specific {@link StravaClub club}. The club must be public or the current {@link StravaAthlete athlete} must be a member.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty club (with only the id set) if the club is private and the authenticated athlete is not a member
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-details">http://strava.github.io/api/v3/clubs/#get-details</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} to be retrieved
	 * @return Returns a detailed club representation.
	 */
	public CompletableFuture<StravaClub> getClubAsync(final Integer clubId);

	/**
	 * <p>
	 * Join a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param clubId
	 *            ID of the {@link StravaClub} to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public StravaClubMembershipResponse joinClub(final Integer clubId);

	/**
	 * <p>
	 * Join a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param clubId
	 *            ID of the {@link StravaClub} to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public CompletableFuture<StravaClubMembershipResponse> joinClubAsync(final Integer clubId);

	/**
	 * <p>
	 * Leave a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param clubId
	 *            ID of the club to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public StravaClubMembershipResponse leaveClub(final Integer clubId);

	/**
	 * <p>
	 * Leave a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 *
	 * @param clubId
	 *            ID of the club to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public CompletableFuture<StravaClubMembershipResponse> leaveClubAsync(final Integer clubId);

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is NOT supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @return List of administrators
	 */
	public List<StravaAthlete> listAllClubAdmins(final Integer clubId);

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is NOT supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @return List of administrators - call {@link CompletableFuture#complete(Object)} when ready.
	 */
	public CompletableFuture<List<StravaAthlete>> listAllClubAdminsAsync(final Integer clubId);

	/**
	 * <p>
	 * Convenience method for returning ALL of the members of a club
	 * </p>
	 *
	 * <p>
	 * Returns ALL the members, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - CLUBS WITH MANY MEMBERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public List<StravaAthlete> listAllClubMembers(final Integer clubId);

	/**
	 * <p>
	 * Convenience method for returning ALL of the members of a club
	 * </p>
	 *
	 * <p>
	 * Returns ALL the members, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - CLUBS WITH MANY MEMBERS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaAthlete>> listAllClubMembersAsync(final Integer clubId);

	/**
	 * <p>
	 * Retrieve ALL the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * <p>
	 * Pagination is supported. However, the results are limited to the last 200 total activities by club members.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId);

	/**
	 * <p>
	 * Retrieve ALL the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * <p>
	 * Pagination is supported. However, the results are limited to the last 200 total activities by club members.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaActivity>> listAllRecentClubActivitiesAsync(final Integer clubId);

	/**
	 * <p>
	 * Fetch an array of {@link StravaClub clubs} that the currently authenticated {@link StravaAthlete athlete} is a member of.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all clubs of which the athlete is a member.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/clubs
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-athletes">http://strava.github.io/api/v3/clubs/#get-athletes</a>
	 *
	 * @return Returns a list of {@link StravaClub club} {@link StravaResourceState summary} representations.
	 */
	public List<StravaClub> listAuthenticatedAthleteClubs();

	/**
	 * <p>
	 * Fetch an array of {@link StravaClub clubs} that the currently authenticated {@link StravaAthlete athlete} is a member of.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns all clubs of which the athlete is a member.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete/clubs
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-athletes">http://strava.github.io/api/v3/clubs/#get-athletes</a>
	 *
	 * @return Returns a list of {@link StravaClub club} {@link StravaResourceState summary} representations.
	 */
	public CompletableFuture<List<StravaClub>> listAuthenticatedAthleteClubsAsync();

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is NOT supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @return List of administrators
	 */
	public List<StravaAthlete> listClubAdmins(final Integer clubId);

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @param paging Paging instruction
	 * @return List of administrators
	 */
	public List<StravaAthlete> listClubAdmins(final Integer clubId, final Paging paging);

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is NOT supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @return {@link CompletableFuture} which will return the List of administrators
	 */
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId);

	/**
	 * <p>
	 * List the {@link StravaAthlete}s who are administrators of a club.
	 * </p>
	 *
	 * <p>The athletes are returned in summary representation</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club.</p>
	 *
	 * <p>Pagination is supported</p>
	 *
	 * @param clubId The club whose administrators should be listed
	 * @param paging Paging instruction
	 * @return {@link CompletableFuture} which will return the List of administrators
	 */
	public CompletableFuture<List<StravaAthlete>> listClubAdminsAsync(final Integer clubId, final Paging paging);

	/**
	 * <p>Announcements are posts sent by Club Admins or Owners to the members of a club.</p>
	 *
	 * <p>Only members of private clubs can access their announcements. </p>
	 *
	 * <p>The objects are returned in summary representation.</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club</p>
	 *
	 * <p>Pagination is not supported</p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-announcements">http://strava.github.io/api/v3/clubs/#get-announcements</a>
	 *
	 * @param clubId The id of the {@link StravaClub} for which announcements should be returned.
	 * @return Returns a list of {@link StravaClubAnnouncement announcements}
	 */
	public List<StravaClubAnnouncement> listClubAnnouncements(final Integer clubId);

	/**
	 * <p>Announcements are posts sent by Club Admins or Owners to the members of a club.</p>
	 *
	 * <p>Only members of private clubs can access their announcements. </p>
	 *
	 * <p>The objects are returned in summary representation.</p>
	 *
	 * <p>Returns <code>null</code> if the club with the given id does not exist.</p>
	 *
	 * <p>Returns an empty list if the club is private and the authorised athlete is not a member of the club</p>
	 *
	 * <p>Pagination is not supported</p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-announcements">http://strava.github.io/api/v3/clubs/#get-announcements</a>
	 *
	 * @param clubId The id of the {@link StravaClub} for which announcements should be returned.
	 * @return Returns a list of {@link StravaClubAnnouncement announcements}
	 */
	public CompletableFuture<List<StravaClubAnnouncement>> listClubAnnouncementsAsync(final Integer clubId);

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
	 * @see <a href="http://strava.github.io/api/partner/v3/clubs/#get-group-events">http://strava.github.io/api/partner/v3/clubs/#get-group-events</a>
	 * @param clubId Club identifier
	 * @return List of all club events
	 */
	public List<StravaClubEvent> listClubGroupEvents(final Integer clubId);

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
	 * @see <a href="http://strava.github.io/api/partner/v3/clubs/#get-group-events">http://strava.github.io/api/partner/v3/clubs/#get-group-events</a>
	 * @param clubId Club identifier
	 * @return List of all club events (to retrieve call {@link CompletableFuture#get()})
	 */
	public CompletableFuture<List<StravaClubEvent>> listClubGroupEventsAsync(final Integer clubId);

	/**
	 * <p>
	 * Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns only the first page of members.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public List<StravaAthlete> listClubMembers(final Integer clubId);

	/**
	 * <p>
	 * Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public List<StravaAthlete> listClubMembers(final Integer clubId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * Pagination is not supported. Returns only the first page of members.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId);

	/**
	 * <p>
	 * Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * Pagination is supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the club is private
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/clubs/:id/members
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaAthlete>> listClubMembersAsync(final Integer clubId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of activities.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer clubId);

	/**
	 * <p>
	 * Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * <p>
	 * Pagination is supported. However, the results are limited to the last 200 total activities by club members.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer clubId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported. Returns only the first page of activities.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId);

	/**
	 * <p>
	 * Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.
	 * </p>
	 *
	 * <p>
	 * The authenticated athlete must be a member of the club.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if club with the given id does not exist
	 * </p>
	 *
	 * <p>
	 * Returns an empty list if the authorised athlete is not a member of the club
	 * </p>
	 *
	 * <p>
	 * Pagination is supported. However, the results are limited to the last 200 total activities by club members.
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 *
	 * @param clubId
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public CompletableFuture<List<StravaActivity>> listRecentClubActivitiesAsync(final Integer clubId, final Paging pagingInstruction);
}

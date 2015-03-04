package javastrava.api.v3.service;

import java.util.List;

import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaClubMembershipResponse;
import javastrava.api.v3.model.reference.StravaResourceState;
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
public interface ClubServices extends StravaServices {
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
	 * @param id
	 *            The id of the {@link StravaClub} to be retrieved
	 * @return Returns a detailed club representation.
	 */
	public StravaClub getClub(final Integer id);

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
	 * @param id
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public List<StravaAthlete> listClubMembers(final Integer id);

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
	 * @param id
	 *            The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 */
	public List<StravaAthlete> listClubMembers(final Integer id, final Paging pagingInstruction);

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
	 * @param id
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer id);

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
	 * @param id
	 *            The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 */
	public List<StravaActivity> listRecentClubActivities(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Join a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 * 
	 * @param id
	 *            ID of the {@link StravaClub} to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public StravaClubMembershipResponse joinClub(final Integer id);

	/**
	 * <p>
	 * Leave a club on behalf of the authenticated user. An access token with write permissions is required.
	 * </p>
	 * 
	 * @param id
	 *            ID of the club to join
	 * @return Response detailing whether request was successful and whether the member is active
	 */
	public StravaClubMembershipResponse leaveClub(final Integer id);
	
	public List<StravaAthlete> listAllClubMembers(final Integer clubId);
	
	public List<StravaActivity> listAllRecentClubActivities(final Integer clubId);
}

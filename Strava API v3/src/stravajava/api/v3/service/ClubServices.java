package stravajava.api.v3.service;

import java.util.List;

import stravajava.api.v3.model.StravaActivity;
import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaClub;
import stravajava.api.v3.model.StravaClubMembershipResponse;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * <p>{@link StravaClub} related services</p>
 * 
 * <p>{@link StravaClub Clubs} represent groups of {@link StravaAthlete athletes} on Strava. They can be public or private.</p>
 * 
 * <p>Only members of private clubs can access their details.</p>
 * 
 * <p>The object is returned in summary or detailed {@link StravaResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface ClubServices {
	/**
	 * <p>Retrieve details about a specific {@link StravaClub club}. The club must be public or the current {@link StravaAthlete athlete} must be a member.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Returns an empty club (with only the id set) if the club is private and the authenticated athlete is not a member</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-details>http://strava.github.io/api/v3/clubs/#get-details</a>
	 * 
	 * @param id The id of the {@link StravaClub} to be retrieved
	 * @return Returns a detailed club representation.
	 * @throws UnauthorizedException If the service's security token is invalid
	 */
	public StravaClub getClub(Integer id);
	
	/**
	 * <p>Fetch an array of {@link StravaClub clubs} that the currently authenticated {@link StravaAthlete athlete} is a member of.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/clubs
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-athletes">http://strava.github.io/api/v3/clubs/#get-athletes</a>
	 * 
	 * @return Returns a list of {@link StravaClub club} {@link StravaResourceState summary} representations.
	 */
	public List<StravaClub> listAuthenticatedAthleteClubs();
	
	/**
	 * <p>Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Returns an empty list if the club is private</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id/members</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 * 
	 * @param id The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 * @throws UnauthorizedException If the service's access token is invalid
	 */
	public List<StravaAthlete> listClubMembers(Integer id);
	
	/**
	 * <p>Retrieve summary information about member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Returns an empty list if the club is private</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id/members</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 * 
	 * @param id The id of the {@link StravaClub} whose member {@link StravaAthlete athletes} should be returned
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary {@link StravaResourceState representations}.
	 * @throws UnauthorizedException If the service's access token is invalid
	 */
	public List<StravaAthlete> listClubMembers(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.</p>
	 * 
	 * <p>The authenticated athlete must be a member of the club.</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Returns an empty list if the authorised athlete is not a member of the club</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 * 
	 * @param id The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 * @throws UnauthorizedException If the service's access token is invalid
	 */
	public List<StravaActivity> listRecentClubActivities(Integer id);
	
	/**
	 * <p>Retrieve the recent {@link StravaActivity activities} performed by member {@link StravaAthlete athletes} of a specific {@link StravaClub club}.</p>
	 * 
	 * <p>The authenticated athlete must be a member of the club.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Returns an empty list if the authorised athlete is not a member of the club</p>
	 * 
	 * <p>Pagination is supported. However, the results are limited to the last 200 total activities by club members.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 * 
	 * @param id The id of the {@link StravaClub} for which recent {@link StravaActivity activities} are to be returned.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link StravaActivity activity} summary {@link StravaResourceState representations}.
	 * @throws UnauthorizedException If the service's access token is invalid
	 */
	public List<StravaActivity> listRecentClubActivities(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Join a club on behalf of the authenticated user. An access token with write permissions is required.</p>
	 * 
	 * @param id ID of the {@link StravaClub} to join
	 * @return Response detailing whether request was successful and whether the member is active
	 * @throws NotFoundException If the club does not exist
	 * @throws UnauthorizedException If the user does not have a token with write permission or the token is invalid
	 */
	public StravaClubMembershipResponse joinClub(Integer id);
	
	/**
	 * <p>Leave a club on behalf of the authenticated user. An access token with write permissions is required.</p>
	 * 
	 * @param id ID of the club to join
	 * @return Response detailing whether request was successful and whether the member is active
	 * @throws NotFoundException If the club does not exist
	 * @throws UnauthorizedException If the user does not have a token with write permission or the token is invalid
	 */
	public StravaClubMembershipResponse leaveClub(Integer id);
}

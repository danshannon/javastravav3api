package com.danshannon.strava.api.service;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * <p>{@link Club} related services</p>
 * 
 * <p>{@link Club Clubs} represent groups of {@link Athlete athletes} on Strava. They can be public or private.</p>
 * 
 * <p>Only members of private clubs can access their details.</p>
 * 
 * <p>The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface ClubServices {
	/**
	 * <p>Retrieve details about a specific {@link Club club}. The club must be public or the current {@link Athlete athlete} must be a member.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-details>http://strava.github.io/api/v3/clubs/#get-details</a>
	 * 
	 * @param id The id of the {@link Club} to be retrieved
	 * @return Returns a detailed club representation.
	 */
	public Club getClub(Integer id);
	
	/**
	 * <p>Fetch an array of {@link Club clubs} that the currently authenticated {@link Athlete athlete} is a member of.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/clubs
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-athletes">http://strava.github.io/api/v3/clubs/#get-athletes</a>
	 * 
	 * @return Returns a {@link Club club} summary {@link ResourceState representation}.
	 */
	public Club[] listAuthenticatedAthleteClubs();
	
	/**
	 * <p>Retrieve summary information about member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id/members</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 * 
	 * @param id The id of the {@link Club} whose member {@link Athlete athletes} should be returned
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Athlete athlete} summary {@link ResourceState representations}.
	 */
	public Athlete[] listClubMembers(Integer id, Integer page, Integer perPage);
	
	/**
	 * <p>Retrieve the recent {@link Activity activities} performed by member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>The authenticated athlete must be a member of the club.</p>
	 * 
	 * <p>Pagination is supported. However, the results are limited to the last 200 total activities by club members.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 * 
	 * @param id The id of the {@link Club} for which recent {@link Activity activities} are to be returned.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Activity activity} summary {@link ResourceState representations}.
	 */
	public Activity[] listRecentClubActivities(Integer id, Integer page, Integer perPage);
}

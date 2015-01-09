package com.danshannon.strava.api.service;

import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.SegmentEffort;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * Athlete related services
 * 
 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
 * @author Dan Shannon
 *
 */
public interface AthleteServices {
	/**
	 * <p>This request is used to retrieve information about the currently authenticated {@link Athlete athlete}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/"/>http://strava.github.io/api/v3/athlete/</a>
	 * @return Returns a detailed representation of the {@link Athlete athlete} 
	 */
	public Athlete getAuthenticatedAthlete() throws UnauthorizedException;
	
	/**
	 * <p>This request is used to retrieve information about any {@link Athlete athlete} on Strava.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete does not exist</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/"/>http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id The id of the {@link Athlete athlete} to be returned
	 * @return Returns a summary representation of the {@link Athlete athlete} even if the indicated athlete matches the authenticated athlete.
	 */
	public Athlete getAthlete(Integer id);
	
	/**
	 * <p>Updates the personal details of the currently authenticated {@link Athlete athlete}.</p>
	 * 
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Only updates city, state, country, gender (sex) and weight.</p>
	 * 
	 * <p>URL PUT https://www.strava.com/api/v3/athlete</p>
	 * 
	 * @throws NotFoundException If the athlete does not exist (this is almost impossible, but just in case the athlete has somehow removed themselves from Strava altogether)
	 * @throws UnauthorizedException If the security token in use does not have write access
	 *
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 */
	public void updateAuthenticatedAthlete(String city, String state, String country, Gender sex, Float weight) throws UnauthorizedException, NotFoundException;
	
	/**
	 * <p>Returns an array of {@link SegmentEffort segment efforts} representing KOMs/QOMs and course records held by the given athlete.</p>
	 * 
	 * <p>Results are sorted by date, newest first.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/koms</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id The id of the {@link Athlete athlete} whose KOM's are to be returned
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link SegmentEffort segment effort} summary representations
	 */
	public SegmentEffort[] listAthleteKOMs(Integer id, Integer page, Integer perPage);
	
	/**
	 * <p>Friends are users the current {@link Athlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>This request is for the authenticated athlete's friends.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Athlete athlete} summary representations.
	 */
	public Athlete[] listAuthenticatedAthleteFriends(Integer page, Integer perPage);
	
	/**
	 * <p>Friends are users an {@link Athlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>If the indicated athlete has blocked the authenticated athlete, the result will be an empty array.</p> 
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link Athlete athlete} whose friends are to be listed
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return List of {@link Athlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public Athlete[] listAthleteFriends(Integer id, Integer page, Integer perPage);
	
	/**
	 * <p>Retrieve the {@link Athlete athletes} who both the authenticated athlete and the indicated athlete are following.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/both-following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link Athlete athlete} for whom the list of mutual friends is to be generated
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Athlete athlete} summary representations.
	 */
	public Athlete[] listAthletesBothFollowing(Integer id, Integer page, Integer perPage);
}

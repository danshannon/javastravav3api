package javastrava.api.v3.service;

import java.util.List;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaStatistics;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.util.Paging;

/**
 * StravaAthlete related services
 * 
 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
 * @author Dan Shannon
 *
 */
public interface AthleteServices {
	/**
	 * <p>
	 * This request is used to retrieve information about the currently authenticated {@link StravaAthlete athlete}.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athlete
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * @return Returns a detailed representation of the {@link StravaAthlete athlete}
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public StravaAthlete getAuthenticatedAthlete();

	/**
	 * <p>
	 * This request is used to retrieve information about any {@link StravaAthlete athlete} on Strava.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete does not exist
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} to be returned
	 * @return Returns a summary representation of the {@link StravaAthlete athlete} even if the indicated athlete matches the authenticated athlete.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public StravaAthlete getAthlete(final Integer id) throws UnauthorizedException;

	/**
	 * <p>
	 * Updates the personal details of the currently authenticated {@link StravaAthlete athlete}.
	 * </p>
	 * 
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 * 
	 * <p>
	 * Only updates city, state, country, gender (sex) and weight.
	 * </p>
	 * 
	 * <p>
	 * URL PUT https://www.strava.com/api/v3/athlete
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @throws UnauthorizedException
	 *             If the security token in use is invalid or does not have write access
	 * @param city The city where the athlete wants Strava to think they live
	 * @param state The state, county or whatever the athlete wants Strava to think they live
	 * @param country The country where the athlete wants Strava to think they live
	 * @param sex The gender the athlete wants Strava to think they identify with
	 * @param weight The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight);

	/**
	 * <p>
	 * Returns an array of {@link StravaSegmentEffort segment efforts} representing KOMs/QOMs and course records held by the given athlete.
	 * </p>
	 * 
	 * <p>
	 * Results are sorted by date, newest first.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/koms
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id);

	/**
	 * <p>
	 * Returns an array of {@link StravaSegmentEffort segment efforts} representing KOMs/QOMs and course records held by the given athlete.
	 * </p>
	 * 
	 * <p>
	 * Results are sorted by date, newest first.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/koms
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's
	 * activity feed.
	 * </p>
	 * 
	 * <p>
	 * This request is for the authenticated athlete's friends.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported. Returns only the first page of athletes.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/friends
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends();

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's
	 * activity feed.
	 * </p>
	 * 
	 * <p>
	 * This request is for the authenticated athlete's friends.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/friends
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction);

	/**
	 * <p>
	 * Friends are users an {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
	 * </p>
	 * 
	 * <p>
	 * If the indicated athlete has blocked the authenticated athlete, the result will be an empty array.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/friends
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the
	 *         currently authenticated athlete.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Friends are users an {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
	 * </p>
	 * 
	 * <p>
	 * If the indicated athlete has blocked the authenticated athlete, the result will be an empty array.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported. Returns only the first page of athletes.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/friends
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the
	 *         currently authenticated athlete.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer id);

	/**
	 * <p>
	 * Retrieve the {@link StravaAthlete athletes} who both the authenticated athlete and the indicated athlete are following.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported. Returns only the first page of athletes.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/both-following
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id);

	/**
	 * <p>
	 * Retrieve the {@link StravaAthlete athletes} who both the authenticated athlete and the indicated athlete are following.
	 * </p>
	 * 
	 * <p>
	 * Pagination is supported.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/both-following
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 * @throws UnauthorizedException
	 *             If the service's access token is invalid
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer id, final Paging pagingInstruction);

	/**
	 * <p>
	 * Returns recent (last 4 weeks), year to date and all time stats for a given athlete. Only available for the authenticated athlete.
	 * </p>
	 * 
	 * <p>
	 * This is the recommended endpoint when polling for athlete upload events.
	 * </p>
	 * 
	 * <p>
	 * Pagination is not supported. Returns all statistics for the athlete.
	 * </p>
	 * 
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/stats
	 * </p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/#stats">http://strava.github.io/api/v3/athlete/#stats</a>
	 * 
	 * @param id
	 *            The id of the athlete (must match authenticated athlete)
	 * @return Strava statistics object; values are in seconds and metres.
	 */
	public StravaStatistics statistics(final Integer id);
}

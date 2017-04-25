package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaAthlete;
import javastrava.model.StravaAthleteZones;
import javastrava.model.StravaSegmentEffort;
import javastrava.model.StravaStatistics;
import javastrava.model.reference.StravaGender;
import javastrava.service.exception.UnauthorizedException;
import javastrava.util.Paging;

/**
 * StravaAthlete related services
 *
 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
 * @author Dan Shannon
 *
 */
public interface AthleteService extends StravaService {
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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} to be returned
	 * @return Returns a summary representation of the {@link StravaAthlete athlete} even if the indicated athlete matches the authenticated athlete.
	 */
	public StravaAthlete getAthlete(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} to be returned
	 * @return Returns a summary representation of the {@link StravaAthlete athlete} even if the indicated athlete matches the authenticated athlete.
	 */
	public CompletableFuture<StravaAthlete> getAthleteAsync(final Integer athleteId);

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
	public CompletableFuture<StravaAthlete> getAuthenticatedAthleteAsync();

	/**
	 * <p>
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1.
	 * </p>
	 *
	 * <p>
	 * Premium members who have set a functional threshold power (ftp) will see their power zones. Power zones are a Premium-only feature. Free members will not see the power part of this endpoint.
	 * </p>
	 *
	 * @return The athlete zones object
	 */
	public StravaAthleteZones getAuthenticatedAthleteZones();

	/**
	 * <p>
	 * Returns the current athlete’s heart rate and power zones. The min for Zone 1 is always 0 and the max for Zone 5 is always -1.
	 * </p>
	 *
	 * <p>
	 * Premium members who have set a functional threshold power (ftp) will see their power zones. Power zones are a Premium-only feature. Free members will not see the power part of this endpoint.
	 * </p>
	 *
	 * @return The athlete zones object (via a {@link CompletableFuture})
	 */
	public CompletableFuture<StravaAthleteZones> getAuthenticatedAthleteZonesAsync();

	/**
	 * <p>
	 * Convenience method for returning ALL of an athlete's friends
	 * </p>
	 *
	 * <p>
	 * Returns ALL the athlete's friends, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/friends
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @param athleteId
	 *            The athlete whose friends are to be listed
	 * @return List of athlete's friends, or <code>null</code> if the athlete does not exist
	 */
	public List<StravaAthlete> listAllAthleteFriends(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of an athlete's friends
	 * </p>
	 *
	 * <p>
	 * Returns ALL the athlete's friends, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/friends
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @param athleteId
	 *            The athlete whose friends are to be listed
	 * @return List of athlete's friends, or <code>null</code> if the athlete does not exist
	 */
	public CompletableFuture<List<StravaAthlete>> listAllAthleteFriendsAsync(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of an athlete's KOM's
	 * </p>
	 *
	 * <p>
	 * Returns ALL the athlete's KOM's, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY KOMS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/koms
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 *
	 * @param athleteId
	 *            The athlete whose KOM'ss are to be listed
	 * @return List of segment efforts for which the athlete is KOM, or <code>null</code> if the athlete does not exist
	 */
	public List<StravaSegmentEffort> listAllAthleteKOMs(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of an athlete's KOM's
	 * </p>
	 *
	 * <p>
	 * Returns ALL the athlete's KOM's, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * Returns <code>null</code> if athlete with the given id is not found.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY KOMS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/koms
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 *
	 * @param athleteId
	 *            The athlete whose KOM'ss are to be listed
	 * @return List of segment efforts for which the athlete is KOM, or <code>null</code> if the athlete does not exist
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listAllAthleteKOMsAsync(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of the athletes that both the authenticated athlete and the given athlete are following
	 * </p>
	 *
	 * <p>
	 * Returns ALL the followers, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/both-following
	 * </p>
	 *
	 * @param athleteId
	 *            The athlete who the list is to be generated for
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @return List of the authenticated athlete's friends
	 */
	public List<StravaAthlete> listAllAthletesBothFollowing(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of the athletes that both the authenticated athlete and the given athlete are following
	 * </p>
	 *
	 * <p>
	 * Returns ALL the followers, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/:id/both-following
	 * </p>
	 *
	 * @param athleteId
	 *            The athlete who the list is to be generated for
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @return List of the authenticated athlete's friends
	 */
	public CompletableFuture<List<StravaAthlete>> listAllAthletesBothFollowingAsync(final Integer athleteId);

	/**
	 * <p>
	 * Convenience method for returning ALL of the authenticated athlete's friends
	 * </p>
	 *
	 * <p>
	 * Returns ALL the authenticated athlete's friends, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/friends
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @return List of the authenticated athlete's friends
	 */
	public List<StravaAthlete> listAllAuthenticatedAthleteFriends();

	/**
	 * <p>
	 * Convenience method for returning ALL of the authenticated athlete's friends
	 * </p>
	 *
	 * <p>
	 * Returns ALL the authenticated athlete's friends, regardless of how many there are
	 * </p>
	 *
	 * <p>
	 * Pagination is NOT supported.
	 * </p>
	 *
	 * <p>
	 * USE WITH CAUTION - ATHLETES WITH MANY FRIENDS WILL REQUIRE MANY CALLS TO THE STRAVA API
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/athletes/friends
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 *
	 * @return List of the authenticated athlete's friends
	 */
	public CompletableFuture<List<StravaAthlete>> listAllAuthenticatedAthleteFriendsAsync();

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public List<StravaAthlete> listAthleteFriends(final Integer athleteId, final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public CompletableFuture<List<StravaAthlete>> listAthleteFriendsAsync(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public CompletableFuture<List<StravaAthlete>> listAthleteFriendsAsync(final Integer athleteId, final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(final Integer athleteId, final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listAthleteKOMsAsync(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public CompletableFuture<List<StravaSegmentEffort>> listAthleteKOMsAsync(final Integer athleteId, final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAthletesBothFollowing(final Integer athleteId, final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public CompletableFuture<List<StravaAthlete>> listAthletesBothFollowingAsync(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @param pagingInstruction
	 *            (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public CompletableFuture<List<StravaAthlete>> listAthletesBothFollowingAsync(final Integer athleteId, final Paging pagingInstruction);

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
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
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends();

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
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
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends(final Paging pagingInstruction);

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
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
	 */
	public CompletableFuture<List<StravaAthlete>> listAuthenticatedAthleteFriendsAsync();

	/**
	 * <p>
	 * Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete's activity feed.
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
	 */
	public CompletableFuture<List<StravaAthlete>> listAuthenticatedAthleteFriendsAsync(final Paging pagingInstruction);

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
	 * @param athleteId
	 *            The id of the athlete (must match authenticated athlete)
	 * @return Strava statistics object; values are in seconds and metres.
	 */
	public StravaStatistics statistics(final Integer athleteId);

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
	 * @param athleteId
	 *            The id of the athlete (must match authenticated athlete)
	 * @return Strava statistics object; values are in seconds and metres.
	 */
	public CompletableFuture<StravaStatistics> statisticsAsync(final Integer athleteId);

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
	 * @param city
	 *            The city where the athlete wants Strava to think they live
	 * @param state
	 *            The state, county or whatever the athlete wants Strava to think they live
	 * @param country
	 *            The country where the athlete wants Strava to think they live
	 * @param sex
	 *            The gender the athlete wants Strava to think they identify with
	 * @param weight
	 *            The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 */
	public StravaAthlete updateAuthenticatedAthlete(final String city, final String state, final String country, final StravaGender sex, final Float weight);

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
	 * @param city
	 *            The city where the athlete wants Strava to think they live
	 * @param state
	 *            The state, county or whatever the athlete wants Strava to think they live
	 * @param country
	 *            The country where the athlete wants Strava to think they live
	 * @param sex
	 *            The gender the athlete wants Strava to think they identify with
	 * @param weight
	 *            The weight that the athlete wants Strava to believe that they are
	 * @return Detailed representation of the updated athlete
	 */
	public CompletableFuture<StravaAthlete> updateAuthenticatedAthleteAsync(final String city, final String state, final String country, final StravaGender sex, final Float weight);
}

/**
 *
 */
package javastrava.util;

import java.util.ArrayList;
import java.util.List;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.api.v3.model.StravaSegmentLeaderboardEntry;
import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * @author danshannon
 *
 */
public class PrivacyUtils {

	/**
	 * <p>
	 * Check that an activity belongs to the authenticated user
	 * </p>
	 * @param activity The activity to check
	 * @return <code>true</code> if the activity belongs to the authenticated user, <code>false</code> otherwise
	 */
	private static boolean activityBelongsToAuthenticatedUser(final StravaActivity activity, final Token token) {
		return activity.getAthlete().getId().equals(token.getAthlete().getId());
	}

	/**
	 * <p>
	 * Removes private activities from the list (by changing private ones to activities with resourceState=PRIVATE)
	 * </p>
	 * @param activities The list of activities to process
	 * @param token The access token in use (so we can check if it has view_private acess)
	 * @return Modified list of activities
	 */
	public static List<StravaActivity> handlePrivateActivities(final List<StravaActivity> activities, final Token token) {
		if (activities == null) {
			return null;
		}

		final List<StravaActivity> returnedActivities = new ArrayList<StravaActivity>();
		for (final StravaActivity activity : activities) {
			// If the activity is not private then it's OK to include
			if (!activityIsPrivate(activity)) {
				returnedActivities.add(activity);
			}
			// Else if it *is* private, and there's no view_private access or it belongs to someone else, then add as the private activity
			else {
				if (activityBelongsToAuthenticatedUser(activity, token) && token.hasViewPrivate()) {
					returnedActivities.add(activity);
				} else {
					returnedActivities.add(PrivacyUtils.privateActivity(activity.getId()));
				}
			}
		}
		return returnedActivities;


	}

	/**
	 * <p>Checks if an activity is private</p>
	 * @param activity The activity to check
	 * @return <code>true</code> if and only if the privateActivity flag is set to Boolean.TRUE
	 */
	private static boolean activityIsPrivate(final StravaActivity activity) {
		return (activity.getPrivateActivity() != null && activity.getPrivateActivity().equals(Boolean.TRUE));
	}

	/**
	 * <p>Creates an activity with the given id and resourceState = {@link StravaResourceState#PRIVATE}</p>
	 * @param activityId the activity id to be given to the private activity
	 * @return The private activity
	 */
	public static StravaActivity privateActivity(final Integer activityId) {
		final StravaActivity activity = new StravaActivity();
		activity.setId(activityId);
		activity.setResourceState(StravaResourceState.PRIVATE);
		return activity;
	}

	/**
	 * <p>Creates a {@link StravaClub} with resourceState = {@link StravaResourceState#PRIVATE}</p>
	 * @param id The id of the club to create
	 * @return The private club
	 */
	public static StravaClub privateClubRepresentation(final Integer id) {
		final StravaClub club = new StravaClub();
		club.setId(id);
		club.setResourceState(StravaResourceState.PRIVATE);
		return club;
	}

	/**
	 * <p>Creates a {@link StravaGear} with resourceState = {@link StravaResourceState#PRIVATE}</p>
	 * @param id The id of the gear to create
	 * @return The private gear
	 */
	public static StravaGear privateGear(final String id) {
		final StravaGear gear = new StravaGear();
		gear.setId(id);
		gear.setResourceState(StravaResourceState.PRIVATE);
		return gear;
	}

	/**
	 * <p>Creates a {@link StravaSegmentEffort} with resourceState = {@link StravaResourceState#PRIVATE}
	 * @param id The id of the effort to create
	 * @return The private effort
	 */
	public static StravaSegmentEffort privateSegmentEffort(final Long id) {
		final StravaSegmentEffort effort = new StravaSegmentEffort();
		effort.setId(id);
		effort.setResourceState(StravaResourceState.PRIVATE);
		return effort;
	}

	/**
	 * <p>Creates a {@link StravaSegment} with resourceState = {@link StravaResourceState#PRIVATE}</p>
	 * @param id The id of the segment to create
	 * @return The private segment
	 */
	public static StravaSegment privateSegment(final Integer id) {
		final StravaSegment segment = new StravaSegment();
		segment.setId(id);
		segment.setResourceState(StravaResourceState.PRIVATE);
		return segment;
	}

	/**
	 * <p>
	 * Removes private segments from the given list (by replacing them with segments with resourceState = {@link StravaResourceState#PRIVATE}
	 * </p>
	 * @param segments The list of segments to be 'privatised'
	 * @param token The access token being used; will be checked for view_private access for segments belonging to the authenticated user
	 * @return The modified list of segments
	 */
	public static List<StravaSegment> handlePrivateSegments(final List<StravaSegment> segments, final Token token) {
		if (segments == null) {
			return null;
		}

		final List<StravaSegment> returnedSegments = new ArrayList<StravaSegment>();
		for (final StravaSegment segment : segments) {
			// If the segment is not flagged as private then its ok to include
			if (!segmentIsPrivate(segment)) {
				returnedSegments.add(segment);
			}
			// Otherwise if it belongs to the authenticated user and the token has view_private scope, then it's OK to return
			else {
				if (token.hasViewPrivate()) {
					returnedSegments.add(segment);
				} else {
					returnedSegments.add(PrivacyUtils.privateSegment(segment.getId()));
				}
			}
		}
		return returnedSegments;
	}

	/**
	 * <p>
	 * Checks if a segment is flagged as private
	 * </p>
	 * @param segment the segment to check
	 * @return <code>true</code> if the segment is flagged as private, <code>false</code> otherwise
	 */
	private static boolean segmentIsPrivate(final StravaSegment segment) {
		return (segment.getPrivateSegment() != null && segment.getPrivateSegment().equals(Boolean.TRUE));
	}

	/**
	 * <p>
	 * Creates a {@link StravaSegmentLeaderboard} with resourceState = {@link StravaResourceState#PRIVATE}
	 * </p>
	 * @return The private leaderboard
	 */
	public static StravaSegmentLeaderboard privateSegmentLeaderboard() {
		StravaSegmentLeaderboard leaderboard = new StravaSegmentLeaderboard();
		leaderboard = new StravaSegmentLeaderboard();
		leaderboard.setNeighborhoodCount(Integer.valueOf(1));
		leaderboard.setAthleteEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
		leaderboard.setEntries(new ArrayList<StravaSegmentLeaderboardEntry>());
		leaderboard.setEffortCount(Integer.valueOf(0));
		leaderboard.setEntryCount(Integer.valueOf(0));
		leaderboard.setResourceState(StravaResourceState.PRIVATE);
		return leaderboard;

	}

	/**
	 * @param efforts List of efforts to be 'privatised'
	 * @param token The access token in use
	 * @return Modified list of efforts
	 */
	public static List<StravaSegmentEffort> handlePrivateSegmentEfforts(final List<StravaSegmentEffort> efforts, final Token token) {
		if (efforts == null) {
			return null;
		}
		// TODO Issue #93 improve this!
		return efforts;
	}

}

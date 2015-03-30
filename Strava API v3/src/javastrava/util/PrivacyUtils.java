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
import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * @author danshannon
 *
 */
public class PrivacyUtils {

	/**
	 * @param activity
	 * @return
	 */
	private static boolean activityBelongsToAuthenticatedUser(final StravaActivity activity, final Token token) {
		return activity.getAthlete().getId().equals(token.getAthlete().getId());
	}

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
	 * @param activity
	 * @return
	 */
	private static boolean activityIsPrivate(final StravaActivity activity) {
		return (activity.getPrivateActivity() != null && activity.getPrivateActivity().equals(Boolean.TRUE));
	}

	public static StravaActivity privateActivity(final Integer activityId) {
		final StravaActivity activity = new StravaActivity();
		activity.setId(activityId);
		activity.setResourceState(StravaResourceState.PRIVATE);
		return activity;
	}

	public static StravaClub privateClubRepresentation(final Integer id) {
		final StravaClub club = new StravaClub();
		club.setId(id);
		club.setResourceState(StravaResourceState.PRIVATE);
		return club;
	}

	/**
	 * @param id
	 * @return
	 */
	public static StravaGear privateGear(final String id) {
		final StravaGear gear = new StravaGear();
		gear.setId(id);
		gear.setResourceState(StravaResourceState.PRIVATE);
		return gear;
	}

	/**
	 * @param id
	 * @return
	 */
	public static StravaSegmentEffort privateSegmentEffort(final Long id) {
		final StravaSegmentEffort effort = new StravaSegmentEffort();
		effort.setId(id);
		effort.setResourceState(StravaResourceState.PRIVATE);
		return effort;
	}

	/**
	 * @param id
	 * @return
	 */
	public static StravaSegment privateSegment(final Integer id) {
		final StravaSegment segment = new StravaSegment();
		segment.setId(id);
		segment.setResourceState(StravaResourceState.PRIVATE);
		return segment;
	}

	/**
	 * @param segments
	 * @return
	 */
	public static List<StravaSegment> handlePrivateSegments(final List<StravaSegment> segments, final Token token) {
		if (segments == null) {
			return null;
		}

		final List<StravaSegment> returnedSegments = new ArrayList<StravaSegment>();
		for (final StravaSegment segment : segments) {
			// If the activity is not flagged as private then its ok to include
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
	 * @param segment
	 * @return
	 */
	private static boolean segmentIsPrivate(final StravaSegment segment) {
		return (segment.getPrivateSegment() != null && segment.getPrivateSegment().equals(Boolean.TRUE));
	}

}

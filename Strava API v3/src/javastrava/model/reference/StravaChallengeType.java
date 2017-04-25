package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ActivityTypeSerializer;

/**
 * <p>
 * Strava challenge type
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaChallengeType implements StravaReferenceType<String> {
	/**
	 * Cumulative challenge
	 */
	CUMULATIVE_CHALLENGE(StravaConfig.string("StravaChallengeType.cumulative"), Messages.string("StravaChallengeType.cumulative.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Single segment challenge
	 */
	SEGMENT_CHALLENGE(StravaConfig.string("StravaChallengeType.segment"), Messages.string("StravaChallengeType.segment.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Single activity challenge
	 */
	SINGLE_ACTIVITY_CHALLENGE(StravaConfig.string("StravaChallengeType.singleActivity"), Messages.string("StravaChallengeType.singleActivity.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$//$NON-NLS-2$

	/**
	 * @param string
	 *            The string representation of the activity type as returned by the Strava API
	 * @return The {@link StravaChallengeType} with the matching id, or {@link StravaChallengeType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaChallengeType create(final String string) {
		for (final StravaChallengeType type : StravaChallengeType.values()) {
			if (type.getId().equals(string)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private String id;

	/**
	 * Description
	 */
	private String description;

	private StravaChallengeType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getValue() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id;
	}
}

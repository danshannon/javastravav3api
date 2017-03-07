package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ActivityTypeSerializer;

/**
 * <p>
 * Strava challenge type
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaChallengeType implements StravaReferenceType<Integer> {
	/**
	 * Cumulative challenge
	 */
	CUMULATIVE_CHALLENGE(StravaConfig.integer("StravaChallengeType.cumulative"), Messages.string("StravaChallengeType.cumulative.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Single segment challenge
	 */
	SEGMENT_CHALLENGE(StravaConfig.integer("StravaChallengeType.segment"), Messages.string("StravaChallengeType.segment.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Single activity challenge
	 */
	SINGLE_ACTIVITY_CHALLENGE(StravaConfig.integer("StravaChallengeType.singleActivity"), Messages.string("StravaChallengeType.singleActivity.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$//$NON-NLS-2$

	/**
	 * @param id
	 *            The string representation of the activity type as returned by the Strava API
	 * @return The {@link StravaChallengeType} with the matching id, or {@link StravaChallengeType#UNKNOWN} if there is no match
	 * @see ActivityTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaChallengeType create(final Integer id) {
		for (final StravaChallengeType type : StravaChallengeType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private Integer id;

	/**
	 * Description
	 */
	private String description;

	private StravaChallengeType(final Integer id, final String description) {
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
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getValue() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id.toString();
	}
}

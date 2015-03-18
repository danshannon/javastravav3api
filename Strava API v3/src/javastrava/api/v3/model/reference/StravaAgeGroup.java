package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.AgeGroupSerializer;

/**
 * <p>
 * Age group ranges used to generate filtered {@link StravaSegmentLeaderboard segment leaderboards}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaAgeGroup {
	/**
	 * Age 0-24
	 */
	AGE0_24(StravaConfig.string("StravaAgeGroup.0-24"), Messages.string("StravaAgeGroup.0-24.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Age 25-34
	 */
	AGE25_34(StravaConfig.string("StravaAgeGroup.25-34"), Messages.string("StravaAgeGroup.25-34.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Age 35-44
	 */
	AGE35_44(StravaConfig.string("StravaAgeGroup.35-44"), Messages.string("StravaAgeGroup.35-44.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Age 45-54
	 */
	AGE45_54(StravaConfig.string("StravaAgeGroup.45-54"), Messages.string("StravaAgeGroup.45-54.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Age 55-64
	 */
	AGE55_64(StravaConfig.string("StravaAgeGroup.55-64"), Messages.string("StravaAgeGroup.55-64.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Age 65+
	 */
	AGE65_PLUS(StravaConfig.string("StravaAgeGroup.65plus"), Messages.string("StravaAgeGroup.65plus.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaAgeGroup(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of the {@link StravaAgeGroup} to be used in JSON
	 * @see AgeGroupSerializer#serialize(StravaAgeGroup, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaAgeGroup} returned by the Strava API
	 * @return The matching {@link StravaAgeGroup}, or {@link StravaAgeGroup#UNKNOWN} if there is no match
	 */
	public static StravaAgeGroup create(final String id) {
		StravaAgeGroup[] ageGroups = StravaAgeGroup.values();
		for (StravaAgeGroup ageGroup : ageGroups) {
			if (ageGroup.getId().equals(id)) {
				return ageGroup;
			}
		}
		return StravaAgeGroup.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}

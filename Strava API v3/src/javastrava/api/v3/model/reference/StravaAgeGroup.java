package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;

/**
 * <p>
 * Age group ranges used to generate filtered {@link StravaSegmentLeaderboard segment leaderboards}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaAgeGroup {
	AGE0_24(Messages.getString("StravaAgeGroup.0-24"), Messages.getString("StravaAgeGroup.0-24.description")), //$NON-NLS-1$ //$NON-NLS-2$
	AGE25_34(Messages.getString("StravaAgeGroup.25-34"), Messages.getString("StravaAgeGroup.25-34.description")), //$NON-NLS-1$ //$NON-NLS-2$
	AGE35_44(Messages.getString("StravaAgeGroup.35-44"), Messages.getString("StravaAgeGroup.35-44.description")), //$NON-NLS-1$ //$NON-NLS-2$
	AGE45_54(Messages.getString("StravaAgeGroup.45-54"), Messages.getString("StravaAgeGroup.45-54.description")), //$NON-NLS-1$ //$NON-NLS-2$
	AGE55_64(Messages.getString("StravaAgeGroup.55-64"), Messages.getString("StravaAgeGroup.55-64.description")), //$NON-NLS-1$ //$NON-NLS-2$
	AGE65_PLUS(Messages.getString("StravaAgeGroup.65plus"), Messages.getString("StravaAgeGroup.65plus.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaAgeGroup(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}

	// For use as Jackson @JsonCreator
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

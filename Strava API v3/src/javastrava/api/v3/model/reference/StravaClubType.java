package javastrava.api.v3.model.reference;

import javastrava.config.Messages;

/**
 * <p>
 * Strava club type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClubType {
	CASUAL(Messages.getString("StravaClubType.casual"), Messages.getString("StravaClubType.casual.description")), //$NON-NLS-1$ //$NON-NLS-2$
	TEAM(Messages.getString("StravaClubType.racing"), Messages.getString("StravaClubType.racing.description")), //$NON-NLS-1$ //$NON-NLS-2$
	SHOP(Messages.getString("StravaClubType.shop"), Messages.getString("StravaClubType.shop.description")), //$NON-NLS-1$ //$NON-NLS-2$
	COMPANY(Messages.getString("StravaClubType.company"), Messages.getString("StravaClubType.company.description")), //$NON-NLS-1$ //$NON-NLS-2$
	OTHER(Messages.getString("StravaClubType.other"), Messages.getString("StravaClubType.other.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaClubType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaClubType create(final String id) {
		StravaClubType[] clubTypes = StravaClubType.values();
		for (StravaClubType clubType : clubTypes) {
			if (clubType.getId().equals(id)) {
				return clubType;
			}
		}
		return StravaClubType.UNKNOWN;
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

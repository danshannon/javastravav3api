package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.util.impl.gson.serializer.ClubTypeSerializer;

/**
 * <p>
 * Strava club type
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClubType {
	/**
	 * Casual
	 */
	CASUAL(StravaConfig.string("StravaClubType.casual"), Messages.string("StravaClubType.casual.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Racing team
	 */
	TEAM(StravaConfig.string("StravaClubType.racing"), Messages.string("StravaClubType.racing.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Shop club
	 */
	SHOP(StravaConfig.string("StravaClubType.shop"), Messages.string("StravaClubType.shop.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Company club
	 */
	COMPANY(StravaConfig.string("StravaClubType.company"), Messages.string("StravaClubType.company.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Other type of club
	 */
	OTHER(StravaConfig.string("StravaClubType.other"), Messages.string("StravaClubType.other.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaClubType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaClubType} to be used with the Strava API
	 * @see ClubTypeSerializer#serialize(StravaClubType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaClubType} returned by the Strava API
	 * @return The matching {@link StravaClubType}, or {@link StravaClubType#UNKNOWN} if there is no match
	 */
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

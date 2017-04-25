package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ClubTypeSerializer;

/**
 * <p>
 * Strava club type
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaClubType implements StravaReferenceType<String> {
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

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaClubType} returned by the Strava API
	 * @return The matching {@link StravaClubType}, or {@link StravaClubType#UNKNOWN} if there is no match
	 */
	public static StravaClubType create(final String id) {
		final StravaClubType[] clubTypes = StravaClubType.values();
		for (final StravaClubType clubType : clubTypes) {
			if (clubType.getId().equals(id)) {
				return clubType;
			}
		}
		return StravaClubType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private String	id;

	/**
	 * Description
	 */
	private String	description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaClubType(final String id, final String description) {
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

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaClubType} to be used with the Strava API
	 * @see ClubTypeSerializer#serialize(StravaClubType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

}

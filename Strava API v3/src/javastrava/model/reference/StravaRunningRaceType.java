package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.AthleteTypeSerializer;
import javastrava.model.StravaRunningRace;

/**
 * <p>
 * Type enumeration for {@link StravaRunningRace running races}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaRunningRaceType implements StravaReferenceType<Integer> {
	/**
	 * Road race
	 */
	ROAD(StravaConfig.integer("StravaRunningRaceType.road"), Messages.string("StravaRunningRaceType.road.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Trail running race
	 */
	TRAIL(StravaConfig.integer("StravaRunningRaceType.trail"), Messages.string("StravaRunningRaceType.trail.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Track race
	 */
	TRACK(StravaConfig.integer("StravaRunningRaceType.track"), Messages.string("StravaRunningRaceType.track.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Cross-country race
	 */
	CROSS_COUNTRY(StravaConfig.integer("StravaRunningRaceType.xc"), Messages.string("StravaRunningRaceType.xc.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown type
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 *
	 * @param id
	 *            The integer representation of the {@link StravaAthleteType} as returned by the Strava API
	 * @return The matching {@link StravaAthleteType}, or {@link StravaAthleteType#UNKNOWN} if there is no match
	 * @see AthleteTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaRunningRaceType create(final Integer id) {
		final StravaRunningRaceType[] categories = StravaRunningRaceType.values();
		for (final StravaRunningRaceType category : categories) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		return StravaRunningRaceType.UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private Integer id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations
	 *
	 * @param id
	 *            Identifier - also used when serialising/deserialising to JSON
	 * @param description
	 *            Description
	 */
	private StravaRunningRaceType(final Integer id, final String description) {
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
	public Integer getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 *
	 * @return The integer value to be used with the Strava API
	 * @see AthleteTypeSerializer#serialize(StravaAthleteType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public Integer getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}

}

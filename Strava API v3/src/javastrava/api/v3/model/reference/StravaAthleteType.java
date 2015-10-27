/**
 *
 */
package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.AthleteTypeSerializer;

/**
 * <p>
 * athlete’s default sport type: 0=cyclist, 1=runner
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaAthleteType {
	/**
	 * Cyclist
	 */
	CYCLIST(StravaConfig.integer("StravaAthleteType.cyclist"),Messages.string("StravaAthleteType.cyclist.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Runner
	 */
	RUNNER(StravaConfig.integer("StravaAthleteType.runner"),Messages.string("StravaAthleteType.runner.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of the {@link StravaAthleteType} as returned by the Strava API
	 * @return The matching {@link StravaAthleteType}, or {@link StravaAthleteType#UNKNOWN} if there is no match
	 * @see AthleteTypeSerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaAthleteType create(final Integer id) {
		final StravaAthleteType[] categories = StravaAthleteType.values();
		for (final StravaAthleteType category : categories) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		return StravaAthleteType.UNKNOWN;
	}
	/**
	 * Identifier
	 */
	private Integer	id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaAthleteType(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * @return The integer value to be used with the Strava API
	 * @see AthleteTypeSerializer#serialize(StravaAthleteType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
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

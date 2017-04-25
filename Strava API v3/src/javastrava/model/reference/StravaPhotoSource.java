package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ClimbCategorySerializer;

/**
 * <p>Identifies the source of a photo which has been attached to an activity</p>
 * @author Dan Shannon
 *
 */
public enum StravaPhotoSource implements StravaReferenceType<Integer>{
	/**
	 * Photo uploaded directly to Strava
	 */
	STRAVA(StravaConfig.integer("StravaPhotoSource.strava"),Messages.string("StravaPhotoSource.strava.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Photo from Instagram
	 */
	INSTAGRAM(StravaConfig.integer("StravaPhotoSource.instagram"),Messages.string("StravaPhotoSource.instagram.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"),Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The integer representation of the {@link StravaClimbCategory} as returned by the Strava API
	 * @return The matching {@link StravaClimbCategory}, or {@link StravaClimbCategory#UNKNOWN} if there is no match
	 * @see ClimbCategorySerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaPhotoSource create(final Integer id) {
		final StravaPhotoSource[] sources = StravaPhotoSource.values();
		for (final StravaPhotoSource source : sources) {
			if (source.getId().equals(id)) {
				return source;
			}
		}
		return StravaPhotoSource.UNKNOWN;
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
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 */
	private StravaPhotoSource(final Integer id, final String description) {
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
	 * @return The integer value to be used with the Strava API
	 * @see ClimbCategorySerializer#serialize(StravaClimbCategory, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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

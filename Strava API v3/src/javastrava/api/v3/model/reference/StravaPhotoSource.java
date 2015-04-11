/**
 *
 */
package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ClimbCategorySerializer;

/**
 * @author danshannon
 *
 */
public enum StravaPhotoSource {
	/**
	 * Photo uploaded directly to Strava
	 */
	STRAVA(Integer.valueOf(1),"Strava"),
	/**
	 * Photo from Instagram
	 */
	INSTAGRAM(Integer.valueOf(2),"Instagram"),
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
	private Integer id;

	private String description;

	private StravaPhotoSource(final Integer id, final String description) {
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
	 * @see ClimbCategorySerializer#serialize(StravaClimbCategory, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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

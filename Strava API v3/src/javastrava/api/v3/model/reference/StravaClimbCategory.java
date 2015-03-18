package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegment;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ClimbCategorySerializer;

/**
 * <p>
 * Strava climb categories as applied to {@link StravaSegment segments}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClimbCategory {
	/**
	 * Hors categorie
	 */
	HORS_CATEGORIE(StravaConfig.integer("StravaClimbCategory.hors_categorie"), Messages.string("StravaClimbCategory.hors_categorie.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Category 1
	 */
	CATEGORY1(StravaConfig.integer("StravaClimbCategory.cat1"), Messages.string("StravaClimbCategory.cat1.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Category 2
	 */
	CATEGORY2(StravaConfig.integer("StravaClimbCategory.cat2"), Messages.string("StravaClimbCategory.cat2.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Category 3
	 */
	CATEGORY3(StravaConfig.integer("StravaClimbCategory.cat3"), Messages.string("StravaClimbCategory.cat3.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Category 4
	 */
	CATEGORY4(StravaConfig.integer("StravaClimbCategory.cat4"), Messages.string("StravaClimbCategory.cat4.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Category 5
	 */
	FLAT(StravaConfig.integer("StravaClimbCategory.no_category"),Messages.string("StravaClimbCategory.no_category.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private Integer	id;
	private String	description;

	private StravaClimbCategory(final Integer id, final String description) {
		this.id = id;
		this.description = description;
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
	 * Used by JSON deserialisation
	 * @param id The integer representation of the {@link StravaClimbCategory} as returned by the Strava API
	 * @return The matching {@link StravaClimbCategory}, or {@link StravaClimbCategory#UNKNOWN} if there is no match
	 * @see ClimbCategorySerializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaClimbCategory create(final Integer id) {
		StravaClimbCategory[] categories = StravaClimbCategory.values();
		for (StravaClimbCategory category : categories) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		return StravaClimbCategory.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
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
		return this.id.toString();
	}
}

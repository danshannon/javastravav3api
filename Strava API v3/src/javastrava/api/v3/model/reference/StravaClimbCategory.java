package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegment;

/**
 * <p>
 * Strava climb categories as applied to {@link StravaSegment segments}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClimbCategory {
	HORS_CATEGORIE(5, "HC"),
	CATEGORY1(1, "Cat 1"),
	CATEGORY2(2, "Cat 2"),
	CATEGORY3(3, "Cat 3"),
	CATEGORY4(4, "Cat 4"),
	UNKNOWN(-1, "Unknown");

	private Integer	id;
	private String	description;

	private StravaClimbCategory(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public Integer getValue() {
		return this.id;
	}

	// @JsonCreator
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

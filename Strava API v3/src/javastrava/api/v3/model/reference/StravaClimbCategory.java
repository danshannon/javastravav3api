package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaSegment;
import javastrava.config.Messages;

/**
 * <p>
 * Strava climb categories as applied to {@link StravaSegment segments}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaClimbCategory {
	HORS_CATEGORIE(5, Messages.getString("StravaClimbCategory.hors_categorie.description")), //$NON-NLS-1$
	CATEGORY1(1, Messages.getString("StravaClimbCategory.cat1.description")), //$NON-NLS-1$
	CATEGORY2(2, Messages.getString("StravaClimbCategory.cat2.description")), //$NON-NLS-1$
	CATEGORY3(3, Messages.getString("StravaClimbCategory.cat3.description")), //$NON-NLS-1$
	CATEGORY4(4, Messages.getString("StravaClimbCategory.cat4.description")), //$NON-NLS-1$
	FLAT(0,Messages.getString("StravaClimbCategory.no_category.description")), //$NON-NLS-1$
	UNKNOWN(-1, Messages.getString("Common.unknown.description")); //$NON-NLS-1$

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

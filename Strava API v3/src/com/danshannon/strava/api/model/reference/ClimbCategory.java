package com.danshannon.strava.api.model.reference;


/**
 * @author Dan Shannon
 *
 */
public enum ClimbCategory {
	HORS_CATEGORIE(0,"HC"),
	CATEGORY1(1,"Cat 1"),
	CATEGORY2(2,"Cat 2"),
	CATEGORY3(3,"Cat 3"),
	CATEGORY4(4,"Cat 4"),
	UNKNOWN(-1,"Unknown");
	
	private Integer id;
	private String description;
	
	private ClimbCategory(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	// @JsonValue
	public Integer getValue() {
		return this.id;
	}
	
	// @JsonCreator
	public static ClimbCategory create(Integer id) {
		ClimbCategory[] categories = ClimbCategory.values();
		for (ClimbCategory category : categories) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		return ClimbCategory.UNKNOWN;
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

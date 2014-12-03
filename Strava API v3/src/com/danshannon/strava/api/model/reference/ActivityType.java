package com.danshannon.strava.api.model.reference;


/**
 * @author dshannon
 *
 */
public enum ActivityType {
	//possible values: ride, run, swim, workout, hike, walk, nordicski, alpineski, backcountryski, iceskate, inlineskate, kitesurf, rollerski, windsurf, workout, snowboard, snowshoe
	//Type detected from file overrides, uses athlete’s default type if not specified
	RIDE("ride","Ride"),
	RUN("run","Run"),
	SWIM("swim","Swim"),
	WORKOUT("workout","Workout"),
	HIKE("hike","Hike"),
	WALK("walk","Walk"),
	NORDIC_SKI("nordicski","Nordic skiing"),
	ALPINE_SKI("alpineski","Alpine skiing"),
	BACKCOUNTRY_SKI("backcountryski","Backcountry skiing (off-piste)"),
	ICE_SKATE("iceskate","Ice skating"),
	INLINE_SKATE("inlineskate","Inline skating"),
	KITESURF("kitesurf","Kite surfing"),
	ROLLERSKI("rollerski","Roller skiing"),
	WINDSURF("windsurf","Windsurfing"),
	SNOWBOARD("snowboard","Snowboarding"),
	SNOWSHOE("snowshoe","Snowshoeing"),
	UNKNOWN(null,"Unknown");
	
	private String id;
	private String description;
	
	private ActivityType(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	// For use as Jackson @JsonValue
	public String getValue() {
		return this.id;
	}
	
	// For use as Jackson @JsonCreator
	public static ActivityType create(String id) {
		for (ActivityType type : ActivityType.values()) {
			if (type.getId() != null && type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
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

package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaFrameType;
import javastrava.api.v3.model.reference.StravaGearType;
import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * StravaGear represents equipment used during an {@link StravaActivity}. Is either a bike, or shoes at present.
 * </p>
 * 
 * <p>
 * The object is returned in summary or detailed {@link StravaResourceState representations}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaGear {
	/**
	 * Unique identifier for the gear. Is prefixed with 'b' for bikes when returning via the API
	 */
	private String id;
	/**
	 * Is set to <code>true</code> if this is the athlete's default bike / shoes
	 */
	private Boolean primary;
	/**
	 * Name given to the gear by the owner
	 */
	private String name;
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Brand name
	 */
	private String brandName;
	/**
	 * Model name
	 */
	private String modelName;
	/**
	 * (Bikes only) 1 -> mtb, 2 -> cross, 3 -> road, 4 -> time trial
	 */
	private StravaFrameType frameType;
	/**
	 * Test description
	 */
	private String description;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	
	private StravaGearType gearType;
	
	/**
	 * @param id The id of the gear
	 */
	public void setId(final String id) {
		this.id = id;
		if (id != null) {
			this.gearType = StravaGearType.create(id.substring(0, 0));
		}
	}

	/**
	 * @return the primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}

	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return this.brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the frameType
	 */
	public StravaFrameType getFrameType() {
		return this.frameType;
	}

	/**
	 * @param frameType the frameType to set
	 */
	public void setFrameType(final StravaFrameType frameType) {
		this.frameType = frameType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @return the gearType
	 */
	public StravaGearType getGearType() {
		return this.gearType;
	}

	/**
	 * @param gearType the gearType to set
	 */
	public void setGearType(final StravaGearType gearType) {
		this.gearType = gearType;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
}

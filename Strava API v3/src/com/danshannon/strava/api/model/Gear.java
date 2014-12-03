package com.danshannon.strava.api.model;

import com.danshannon.strava.api.model.reference.FrameType;
import com.danshannon.strava.api.model.reference.ResourceState;

/**
 * <p>Gear represents equipment used during an {@link Activity}.</p>
 * 
 * <p>The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public class Gear {
	private String id;
	private Boolean primary;
	private String name;
	private Float distance;
	private String brandName;
	private String modelName;
	private FrameType frameType;
	private String description;
	private ResourceState resourceState;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @return the primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return this.brandName;
	}
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return this.modelName;
	}
	/**
	 * @return the frameType
	 */
	public FrameType getFrameType() {
		return this.frameType;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @return the resourceState
	 */
	public ResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	/**
	 * @param frameType the frameType to set
	 */
	public void setFrameType(FrameType frameType) {
		this.frameType = frameType;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(ResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Gear [" + (this.id != null ? "id=" + this.id + ", " : "")
				+ (this.primary != null ? "primary=" + this.primary + ", " : "")
				+ (this.name != null ? "name=" + this.name + ", " : "")
				+ (this.distance != null ? "distance=" + this.distance + ", " : "")
				+ (this.brandName != null ? "brandName=" + this.brandName + ", " : "")
				+ (this.modelName != null ? "modelName=" + this.modelName + ", " : "")
				+ (this.frameType != null ? "frameType=" + this.frameType + ", " : "")
				+ (this.description != null ? "description=" + this.description + ", " : "")
				+ (this.resourceState != null ? "resourceState=" + this.resourceState : "") + "]";
	}
}

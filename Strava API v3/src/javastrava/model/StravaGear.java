package javastrava.model;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaFrameType;
import javastrava.model.reference.StravaGearType;
import javastrava.model.reference.StravaResourceState;

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
public class StravaGear implements StravaCacheableEntity<String> {
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
	private StravaResourceState resourceState;

	/**
	 * Type of gear (bike or shoes)
	 */
	private StravaGearType gearType;

	/**
	 * No args constructor
	 */
	public StravaGear() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaGear)) {
			return false;
		}
		final StravaGear other = (StravaGear) obj;
		if (this.brandName == null) {
			if (other.brandName != null) {
				return false;
			}
		} else if (!this.brandName.equals(other.brandName)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.frameType != other.frameType) {
			return false;
		}
		if (this.gearType != other.gearType) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.modelName == null) {
			if (other.modelName != null) {
				return false;
			}
		} else if (!this.modelName.equals(other.modelName)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.primary == null) {
			if (other.primary != null) {
				return false;
			}
		} else if (!this.primary.equals(other.primary)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		return true;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return this.brandName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the frameType
	 */
	public StravaFrameType getFrameType() {
		return this.frameType;
	}

	/**
	 * @return the gearType
	 */
	public StravaGearType getGearType() {
		return this.gearType;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.brandName == null) ? 0 : this.brandName.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.frameType == null) ? 0 : this.frameType.hashCode());
		result = (prime * result) + ((this.gearType == null) ? 0 : this.gearType.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.modelName == null) ? 0 : this.modelName.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.primary == null) ? 0 : this.primary.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		return result;
	}

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param frameType
	 *            the frameType to set
	 */
	public void setFrameType(final StravaFrameType frameType) {
		this.frameType = frameType;
	}

	/**
	 * @param gearType
	 *            the gearType to set
	 */
	public void setGearType(final StravaGearType gearType) {
		this.gearType = gearType;
	}

	/**
	 * @param id
	 *            The id of the gear
	 */
	public void setId(final String id) {
		this.id = id;
		if (id != null) {
			this.gearType = StravaGearType.create(id.substring(0, 0));
		}
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaGear [id=" + this.id + ", primary=" + this.primary + ", name=" + this.name + ", distance=" + this.distance //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", brandName=" //$NON-NLS-1$
				+ this.brandName + ", modelName=" + this.modelName + ", frameType=" + this.frameType + ", description=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.description + ", resourceState=" //$NON-NLS-1$
				+ this.resourceState + ", gearType=" + this.gearType + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}

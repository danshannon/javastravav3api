package javastrava.api.v3.model;

import java.util.List;

import javastrava.api.v3.model.reference.StravaActivityZoneType;
import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Heart rate and power zones are set by the {@link StravaAthlete athlete}. This class returns the time (in seconds) spent in each zone during an
 * {@link StravaActivity activity}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaActivityZone {
	/**
	 * No args constructor
	 */
	public StravaActivityZone() {
		super();
	}

	/**
	 * Strava suffer score for the activity
	 */
	private Integer score;
	/**
	 * Data on each zone and time spent in it
	 */
	private List<StravaActivityZoneDistributionBucket> distributionBuckets;
	/**
	 * The type of activity zone - {@link StravaActivityZoneType#HEARTRATE} or {@link StravaActivityZoneType#POWER}
	 */
	private StravaActivityZoneType type;
	/**
	 * State of this resource
	 */
	private StravaResourceState resourceState;
	/**
	 * Is set to <code>true</code> if the information is based on sensor data included with the upload of the activity (i.e. a heart rate monitor for heart
	 * rates, or a power meter for power)
	 */
	private Boolean sensorBased;
	/**
	 * Points in this zone (contributing to the total suffer score)
	 */
	private Integer points;
	/**
	 * Is set to <code>true</code> if the athlete has customised the zones
	 */
	private Boolean customZones;
	/**
	 * Maximum heart rate reached during the activity
	 */
	private Integer max;

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return this.score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(final Integer score) {
		this.score = score;
	}

	/**
	 * @return the distributionBuckets
	 */
	public List<StravaActivityZoneDistributionBucket> getDistributionBuckets() {
		return this.distributionBuckets;
	}

	/**
	 * @param distributionBuckets
	 *            the distributionBuckets to set
	 */
	public void setDistributionBuckets(final List<StravaActivityZoneDistributionBucket> distributionBuckets) {
		this.distributionBuckets = distributionBuckets;
	}

	/**
	 * @return the type
	 */
	public StravaActivityZoneType getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final StravaActivityZoneType type) {
		this.type = type;
	}

	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @return the sensorBased
	 */
	public Boolean getSensorBased() {
		return this.sensorBased;
	}

	/**
	 * @param sensorBased
	 *            the sensorBased to set
	 */
	public void setSensorBased(final Boolean sensorBased) {
		this.sensorBased = sensorBased;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return this.points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(final Integer points) {
		this.points = points;
	}

	/**
	 * @return the customZones
	 */
	public Boolean getCustomZones() {
		return this.customZones;
	}

	/**
	 * @param customZones
	 *            the customZones to set
	 */
	public void setCustomZones(final Boolean customZones) {
		this.customZones = customZones;
	}

	/**
	 * @return the max
	 */
	public Integer getMax() {
		return this.max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(final Integer max) {
		this.max = max;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivityZone [score=" + this.score + ", distributionBuckets=" + this.distributionBuckets + ", type=" + this.type + ", resourceState=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.resourceState + ", sensorBased=" + this.sensorBased + ", points=" + this.points + ", customZones=" + this.customZones + ", max=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.max + "]"; //$NON-NLS-1$
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.customZones == null) ? 0 : this.customZones.hashCode());
		result = prime * result + ((this.distributionBuckets == null) ? 0 : this.distributionBuckets.hashCode());
		result = prime * result + ((this.max == null) ? 0 : this.max.hashCode());
		result = prime * result + ((this.points == null) ? 0 : this.points.hashCode());
		result = prime * result + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = prime * result + ((this.score == null) ? 0 : this.score.hashCode());
		result = prime * result + ((this.sensorBased == null) ? 0 : this.sensorBased.hashCode());
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
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
		if (!(obj instanceof StravaActivityZone)) {
			return false;
		}
		StravaActivityZone other = (StravaActivityZone) obj;
		if (this.customZones == null) {
			if (other.customZones != null) {
				return false;
			}
		} else if (!this.customZones.equals(other.customZones)) {
			return false;
		}
		if (this.distributionBuckets == null) {
			if (other.distributionBuckets != null) {
				return false;
			}
		} else if (!this.distributionBuckets.equals(other.distributionBuckets)) {
			return false;
		}
		if (this.max == null) {
			if (other.max != null) {
				return false;
			}
		} else if (!this.max.equals(other.max)) {
			return false;
		}
		if (this.points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!this.points.equals(other.points)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.score == null) {
			if (other.score != null) {
				return false;
			}
		} else if (!this.score.equals(other.score)) {
			return false;
		}
		if (this.sensorBased == null) {
			if (other.sensorBased != null) {
				return false;
			}
		} else if (!this.sensorBased.equals(other.sensorBased)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}
}

package javastrava.model;

import java.util.List;

import javastrava.model.reference.StravaResourceState;

/**
 * Set of athlete heart rate or power zones
 *
 * @author Dan Shannon
 *
 */
public class StravaAthleteZone implements StravaEntity {
	/**
	 * Is this a set of custom zones?
	 */
	private Boolean				customZones;
	/**
	 * The actual zones
	 */
	private List<StravaZone>	zones;

	/**
	 * no-args constructor
	 */
	public StravaAthleteZone() {
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
		if (!(obj instanceof StravaAthleteZone)) {
			return false;
		}
		final StravaAthleteZone other = (StravaAthleteZone) obj;
		if (this.customZones == null) {
			if (other.customZones != null) {
				return false;
			}
		} else if (!this.customZones.equals(other.customZones)) {
			return false;
		}
		if (this.zones == null) {
			if (other.zones != null) {
				return false;
			}
		} else if (!this.zones.equals(other.zones)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the customZones
	 */
	public Boolean getCustomZones() {
		return this.customZones;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the zones
	 */
	public List<StravaZone> getZones() {
		return this.zones;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.customZones == null) ? 0 : this.customZones.hashCode());
		result = (prime * result) + ((this.zones == null) ? 0 : this.zones.hashCode());
		return result;
	}

	/**
	 * @param customZones
	 *            the customZones to set
	 */
	public void setCustomZones(final Boolean customZones) {
		this.customZones = customZones;
	}

	/**
	 * @param zones
	 *            the zones to set
	 */
	public void setZones(final List<StravaZone> zones) {
		this.zones = zones;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaHeartRateZones [customZones=" + this.customZones + ", zones=" + this.zones + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}

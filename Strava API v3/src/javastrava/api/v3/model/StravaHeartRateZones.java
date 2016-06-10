package javastrava.api.v3.model;

import java.util.List;

public class StravaHeartRateZones {
	/**
	 * Is this a set of custom zones?
	 */
	private Boolean customZones;
	/**
	 * The actual zones
	 */
	private List<StravaZone> zones;
	/**
	 * no-args constructor
	 */
	public StravaHeartRateZones() {
		super();
		
	}
	/**
	 * @return the customZones
	 */
	public Boolean getCustomZones() {
		return customZones;
	}
	/**
	 * @param customZones the customZones to set
	 */
	public void setCustomZones(Boolean customZones) {
		this.customZones = customZones;
	}
	/**
	 * @return the zones
	 */
	public List<StravaZone> getZones() {
		return zones;
	}
	/**
	 * @param zones the zones to set
	 */
	public void setZones(List<StravaZone> zones) {
		this.zones = zones;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaHeartRateZones [customZones=" + customZones + ", zones=" + zones + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customZones == null) ? 0 : customZones.hashCode());
		result = prime * result + ((zones == null) ? 0 : zones.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StravaHeartRateZones other = (StravaHeartRateZones) obj;
		if (customZones == null) {
			if (other.customZones != null)
				return false;
		} else if (!customZones.equals(other.customZones))
			return false;
		if (zones == null) {
			if (other.zones != null)
				return false;
		} else if (!zones.equals(other.zones))
			return false;
		return true;
	}
}

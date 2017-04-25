package javastrava.model;

import java.time.ZonedDateTime;
import java.util.List;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaMeasurementMethod;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaRunningRaceType;

/**
 * <p>
 * A featured set of running races taking place across the world. Each race has an associated race page with course information, athlete goals, and results.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaRunningRace implements StravaCacheableEntity<Integer> {
	/**
	 * Identifier
	 */
	private Integer id;

	/**
	 * <p>
	 * Indicates level of detail
	 * </p>
	 * <p>
	 * The {@link StravaResourceState#SUMMARY summary representation} of the running race is the same as the {@link StravaResourceState#DETAILED detailed representation}, except it does NOT include
	 * these attributes:
	 * <ul>
	 * <li><code>{@link #routeIds}</code></li>
	 * <li><code>{@link #websiteUrl}</code></li>
	 * </ul>
	 * </p>
	 *
	 */
	private StravaResourceState resourceState;

	/**
	 * Name of the race
	 */
	private String name;

	/**
	 * Road, trail, track or cross-country
	 */
	private StravaRunningRaceType runningRaceType;

	/**
	 * <p>
	 * Race distance in metres
	 * </p>
	 * <p>
	 * Common race distances have fixed distance values. These include:
	 * <ul>
	 * <li>1 Mile: 1609.34</li>
	 * <li>5K: 5000</li>
	 * <li>10K: 10000</li>
	 * <li>Half Marathon: 21097</li>
	 * <li>Marathon: 42195.0</li>
	 * <li>50K: 50000</li>
	 * <li>100K: 100000</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Other races have custom distances. For example, a 50 Mile will be 80467.0 calculated as 1609.34 meters/mile.
	 * </p>
	 *
	 */
	private Float distance;

	/**
	 * Local date-time for race start
	 */
	private ZonedDateTime startDateLocal;

	/**
	 * City where the race is held
	 */
	private String city;

	/**
	 * State where the race is held
	 */
	private String state;

	/**
	 * Country where the race is held
	 */
	private String country;

	/**
	 * Can be used to retrieve route information
	 */
	private List<Integer> routeIds;

	/**
	 * Meters or feet
	 */
	private StravaMeasurementMethod measurementPreference;

	/**
	 * canonical Strava url i.e. www.strava.com/running-races/:url
	 */
	private String url;

	/**
	 * Official website url e.g. “http://www.baa.org/races/boston-marathon”
	 */
	private String websiteUrl;

	/**
	 * UNDOCUMENTED - see #
	 */
	private Integer status;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaRunningRace)) {
			return false;
		}
		final StravaRunningRace other = (StravaRunningRace) obj;
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!this.country.equals(other.country)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.measurementPreference != other.measurementPreference) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.routeIds == null) {
			if (other.routeIds != null) {
				return false;
			}
		} else if (!this.routeIds.equals(other.routeIds)) {
			return false;
		}
		if (this.runningRaceType != other.runningRaceType) {
			return false;
		}
		if (this.startDateLocal == null) {
			if (other.startDateLocal != null) {
				return false;
			}
		} else if (!this.startDateLocal.equals(other.startDateLocal)) {
			return false;
		}
		if (this.state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!this.state.equals(other.state)) {
			return false;
		}
		if (this.status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!this.status.equals(other.status)) {
			return false;
		}
		if (this.url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!this.url.equals(other.url)) {
			return false;
		}
		if (this.websiteUrl == null) {
			if (other.websiteUrl != null) {
				return false;
			}
		} else if (!this.websiteUrl.equals(other.websiteUrl)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the measurementPreference
	 */
	public StravaMeasurementMethod getMeasurementPreference() {
		return this.measurementPreference;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the routeIds
	 */
	public List<Integer> getRouteIds() {
		return this.routeIds;
	}

	/**
	 * @return the runningRaceType
	 */
	public StravaRunningRaceType getRunningRaceType() {
		return this.runningRaceType;
	}

	/**
	 * @return the startDateLocal
	 */
	public ZonedDateTime getStartDateLocal() {
		return this.startDateLocal;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * @return the websiteUrl
	 */
	public String getWebsiteUrl() {
		return this.websiteUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.measurementPreference == null) ? 0 : this.measurementPreference.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.routeIds == null) ? 0 : this.routeIds.hashCode());
		result = (prime * result) + ((this.runningRaceType == null) ? 0 : this.runningRaceType.hashCode());
		result = (prime * result) + ((this.startDateLocal == null) ? 0 : this.startDateLocal.hashCode());
		result = (prime * result) + ((this.state == null) ? 0 : this.state.hashCode());
		result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
		result = (prime * result) + ((this.url == null) ? 0 : this.url.hashCode());
		result = (prime * result) + ((this.websiteUrl == null) ? 0 : this.websiteUrl.hashCode());
		return result;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param measurementPreference
	 *            the measurementPreference to set
	 */
	public void setMeasurementPreference(StravaMeasurementMethod measurementPreference) {
		this.measurementPreference = measurementPreference;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param routeIds
	 *            the routeIds to set
	 */
	public void setRouteIds(List<Integer> routeIds) {
		this.routeIds = routeIds;
	}

	/**
	 * @param runningRaceType
	 *            the runningRaceType to set
	 */
	public void setRunningRaceType(StravaRunningRaceType runningRaceType) {
		this.runningRaceType = runningRaceType;
	}

	/**
	 * @param startDateLocal
	 *            the startDateLocal to set
	 */
	public void setStartDateLocal(ZonedDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param websiteUrl
	 *            the websiteUrl to set
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	@Override
	public String toString() {
		return "StravaRunningRace [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", runningRaceType=" + this.runningRaceType + ", distance=" + this.distance //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", startDateLocal=" + this.startDateLocal + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", routeIds=" + this.routeIds + ", measurementPreference=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.measurementPreference + ", url=" + this.url + ", websiteUrl=" + this.websiteUrl + ", status=" + this.status + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}

package javastrava.api.v3.model;

import java.time.ZonedDateTime;
import java.util.List;

import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaRunningRaceType;
import javastrava.cache.StravaCacheableEntity;

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
	 * @see java.lang.Object#hashCode()
	 */
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
		result = (prime * result) + ((this.url == null) ? 0 : this.url.hashCode());
		result = (prime * result) + ((this.websiteUrl == null) ? 0 : this.websiteUrl.hashCode());
		return result;
	}

	/**
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
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the runningRaceType
	 */
	public StravaRunningRaceType getRunningRaceType() {
		return this.runningRaceType;
	}

	/**
	 * @param runningRaceType
	 *            the runningRaceType to set
	 */
	public void setRunningRaceType(StravaRunningRaceType runningRaceType) {
		this.runningRaceType = runningRaceType;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the startDateLocal
	 */
	public ZonedDateTime getStartDateLocal() {
		return this.startDateLocal;
	}

	/**
	 * @param startDateLocal
	 *            the startDateLocal to set
	 */
	public void setStartDateLocal(ZonedDateTime startDateLocal) {
		this.startDateLocal = startDateLocal;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the routeIds
	 */
	public List<Integer> getRouteIds() {
		return this.routeIds;
	}

	/**
	 * @param routeIds
	 *            the routeIds to set
	 */
	public void setRouteIds(List<Integer> routeIds) {
		this.routeIds = routeIds;
	}

	/**
	 * @return the measurementPreference
	 */
	public StravaMeasurementMethod getMeasurementPreference() {
		return this.measurementPreference;
	}

	/**
	 * @param measurementPreference
	 *            the measurementPreference to set
	 */
	public void setMeasurementPreference(StravaMeasurementMethod measurementPreference) {
		this.measurementPreference = measurementPreference;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the websiteUrl
	 */
	public String getWebsiteUrl() {
		return this.websiteUrl;
	}

	/**
	 * @param websiteUrl
	 *            the websiteUrl to set
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
}

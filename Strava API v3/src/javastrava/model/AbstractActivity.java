package javastrava.model;

import java.util.Calendar;

/**
 * @author Dan Shannon
 *
 */
public abstract class AbstractActivity {
	private String id;
	private ActivityMap map;
	private Calendar startTime;
	private Float distance;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}
	/**
	 * @return the map
	 */
	public ActivityMap getMap() {
		return this.map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(final ActivityMap map) {
		this.map = map;
	}
	/**
	 * @return the startTime
	 */
	public Calendar getStartTime() {
		return this.startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(final Calendar startTime) {
		this.startTime = startTime;
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
}

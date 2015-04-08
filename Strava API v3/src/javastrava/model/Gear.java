package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class Gear {
	private String id;
	private Athlete athlete;
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
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return this.athlete;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(final Athlete athlete) {
		this.athlete = athlete;
	}
}

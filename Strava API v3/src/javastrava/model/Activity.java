package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class Activity extends AbstractActivity {
	private Athlete athlete;
	private Gear gear;
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
	/**
	 * @return the gear
	 */
	public Gear getGear() {
		return this.gear;
	}
	/**
	 * @param gear the gear to set
	 */
	public void setGear(final Gear gear) {
		this.gear = gear;
	}
}

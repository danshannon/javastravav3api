package javastrava.api.v3.model;

/**
 * Athlete zones to support the athlete zones endpoint
 *
 * @author Dan Shannon
 *
 */
public class StravaAthleteZones {
	/**
	 * Set of heart-rate zones for the athlet
	 */
	private StravaHeartRateZones heartRate;
	/**
	 * No-args constructor
	 */
	public StravaAthleteZones() {
		super();
	}
	/**
	 * @return the heartRate
	 */
	public StravaHeartRateZones getHeartRate() {
		return this.heartRate;
	}
	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(final StravaHeartRateZones heartRate) {
		this.heartRate = heartRate;
	}
}

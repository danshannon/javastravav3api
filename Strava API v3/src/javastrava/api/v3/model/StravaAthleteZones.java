package javastrava.api.v3.model;

public class StravaAthleteZones {
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
		return heartRate;
	}
	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(final StravaHeartRateZones heartRate) {
		this.heartRate = heartRate;
	}
}

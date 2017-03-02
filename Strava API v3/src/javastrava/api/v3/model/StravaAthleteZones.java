package javastrava.api.v3.model;

/**
 * Athlete zones to support the athlete zones endpoint
 *
 * @author Dan Shannon
 *
 */
public class StravaAthleteZones implements StravaEntity {
	/**
	 * Set of heart-rate zones for the athlete
	 */
	private StravaAthleteZone heartRate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.heartRate == null) ? 0 : this.heartRate.hashCode());
		result = (prime * result) + ((this.power == null) ? 0 : this.power.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaAthleteZones)) {
			return false;
		}
		final StravaAthleteZones other = (StravaAthleteZones) obj;
		if (this.heartRate == null) {
			if (other.heartRate != null) {
				return false;
			}
		} else if (!this.heartRate.equals(other.heartRate)) {
			return false;
		}
		if (this.power == null) {
			if (other.power != null) {
				return false;
			}
		} else if (!this.power.equals(other.power)) {
			return false;
		}
		return true;
	}

	/**
	 * Set of power zones for athletes who have set FTP values
	 */
	private StravaAthleteZone power;

	/**
	 * No-args constructor
	 */
	public StravaAthleteZones() {
		super();
	}

	/**
	 * @return the heartRate
	 */
	public StravaAthleteZone getHeartRate() {
		return this.heartRate;
	}

	/**
	 * @param heartRate
	 *            the heartRate to set
	 */
	public void setHeartRate(final StravaAthleteZone heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @return the power zones
	 */
	public StravaAthleteZone getPower() {
		return this.power;
	}

	/**
	 * @param power
	 *            The power zone to set
	 */
	public void setPower(StravaAthleteZone power) {
		this.power = power;
	}
}

package javastrava.api.v3.model.reference;

import javastrava.api.v3.model.StravaClub;
import javastrava.config.Messages;

/**
 * <p>
 * Strava sport type associated with {@link StravaClub clubs}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaSportType {
	CYCLING(Messages.getString("StravaSportType.cycling"), Messages.getString("StravaSportType.cycling.description")), //$NON-NLS-1$ //$NON-NLS-2$
	RUNNING(Messages.getString("StravaSportType.running"), Messages.getString("StravaSportType.running.description")), //$NON-NLS-1$ //$NON-NLS-2$
	TRIATHLON(Messages.getString("StravaSportType.triathlon"), Messages.getString("StravaSportType.triathlon.description")), //$NON-NLS-1$ //$NON-NLS-2$
	OTHER(Messages.getString("StravaSportType.other"), Messages.getString("StravaSportType.other.description")), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaSportType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaSportType create(final String id) {
		StravaSportType[] sportTypes = StravaSportType.values();
		for (StravaSportType sportType : sportTypes) {
			if (sportType.getId().equals(id)) {
				return sportType;
			}
		}
		return StravaSportType.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

}

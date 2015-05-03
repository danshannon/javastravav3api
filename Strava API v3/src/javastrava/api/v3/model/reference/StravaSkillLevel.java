/**
 *
 */
package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * @author Dan Shannon
 *
 */
public enum StravaSkillLevel {
	/**
	 * Casual
	 */
	CASUAL(StravaConfig.integer("StravaSkillLevel.casual"), Messages.string("StravaSkillLevel.casual.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Tempo
	 */
	TEMPO(StravaConfig.integer("StravaSkillLevel.tempo"), Messages.string("StravaSkillLevel.tempo.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Hammerfest!
	 */
	HAMMERFEST(StravaConfig.integer("StravaSkillLevel.hammerfest"), Messages.string("StravaSkillLevel.hammerfest.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The id of the enum to return
	 * @return The enumeration, or {@link #UNKNOWN} if not found
	 */
	public static StravaSkillLevel create(final Integer id) {
		for (final StravaSkillLevel skillLevel : StravaSkillLevel.values()) {
			if (skillLevel.getId().equals(id)) {
				return skillLevel;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private final Integer id;

	/**
	 * Description
	 */
	private final String description;

	/**
	 * @param id Identifier
	 * @param description Description
	 *
	 */
	private StravaSkillLevel(final Integer id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 *
	 * @return The id
	 */
	public Integer getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}

}

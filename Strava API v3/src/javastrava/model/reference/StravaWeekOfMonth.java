package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * Week of month
 *
 * @author Dan Shannon
 *
 */
public enum StravaWeekOfMonth implements StravaReferenceType<Integer> {
	/**
	 * First week
	 */
	FIRST(StravaConfig.integer("StravaWeekOfMonth.first"), Messages.string("StravaWeekOfMonth.first.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Second week
	 */
	SECOND(StravaConfig.integer("StravaWeekOfMonth.second"), Messages.string("StravaWeekOfMonth.second.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Second week
	 */
	THIRD(StravaConfig.integer("StravaWeekOfMonth.third"), Messages.string("StravaWeekOfMonth.third.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Second week
	 */
	FOURTH(StravaConfig.integer("StravaWeekOfMonth.fourth"), Messages.string("StravaWeekOfMonth.fourth.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Last
	 */
	LAST(StravaConfig.integer("StravaWeekOfMonth.last"), Messages.string("StravaWeekOfMonth.last.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON serialisation
	 * 
	 * @param id
	 *            The integer representation of this {@link StravaWeekOfMonth}
	 * @return The corresponding week
	 */
	public static StravaWeekOfMonth create(final Integer id) {
		final StravaWeekOfMonth[] weeks = StravaWeekOfMonth.values();
		for (final StravaWeekOfMonth week : weeks) {
			if (week.getId().equals(id)) {
				return week;
			}
		}
		return StravaWeekOfMonth.UNKNOWN;
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
	 * Private constructor used by declaration
	 * 
	 * @param id
	 *            Identifier
	 * @param description
	 *            Description
	 */
	private StravaWeekOfMonth(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getValue() {
		return this.id;
	}

	@Override
	public String toString() {
		return this.id.toString();
	}
}

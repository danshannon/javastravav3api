package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;

/**
 * @author danshannon
 *
 */
public enum StravaTerrainType implements StravaReferenceType<Integer> {
	/**
	 * Mostly flat
	 */
	MOSTLY_FLAT(StravaConfig.integer("StravaTerrainType.mostly_flat"), Messages.string("StravaTerrainType.mostly_flat.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Rolling hills
	 */
	ROLLING_HILLS(StravaConfig.integer("StravaTerrainType.rolling_hills"), Messages.string("StravaTerrainType.rolling_hills.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Killer climbs
	 */
	KILLER_CLIMBS(StravaConfig.integer("StravaTerrainType.killer_climbs"), Messages.string("StravaTerrainType.killer_climbs.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Unknown
	 */
	UNKNOWN(StravaConfig.integer("Common.unknown.integer"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Create the one from the integer id
	 * @param id Identifier
	 * @return The enumeration instance with the given id, or {@link #UNKNOWN} if there is no match
	 */
	public static StravaTerrainType create(final Integer id) {
		for (final StravaTerrainType terrainType : StravaTerrainType.values()) {
			if (terrainType.getId().equals(id)) {
				return terrainType;
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
	 * Private constructor supports the declarations
	 * @param id Identifier
	 * @param description Description
	 */
	private StravaTerrainType(final Integer id, final String description) {
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

	/**
	 * Used by JSON serialisation
	 * @return The identifier
	 */
	@Override
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

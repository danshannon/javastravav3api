package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.SegmentExplorerActivityTypeSerializer;
import javastrava.model.StravaMapPoint;
import javastrava.service.SegmentService;

/**
 * <p>
 * The Strava {@link SegmentService#segmentExplore(StravaMapPoint, StravaMapPoint, StravaSegmentExplorerActivityType, StravaClimbCategory, StravaClimbCategory) segment explorer} really
 * <strong>should</strong> return the same activity types as held in {@link StravaSegmentActivityType}. But it doesn't. Hey ho!
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaSegmentExplorerActivityType implements StravaReferenceType<String> {
	/**
	 * Running
	 */
	RUNNING(StravaConfig.string("StravaSegmentExplorerActivityType.running"), Messages.string("StravaSegmentExplorerActivityType.running.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Riding
	 */
	RIDING(StravaConfig.string("StravaSegmentExplorerActivityType.riding"), Messages.string("StravaSegmentExplorerActivityType.riding.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * 
	 * @param id
	 *            The string representation of a {@link StravaSegmentExplorerActivityType} as returned by the Strava API
	 * @return The matching {@link StravaSegmentExplorerActivityType}, or {@link StravaSegmentExplorerActivityType#UNKNOWN} if there is no match
	 */
	public static StravaSegmentExplorerActivityType create(final String id) {
		for (final StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Identifier
	 */
	private String id;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Private constructor used by declarations
	 * 
	 * @param id
	 *            Identifier - also used when serialising/deserialising to JSON
	 * @param description
	 *            Description
	 */
	private StravaSegmentExplorerActivityType(final String id, final String description) {
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
	public String getId() {
		return this.id;
	}

	/**
	 * Used by JSON serialisation
	 * 
	 * @return The string representation of this {@link StravaSegmentExplorerActivityType} to be used with the Strava API
	 * @see SegmentExplorerActivityTypeSerializer#serialize(StravaSegmentExplorerActivityType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public String getValue() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}

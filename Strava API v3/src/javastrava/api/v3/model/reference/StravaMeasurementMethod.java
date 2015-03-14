package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.impl.gson.serializer.MeasurementMethodSerializer;

/**
 * <p>
 * Preferred measurement system for an athlete. Those of you living in the 19th century will prefer {@link #IMPERIAL}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaMeasurementMethod {
	/**
	 * Imperial units
	 */
	IMPERIAL(Strava.stringProperty("StravaMeasurementMethod.imperial"), Messages.getString("StravaMeasurementMethod.imperial.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Metric
	 */
	METRIC(Strava.stringProperty("StravaMeasurementMethod.metric"), Messages.getString("StravaMeasurementMethod.metric.description")),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Strava.stringProperty("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaMeasurementMethod(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of the {@link StravaMeasurementMethod} to be used with the Strava API
	 * @see MeasurementMethodSerializer#serialize(StravaMeasurementMethod, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of the {@link StravaMeasurementMethod} returned by the Strava API
	 * @return The matching {@link StravaMeasurementMethod}, or {@link StravaMeasurementMethod#UNKNOWN} if there is no match
	 */
	public static StravaMeasurementMethod create(final String id) {
		StravaMeasurementMethod[] methods = StravaMeasurementMethod.values();
		for (StravaMeasurementMethod method : methods) {
			if (method.getId().equals(id)) {
				return method;
			}
		}
		return StravaMeasurementMethod.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

}

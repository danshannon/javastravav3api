package javastrava.model.reference;

import java.util.ArrayList;
import java.util.List;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.WeightClassSerializer;
import javastrava.model.StravaSegmentLeaderboard;

/**
 * <p>
 * Weight classes used to filter {@link StravaSegmentLeaderboard segment leaderboards}.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public enum StravaWeightClass implements StravaReferenceType<String> {
	/**
	 * Up to 124 pounds
	 */
	POUNDS0_124(StravaConfig.string("StravaWeightClass.0-124lb"), Messages.string("StravaWeightClass.0-124lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 125-149 pounds
	 */
	POUNDS125_149(StravaConfig.string("StravaWeightClass.125-149lb"), Messages.string("StravaWeightClass.125-149lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 150-164 pounds
	 */
	POUNDS150_164(StravaConfig.string("StravaWeightClass.150-164lb"), Messages.string("StravaWeightClass.150-164lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 165-179 pounds
	 */
	POUNDS165_179(StravaConfig.string("StravaWeightClass.165-179lb"), Messages.string("StravaWeightClass.165-179lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 180-199 pounds
	 */
	POUNDS180_199(StravaConfig.string("StravaWeightClass.180-199lb"), Messages.string("StravaWeightClass.180-199lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 200 pounds and over
	 */
	POUNDS_200PLUS(StravaConfig.string("StravaWeightClass.200lbPlus"), Messages.string("StravaWeightClass.200lbPlus.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Up to 54kg
	 */
	KG0_54(StravaConfig.string("StravaWeightClass.0-54kg"), Messages.string("StravaWeightClass.0-54kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 55-64kg
	 */
	KG55_64(StravaConfig.string("StravaWeightClass.55-64kg"), Messages.string("StravaWeightClass.55-64kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 65-74kg
	 */
	KG65_74(StravaConfig.string("StravaWeightClass.65-74kg"), Messages.string("StravaWeightClass.65-74kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 75-84kg
	 */
	KG75_84(StravaConfig.string("StravaWeightClass.75-84kg"), Messages.string("StravaWeightClass.75-84kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 85-92kg
	 */
	KG85_94(StravaConfig.string("StravaWeightClass.85-94kg"), Messages.string("StravaWeightClass.85-94kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 95kg and over
	 */
	KG95PLUS(StravaConfig.string("StravaWeightClass.95kgPlus"), Messages.string("StravaWeightClass.95kgPlus.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description"), StravaMeasurementMethod.UNKNOWN); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaWeightClass} as returned by the Strava API
	 * @return The matching {@link StravaWeightClass}, or {@link StravaWeightClass#UNKNOWN} if there is no match
	 */
	public static StravaWeightClass create(final String id) {
		final StravaWeightClass[] weightClasses = StravaWeightClass.values();
		for (final StravaWeightClass weightClass : weightClasses) {
			if (weightClass.getId().equals(id)) {
				return weightClass;
			}
		}
		return UNKNOWN;
	}
	/**
	 * Returns a list of all {@link StravaWeightClass weight classes} for the given {@link StravaMeasurementMethod}
	 * @param measurementMethod The measurement method for which weight classes are to be returned
	 * @return The matching weight classes
	 */
	public static List<StravaWeightClass> listByMeasurementMethod(final StravaMeasurementMethod measurementMethod) {
		final List<StravaWeightClass> weightClasses = new ArrayList<StravaWeightClass>();
		if (measurementMethod == StravaMeasurementMethod.UNKNOWN) {
			return weightClasses;
		}
		for (final StravaWeightClass weightClass : StravaWeightClass.values()) {
			if (weightClass.getMeasurementMethod() == measurementMethod) {
				weightClasses.add(weightClass);
			}
		}
		return weightClasses;
	}
	/**
	 * Identifier
	 */
	private String					id;

	/**
	 * Description
	 */
	private String					description;

	/**
	 * Metric or imperial
	 */
	private StravaMeasurementMethod	measurementMethod;

	/**
	 * Private constructor used by declarations
	 * @param id Identifier - also used when serialising/deserialising to JSON
	 * @param description Description
	 * @param measurementMethod Metric or imperial
	 */
	private StravaWeightClass(final String id, final String description, final StravaMeasurementMethod measurementMethod) {
		this.id = id;
		this.description = description;
		this.measurementMethod = measurementMethod;
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
	 * @return the measurementMethod
	 */
	public StravaMeasurementMethod getMeasurementMethod() {
		return this.measurementMethod;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaWeightClass} to be used with the Strava API
	 * @see WeightClassSerializer#serialize(StravaWeightClass, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
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

package javastrava.api.v3.model.reference;

import java.util.ArrayList;
import java.util.List;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.impl.gson.serializer.WeightClassSerializer;

/**
 * <p>
 * Weight classes used to filter {@link StravaSegmentLeaderboard segment leaderboards}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaWeightClass {
	/**
	 * Up to 124 pounds
	 */
	POUNDS0_124(Strava.stringProperty("StravaWeightClass.0-124lb"), Messages.getString("StravaWeightClass.0-124lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 125-149 pounds
	 */
	POUNDS125_149(Strava.stringProperty("StravaWeightClass.125-149lb"), Messages.getString("StravaWeightClass.125-149lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 150-164 pounds
	 */
	POUNDS150_164(Strava.stringProperty("StravaWeightClass.150-164lb"), Messages.getString("StravaWeightClass.150-164lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 165-179 pounds
	 */
	POUNDS165_179(Strava.stringProperty("StravaWeightClass.165-179lb"), Messages.getString("StravaWeightClass.165-179lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 180-199 pounds
	 */
	POUNDS180_199(Strava.stringProperty("StravaWeightClass.180-199lb"), Messages.getString("StravaWeightClass.180-199lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 200 pounds and over
	 */
	POUNDS_200PLUS(Strava.stringProperty("StravaWeightClass.200lbPlus"), Messages.getString("StravaWeightClass.200lbPlus.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Up to 54kg
	 */
	KG0_54(Strava.stringProperty("StravaWeightClass.0-54kg"), Messages.getString("StravaWeightClass.0-54kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 55-64kg
	 */
	KG55_64(Strava.stringProperty("StravaWeightClass.55-64kg"), Messages.getString("StravaWeightClass.55-64kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 65-74kg
	 */
	KG65_74(Strava.stringProperty("StravaWeightClass.65-74kg"), Messages.getString("StravaWeightClass.65-74kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 75-84kg
	 */
	KG75_84(Strava.stringProperty("StravaWeightClass.75-84kg"), Messages.getString("StravaWeightClass.75-84kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 85-92kg
	 */
	KG85_94(Strava.stringProperty("StravaWeightClass.85-94kg"), Messages.getString("StravaWeightClass.85-94kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * 95kg and over
	 */
	KG95PLUS(Strava.stringProperty("StravaWeightClass.95kgPlus"), Messages.getString("StravaWeightClass.95kgPlus.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Strava.stringProperty("Common.unknown"), Messages.getString("Common.unknown.description"), StravaMeasurementMethod.UNKNOWN); //$NON-NLS-1$ //$NON-NLS-2$

	private String					id;
	private String					description;
	private StravaMeasurementMethod	measurementMethod;

	private StravaWeightClass(final String id, final String description, final StravaMeasurementMethod measurementMethod) {
		this.id = id;
		this.description = description;
		this.measurementMethod = measurementMethod;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaWeightClass} to be used with the Strava API
	 * @see WeightClassSerializer#serialize(StravaWeightClass, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaWeightClass} as returned by the Strava API
	 * @return The matching {@link StravaWeightClass}, or {@link StravaWeightClass#UNKNOWN} if there is no match
	 */
	public static StravaWeightClass create(final String id) {
		StravaWeightClass[] weightClasses = StravaWeightClass.values();
		for (StravaWeightClass weightClass : weightClasses) {
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
		List<StravaWeightClass> weightClasses = new ArrayList<StravaWeightClass>();
		if (measurementMethod == StravaMeasurementMethod.UNKNOWN) {
			return weightClasses;
		}
		for (StravaWeightClass weightClass : StravaWeightClass.values()) {
			if (weightClass.getMeasurementMethod() == measurementMethod) {
				weightClasses.add(weightClass);
			}
		}
		return weightClasses;
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
	 * @return the measurementMethod
	 */
	public StravaMeasurementMethod getMeasurementMethod() {
		return this.measurementMethod;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}

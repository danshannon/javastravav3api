package javastrava.api.v3.model.reference;

import java.util.ArrayList;
import java.util.List;

import javastrava.api.v3.model.StravaSegmentLeaderboard;
import javastrava.config.Messages;

/**
 * <p>
 * Weight classes used to filter {@link StravaSegmentLeaderboard segment leaderboards}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaWeightClass {
	POUNDS0_124(Messages.getString("StravaWeightClass.0-124lb"), Messages.getString("StravaWeightClass.0-124lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	POUNDS125_149(Messages.getString("StravaWeightClass.125-149lb"), Messages.getString("StravaWeightClass.125-149lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	POUNDS150_164(Messages.getString("StravaWeightClass.150-164lb"), Messages.getString("StravaWeightClass.150-164lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	POUNDS165_179(Messages.getString("StravaWeightClass.165-179lb"), Messages.getString("StravaWeightClass.165-179lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	POUNDS180_199(Messages.getString("StravaWeightClass.180-199lb"), Messages.getString("StravaWeightClass.180-199lb.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	POUNDS_200PLUS(Messages.getString("StravaWeightClass.200lbPlus"), Messages.getString("StravaWeightClass.200lbPlus.description"), StravaMeasurementMethod.IMPERIAL), //$NON-NLS-1$ //$NON-NLS-2$
	KG0_54(Messages.getString("StravaWeightClass.0-54kg"), Messages.getString("StravaWeightClass.0-54kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	KG55_64(Messages.getString("StravaWeightClass.55-64kg"), Messages.getString("StravaWeightClass.55-64kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	KG65_74(Messages.getString("StravaWeightClass.65-74kg"), Messages.getString("StravaWeightClass.65-74kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	KG75_84(Messages.getString("StravaWeightClass.75-84kg"), Messages.getString("StravaWeightClass.75-84kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	KG85_94(Messages.getString("StravaWeightClass.85-94kg"), Messages.getString("StravaWeightClass.85-94kg.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	KG95PLUS(Messages.getString("StravaWeightClass.95kgPlus"), Messages.getString("StravaWeightClass.95kgPlus.description"), StravaMeasurementMethod.METRIC), //$NON-NLS-1$ //$NON-NLS-2$
	UNKNOWN(Messages.getString("Common.unknown"), Messages.getString("Common.unknown.description"), StravaMeasurementMethod.UNKNOWN); //$NON-NLS-1$ //$NON-NLS-2$

	private String					id;
	private String					description;
	private StravaMeasurementMethod	measurementMethod;

	private StravaWeightClass(final String id, final String description, final StravaMeasurementMethod measurementMethod) {
		this.id = id;
		this.description = description;
		this.measurementMethod = measurementMethod;
	}

	// @JsonValue
	public String getValue() {
		return this.id;
	}

	// @JsonCreator
	public static StravaWeightClass create(final String id) {
		StravaWeightClass[] weightClasses = StravaWeightClass.values();
		for (StravaWeightClass weightClass : weightClasses) {
			if (weightClass.getId().equals(id)) {
				return weightClass;
			}
		}
		return UNKNOWN;
	}

	public static List<StravaWeightClass> listByMeasurementMethod(final StravaMeasurementMethod measurementMethod) {
		List<StravaWeightClass> weightClasses = new ArrayList<StravaWeightClass>();
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

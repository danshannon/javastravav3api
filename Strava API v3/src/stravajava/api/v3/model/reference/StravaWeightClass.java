package stravajava.api.v3.model.reference;

import java.util.ArrayList;
import java.util.List;

import stravajava.api.v3.model.StravaSegmentLeaderboard;

/**
 * <p>
 * Weight classes used to filter {@link StravaSegmentLeaderboard segment leaderboards}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaWeightClass {
	POUNDS0_124("0_124", "Under 125lb (under 8st 13lb)", StravaMeasurementMethod.IMPERIAL),
	POUNDS125_149("125_149", "125-149lb (8st 13lb to 10st 9lb)", StravaMeasurementMethod.IMPERIAL),
	POUNDS150_164("150_164", "150-164lb (10st 10lb to 11st 10lb)", StravaMeasurementMethod.IMPERIAL),
	POUNDS165_179("165_179", "165-179lb (11st 11lb to 12st 11lb)", StravaMeasurementMethod.IMPERIAL),
	POUNDS180_199("180_199", "180-199lb (12st 12lb to 14st 3lb)", StravaMeasurementMethod.IMPERIAL),
	POUNDS_200PLUS("200_plus", "200lb and over (14st 4lb and over)", StravaMeasurementMethod.IMPERIAL),
	KG0_54("0_54", "Under 55kg", StravaMeasurementMethod.METRIC),
	KG55_64("55_64", "55-64kg", StravaMeasurementMethod.METRIC),
	KG65_74("65_74", "65-74kg", StravaMeasurementMethod.METRIC),
	KG75_84("75_84", "75-84kg", StravaMeasurementMethod.METRIC),
	KG85_94("85_94", "85-94kg", StravaMeasurementMethod.METRIC),
	KG95PLUS("95_plus", "95kg and over", StravaMeasurementMethod.METRIC),
	UNKNOWN("UNKNOWN", "Unkown", StravaMeasurementMethod.UNKNOWN);

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

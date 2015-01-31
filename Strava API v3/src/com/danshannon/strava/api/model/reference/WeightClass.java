package com.danshannon.strava.api.model.reference;

import java.util.ArrayList;
import java.util.List;

import com.danshannon.strava.api.model.SegmentLeaderboard;

/**
 * Weight classes used to generate {@link SegmentLeaderboard segment leaderboards}.
 * 
 * @author Dan Shannon
 *
 */
public enum WeightClass {
	POUNDS0_124("0_124","Under 125lb (under 8st 13lb)",MeasurementMethod.IMPERIAL),
	POUNDS125_149("125_149","125-149lb (8st 13lb to 10st 9lb)",MeasurementMethod.IMPERIAL),
	POUNDS150_164("150_164","150-164lb (10st 10lb to 11st 10lb)",MeasurementMethod.IMPERIAL),
	POUNDS165_179("165_179","165-179lb (11st 11lb to 12st 11lb)",MeasurementMethod.IMPERIAL),
	POUNDS180_199("180_199","180-199lb (12st 12lb to 14st 3lb)",MeasurementMethod.IMPERIAL),
	POUNDS_200PLUS("200_plus","200lb and over (14st 4lb and over)",MeasurementMethod.IMPERIAL),
	KG0_54("0_54","Under 55kg",MeasurementMethod.METRIC),
	KG55_64("55_64","55-64kg",MeasurementMethod.METRIC),
	KG65_74("65_74","65-74kg",MeasurementMethod.METRIC),
	KG75_84("75_84","75-84kg",MeasurementMethod.METRIC),
	KG85_94("85_94","85-94kg",MeasurementMethod.METRIC),
	KG95PLUS("95_plus","95kg and over",MeasurementMethod.METRIC),
	UNKNOWN("UNKNOWN","Unkown",MeasurementMethod.UNKNOWN);
	
	private String id;
	private String description;
	private MeasurementMethod measurementMethod;
	
	private WeightClass(String id, String description, MeasurementMethod measurementMethod) {
		this.id = id;
		this.description = description;
		this.measurementMethod = measurementMethod;
	}
	
	//@JsonValue
	public String getValue() {
		return this.id;
	}
	
	//@JsonCreator
	public static WeightClass create(String id) {
		WeightClass[] weightClasses =  WeightClass.values();
		for (WeightClass weightClass : weightClasses) {
			if(weightClass.getId().equals(id)) {
				return weightClass;
			}
		}
		return UNKNOWN;
	}
	
	public static List<WeightClass> listByMeasurementMethod(MeasurementMethod measurementMethod) {
		List<WeightClass> weightClasses = new ArrayList<WeightClass>();
		for (WeightClass weightClass : WeightClass.values()) {
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
	public MeasurementMethod getMeasurementMethod() {
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

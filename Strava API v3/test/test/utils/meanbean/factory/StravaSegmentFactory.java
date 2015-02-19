package test.utils.meanbean.factory;

import javastrava.api.v3.model.StravaSegment;
import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentActivityType;

import org.meanbean.lang.Factory;

public class StravaSegmentFactory implements Factory<StravaSegment> {

	@Override
	public StravaSegment create() {
		StravaSegment segment = new StravaSegment();
		segment.setActivityType(StravaSegmentActivityType.RIDE);
		segment.setAthleteCount(1);
		segment.setAthletePrEffort(null);
		segment.setAverageGrade(1F);
		segment.setCity("Melbourne");
		segment.setClimbCategory(StravaClimbCategory.CATEGORY4);
		segment.setCountry("Australia");
		segment.setCreatedAt(null);
		segment.setDistance(1000F);
		segment.setEffortCount(1);
		segment.setElevationHigh(1000F);
		segment.setElevationLow(1F);
		segment.setEndLatlng(null);
		segment.setHazardous(Boolean.FALSE);
		segment.setId(1);
		segment.setMap(null);
		segment.setMaximumGrade(10f);
		segment.setName("A name by any other rose");
		segment.setPrivateSegment(Boolean.FALSE);
		segment.setResourceState(StravaResourceState.DETAILED);
		segment.setStarCount(0);
		segment.setStarred(Boolean.FALSE);
		segment.setStartLatlng(null);
		segment.setState("VIC");
		segment.setTotalElevationGain(1001F);
		segment.setUpdatedAt(null);
		return segment;
	}

}

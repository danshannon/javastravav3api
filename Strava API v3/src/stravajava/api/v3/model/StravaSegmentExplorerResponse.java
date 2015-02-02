package stravajava.api.v3.model;

import java.util.List;

import stravajava.api.v3.service.SegmentServices;
import lombok.Data;

/**
 * Returned by the {@link SegmentServices#segmentExplore(StravaMapPoint, StravaMapPoint, stravajava.api.v3.service.athlete.SegmentExplorerActivityType, Integer, Integer) segment explorer service}.
 * 
 * Essentially is an array of {@link StravaSegment segments} but sadly is slightly not quite the same
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaSegmentExplorerResponse {
	public StravaSegmentExplorerResponse() {
		// Required
		super();
	}
	
	private List<StravaSegmentExplorerResponseSegment> segments;
}

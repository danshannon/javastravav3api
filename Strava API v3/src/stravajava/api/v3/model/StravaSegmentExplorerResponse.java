package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.service.SegmentServices;

/**
 * <p>
 * Returned by the
 * {@link SegmentServices#segmentExplore(StravaMapPoint, StravaMapPoint, stravajava.api.v3.service.athlete.SegmentExplorerActivityType, Integer, Integer)
 * segment explorer service}.
 * </p>
 * 
 * <p>
 * Essentially is an array of {@link StravaSegment segments} but sadly is slightly not quite the same
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSegmentExplorerResponse {
	/**
	 * List of segments returned by the explorer
	 */
	private List<StravaSegmentExplorerResponseSegment> segments;
}

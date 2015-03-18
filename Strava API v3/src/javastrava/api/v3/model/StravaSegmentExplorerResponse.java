package javastrava.api.v3.model;

import java.util.List;

import javastrava.api.v3.service.SegmentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Returned by the
 * {@link SegmentService#segmentExplore(StravaMapPoint, StravaMapPoint, javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType, javastrava.api.v3.model.reference.StravaClimbCategory, javastrava.api.v3.model.reference.StravaClimbCategory)
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

package javastrava.api.v3.model;

import java.util.List;

import javastrava.api.v3.service.SegmentService;

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
public class StravaSegmentExplorerResponse {
	/**
	 * List of segments returned by the explorer
	 */
	private List<StravaSegmentExplorerResponseSegment> segments;

	/**
	 * @return the segments
	 */
	public List<StravaSegmentExplorerResponseSegment> getSegments() {
		return this.segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(final List<StravaSegmentExplorerResponseSegment> segments) {
		this.segments = segments;
	}
}

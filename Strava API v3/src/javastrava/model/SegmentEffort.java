package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class SegmentEffort extends AbstractSubActivity {
	private Segment segment;

	/**
	 * @return the segment
	 */
	public Segment getSegment() {
		return this.segment;
	}

	/**
	 * @param segment the segment to set
	 */
	public void setSegment(final Segment segment) {
		this.segment = segment;
	}
}

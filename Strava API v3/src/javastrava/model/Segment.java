package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class Segment {
	private String id;
	private SegmentMap map;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}
	/**
	 * @return the map
	 */
	public SegmentMap getMap() {
		return this.map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(final SegmentMap map) {
		this.map = map;
	}
}

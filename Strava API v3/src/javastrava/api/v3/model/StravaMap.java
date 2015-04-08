package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Strava's representation of a map. Contains only a pair of polylines suitable for use with Google maps.
 * </p>
 *
 * <p>
 * If you want a detailed map that consists of GPS co-ordinates as in the original upload, then you're after {@link StravaStream}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaMap {
	/**
	 * Strava's unique identifier for the map
	 */
	private String id;

	/**
	 * Detailed polyline (give it to Google maps for rendering)
	 */
	private String polyline;

	/**
	 * Summary polyline (give it to Google maps for rendering)
	 */
	private String summaryPolyline;

	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;

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
	 * @return the polyline
	 */
	public String getPolyline() {
		return this.polyline;
	}

	/**
	 * @param polyline the polyline to set
	 */
	public void setPolyline(final String polyline) {
		this.polyline = polyline;
	}

	/**
	 * @return the summaryPolyline
	 */
	public String getSummaryPolyline() {
		return this.summaryPolyline;
	}

	/**
	 * @param summaryPolyline the summaryPolyline to set
	 */
	public void setSummaryPolyline(final String summaryPolyline) {
		this.summaryPolyline = summaryPolyline;
	}

	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
}

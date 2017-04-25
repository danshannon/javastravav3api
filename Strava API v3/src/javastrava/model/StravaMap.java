package javastrava.model;

import javastrava.model.reference.StravaResourceState;

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
public class StravaMap implements StravaEntity {
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
	 * No args constructor
	 */
	public StravaMap() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaMap)) {
			return false;
		}
		final StravaMap other = (StravaMap) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.polyline == null) {
			if (other.polyline != null) {
				return false;
			}
		} else if (!this.polyline.equals(other.polyline)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.summaryPolyline == null) {
			if (other.summaryPolyline != null) {
				return false;
			}
		} else if (!this.summaryPolyline.equals(other.summaryPolyline)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the polyline
	 */
	public String getPolyline() {
		return this.polyline;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the summaryPolyline
	 */
	public String getSummaryPolyline() {
		return this.summaryPolyline;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.polyline == null) ? 0 : this.polyline.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.summaryPolyline == null) ? 0 : this.summaryPolyline.hashCode());
		return result;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @param polyline
	 *            the polyline to set
	 */
	public void setPolyline(final String polyline) {
		this.polyline = polyline;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param summaryPolyline
	 *            the summaryPolyline to set
	 */
	public void setSummaryPolyline(final String summaryPolyline) {
		this.summaryPolyline = summaryPolyline;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaMap [id=" + this.id + ", polyline=" + this.polyline + ", summaryPolyline=" + this.summaryPolyline + ", resourceState=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.resourceState + "]"; //$NON-NLS-1$
	}
}

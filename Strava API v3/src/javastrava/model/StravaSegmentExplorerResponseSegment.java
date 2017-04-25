package javastrava.model;

import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Summary of segment returned by the segment explorer
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaSegmentExplorerResponseSegment implements StravaEntity {
	/**
	 * Strava's unique identifier of the {@link StravaSegment segment}
	 */
	private Integer id;

	/**
	 * Segment name
	 */
	private String name;

	/**
	 * Climb category
	 */
	private StravaClimbCategory climbCategory;

	/**
	 * Description of the climb category (see {@link StravaClimbCategory#getDescription()})
	 */
	private String climbCategoryDesc;

	/**
	 * Average grade in percent
	 */
	private Float avgGrade;

	/**
	 * Start co-ordinates of the segment
	 */
	private StravaMapPoint startLatlng;

	/**
	 * End co-ordinates of the segment
	 */
	private StravaMapPoint endLatlng;

	/**
	 * Total elevation difference in metres
	 */
	private Float elevDifference;

	/**
	 * Total distance in metres
	 */
	private Float distance;

	/**
	 * Polyline for rendering with Google maps
	 */
	private String points;

	/**
	 * Resource state - not returned by Strava API but is set at the service layer instead
	 */
	private StravaResourceState resourceState;

	/**
	 * No args constructor
	 */
	public StravaSegmentExplorerResponseSegment() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaSegmentExplorerResponseSegment)) {
			return false;
		}
		final StravaSegmentExplorerResponseSegment other = (StravaSegmentExplorerResponseSegment) obj;
		if (this.avgGrade == null) {
			if (other.avgGrade != null) {
				return false;
			}
		} else if (!this.avgGrade.equals(other.avgGrade)) {
			return false;
		}
		if (this.climbCategory != other.climbCategory) {
			return false;
		}
		if (this.climbCategoryDesc == null) {
			if (other.climbCategoryDesc != null) {
				return false;
			}
		} else if (!this.climbCategoryDesc.equals(other.climbCategoryDesc)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.elevDifference == null) {
			if (other.elevDifference != null) {
				return false;
			}
		} else if (!this.elevDifference.equals(other.elevDifference)) {
			return false;
		}
		if (this.endLatlng == null) {
			if (other.endLatlng != null) {
				return false;
			}
		} else if (!this.endLatlng.equals(other.endLatlng)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!this.points.equals(other.points)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.startLatlng == null) {
			if (other.startLatlng != null) {
				return false;
			}
		} else if (!this.startLatlng.equals(other.startLatlng)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the avgGrade
	 */
	public Float getAvgGrade() {
		return this.avgGrade;
	}

	/**
	 * @return the climbCategory
	 */
	public StravaClimbCategory getClimbCategory() {
		return this.climbCategory;
	}

	/**
	 * @return the climbCategoryDesc
	 */
	public String getClimbCategoryDesc() {
		return this.climbCategoryDesc;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the elevDifference
	 */
	public Float getElevDifference() {
		return this.elevDifference;
	}

	/**
	 * @return the endLatlng
	 */
	public StravaMapPoint getEndLatlng() {
		return this.endLatlng;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the points
	 */
	public String getPoints() {
		return this.points;
	}

	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the startLatlng
	 */
	public StravaMapPoint getStartLatlng() {
		return this.startLatlng;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.avgGrade == null) ? 0 : this.avgGrade.hashCode());
		result = (prime * result) + ((this.climbCategory == null) ? 0 : this.climbCategory.hashCode());
		result = (prime * result) + ((this.climbCategoryDesc == null) ? 0 : this.climbCategoryDesc.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elevDifference == null) ? 0 : this.elevDifference.hashCode());
		result = (prime * result) + ((this.endLatlng == null) ? 0 : this.endLatlng.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.points == null) ? 0 : this.points.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.startLatlng == null) ? 0 : this.startLatlng.hashCode());
		return result;
	}

	/**
	 * @param avgGrade
	 *            the avgGrade to set
	 */
	public void setAvgGrade(final Float avgGrade) {
		this.avgGrade = avgGrade;
	}

	/**
	 * @param climbCategory
	 *            the climbCategory to set
	 */
	public void setClimbCategory(final StravaClimbCategory climbCategory) {
		this.climbCategory = climbCategory;
	}

	/**
	 * @param climbCategoryDesc
	 *            the climbCategoryDesc to set
	 */
	public void setClimbCategoryDesc(final String climbCategoryDesc) {
		this.climbCategoryDesc = climbCategoryDesc;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final Float distance) {
		this.distance = distance;
	}

	/**
	 * @param elevDifference
	 *            the elevDifference to set
	 */
	public void setElevDifference(final Float elevDifference) {
		this.elevDifference = elevDifference;
	}

	/**
	 * @param endLatlng
	 *            the endLatlng to set
	 */
	public void setEndLatlng(final StravaMapPoint endLatlng) {
		this.endLatlng = endLatlng;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(final String points) {
		this.points = points;
	}

	/**
	 * @param resourceState
	 *            The resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;

	}

	/**
	 * @param startLatlng
	 *            the startLatlng to set
	 */
	public void setStartLatlng(final StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
	}

	@Override
	public String toString() {
		return "StravaSegmentExplorerResponseSegment [id=" + this.id + ", name=" + this.name + ", climbCategory=" + this.climbCategory + ", climbCategoryDesc=" + this.climbCategoryDesc + ", avgGrade=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.avgGrade
				+ ", startLatlng=" + this.startLatlng + ", endLatlng=" + this.endLatlng + ", elevDifference=" + this.elevDifference + ", distance=" + this.distance + ", points=" + this.points //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", resourceState=" //$NON-NLS-1$
				+ this.resourceState + "]"; //$NON-NLS-1$
	}
}

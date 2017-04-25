package javastrava.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaRouteSubType;
import javastrava.model.reference.StravaRouteType;

/**
 * <p>
 * Routes are manually-created paths made up of sections called legs. Currently it is only possible to create routes using the Routebuilder web interface.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaRoute implements StravaEntity {

	/**
	 * Identifier
	 */
	private Integer id;

	/**
	 * Resource state
	 */
	private StravaResourceState resourceState;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Owner of the route
	 */
	private StravaAthlete athlete;

	/**
	 * Distance in metres
	 */
	private Float distance;

	/**
	 * Elevation gain in metres
	 */
	private Float elevationGain;

	/**
	 * Maps summary
	 */
	private StravaMap map;

	/**
	 * Type - ride or run
	 */
	private StravaRouteType type;

	/**
	 * Subtype - road, mtb, cross, trail, mixed
	 */
	private StravaRouteSubType subType;

	/**
	 * Route is private
	 */
	@SerializedName("private")
	private Boolean isPrivate;

	/**
	 * <code>true</code> if the authenticated athlete has starred this route
	 */
	private Boolean starred;

	/**
	 * UNIX epoch timestamp (time since 1970 in seconds)
	 */
	private Long timestamp;

	/**
	 * List of all segments that a route traverses
	 */
	private List<StravaSegment> segments;

	/**
	 * UNDOCUMENTED Estimated moving time for the authenticated athlete
	 */
	private Integer estimatedMovingTime;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaRoute)) {
			return false;
		}
		final StravaRoute other = (StravaRoute) obj;
		if (this.athlete == null) {
			if (other.athlete != null) {
				return false;
			}
		} else if (!this.athlete.equals(other.athlete)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.distance == null) {
			if (other.distance != null) {
				return false;
			}
		} else if (!this.distance.equals(other.distance)) {
			return false;
		}
		if (this.elevationGain == null) {
			if (other.elevationGain != null) {
				return false;
			}
		} else if (!this.elevationGain.equals(other.elevationGain)) {
			return false;
		}
		if (this.estimatedMovingTime == null) {
			if (other.estimatedMovingTime != null) {
				return false;
			}
		} else if (!this.estimatedMovingTime.equals(other.estimatedMovingTime)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.isPrivate == null) {
			if (other.isPrivate != null) {
				return false;
			}
		} else if (!this.isPrivate.equals(other.isPrivate)) {
			return false;
		}
		if (this.map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!this.map.equals(other.map)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.segments == null) {
			if (other.segments != null) {
				return false;
			}
		} else if (!this.segments.equals(other.segments)) {
			return false;
		}
		if (this.starred == null) {
			if (other.starred != null) {
				return false;
			}
		} else if (!this.starred.equals(other.starred)) {
			return false;
		}
		if (this.subType != other.subType) {
			return false;
		}
		if (this.timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!this.timestamp.equals(other.timestamp)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @return the elevationGain
	 */
	public Float getElevationGain() {
		return this.elevationGain;
	}

	/**
	 * @return the estimatedMovingTime
	 */
	public Integer getEstimatedMovingTime() {
		return this.estimatedMovingTime;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the isPrivate
	 */
	public Boolean getIsPrivate() {
		return this.isPrivate;
	}

	/**
	 * @return the map
	 */
	public StravaMap getMap() {
		return this.map;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the segments
	 */
	public List<StravaSegment> getSegments() {
		return this.segments;
	}

	/**
	 * @return the starred
	 */
	public Boolean getStarred() {
		return this.starred;
	}

	/**
	 * @return the subType
	 */
	public StravaRouteSubType getSubType() {
		return this.subType;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * @return the type
	 */
	public StravaRouteType getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.athlete == null) ? 0 : this.athlete.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.distance == null) ? 0 : this.distance.hashCode());
		result = (prime * result) + ((this.elevationGain == null) ? 0 : this.elevationGain.hashCode());
		result = (prime * result) + ((this.estimatedMovingTime == null) ? 0 : this.estimatedMovingTime.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.isPrivate == null) ? 0 : this.isPrivate.hashCode());
		result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.segments == null) ? 0 : this.segments.hashCode());
		result = (prime * result) + ((this.starred == null) ? 0 : this.starred.hashCode());
		result = (prime * result) + ((this.subType == null) ? 0 : this.subType.hashCode());
		result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @param elevationGain
	 *            the elevationGain to set
	 */
	public void setElevationGain(Float elevationGain) {
		this.elevationGain = elevationGain;
	}

	/**
	 * @param estimatedMovingTime
	 *            the estimatedMovingTime to set
	 */
	public void setEstimatedMovingTime(Integer estimatedMovingTime) {
		this.estimatedMovingTime = estimatedMovingTime;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 */
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(StravaMap map) {
		this.map = map;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param segments
	 *            the segments to set
	 */
	public void setSegments(List<StravaSegment> segments) {
		this.segments = segments;
	}

	/**
	 * @param starred
	 *            the starred to set
	 */
	public void setStarred(Boolean starred) {
		this.starred = starred;
	}

	/**
	 * @param subType
	 *            the subType to set
	 */
	public void setSubType(StravaRouteSubType subType) {
		this.subType = subType;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(StravaRouteType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "StravaRoute [id=" + this.id + ", resourceState=" + this.resourceState + ", name=" + this.name + ", description=" + this.description + ", athlete=" + this.athlete + ", distance=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.distance + ", elevationGain=" + this.elevationGain + ", map=" + this.map + ", type=" + this.type + ", subType=" + this.subType + ", isPrivate=" + this.isPrivate + ", starred=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.starred + ", timestamp=" + this.timestamp + ", segments=" + this.segments + ", estimatedMovingTime=" + this.estimatedMovingTime + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}

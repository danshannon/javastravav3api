package javastrava.api.v3.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSkillLevel;
import javastrava.api.v3.model.reference.StravaTerrainType;

/**
 * <p>
 * Group Events are optionally recurring events for club members. Only club members can access private club events.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClubEvent implements StravaEntity<Integer> {
	/**
	 * Unique id of this event
	 */
	private Integer				id;
	/**
	 * Resource state as returned by Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * Title of the event
	 */
	private String				title;
	/**
	 * Text description of the event
	 */
	private String				description;
	/**
	 * Unique id of the club that the event belongs to
	 */
	private Integer				clubId;
	/**
	 * Type of activity for this event
	 */
	private StravaActivityType	activityType;
	/**
	 * Date and time the event was created
	 */
	private ZonedDateTime		createdAt;
	/**
	 * Unique ID of the Strava route associated with the
	 */
	private Integer				routeId;
	/**
	 * Event is for women
	 */
	private Boolean				womanOnly;
	/**
	 * Is this a private event (only club members can access private events)
	 */
	@SerializedName("private")
	private Boolean				privateEvent;
	/**
	 * Skill level (casual, tempo, hammerfest)
	 */
	private StravaSkillLevel	skillLevel;
	/**
	 * Terrain type (flat, rolling, etc)
	 */
	private StravaTerrainType	terrain;
	/**
	 * List of upcoming occurrences
	 */
	private List<ZonedDateTime>	upcomingOccurrences;
	/**
	 * Location of the event
	 */
	private String				address;

	/**
	 * No-args constructor
	 */
	public StravaClubEvent() {
		// Empty
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
		if (!(obj instanceof StravaClubEvent)) {
			return false;
		}
		final StravaClubEvent other = (StravaClubEvent) obj;
		if (this.activityType != other.activityType) {
			return false;
		}
		if (this.address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!this.address.equals(other.address)) {
			return false;
		}
		if (this.clubId == null) {
			if (other.clubId != null) {
				return false;
			}
		} else if (!this.clubId.equals(other.clubId)) {
			return false;
		}
		if (this.createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!this.createdAt.equals(other.createdAt)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.privateEvent == null) {
			if (other.privateEvent != null) {
				return false;
			}
		} else if (!this.privateEvent.equals(other.privateEvent)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.routeId == null) {
			if (other.routeId != null) {
				return false;
			}
		} else if (!this.routeId.equals(other.routeId)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		if (this.upcomingOccurrences == null) {
			if (other.upcomingOccurrences != null) {
				return false;
			}
		} else if (!this.upcomingOccurrences.equals(other.upcomingOccurrences)) {
			return false;
		}
		if (this.womanOnly == null) {
			if (other.womanOnly != null) {
				return false;
			}
		} else if (!this.womanOnly.equals(other.womanOnly)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activityType
	 */
	public StravaActivityType getActivityType() {
		return this.activityType;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @return the clubId
	 */
	public Integer getClubId() {
		return this.clubId;
	}

	/**
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the privateEvent
	 */
	public Boolean getPrivateEvent() {
		return this.privateEvent;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the routeId
	 */
	public Integer getRouteId() {
		return this.routeId;
	}

	/**
	 * @return the skillLevel
	 */
	public StravaSkillLevel getSkillLevel() {
		return this.skillLevel;
	}

	/**
	 * @return the terrain
	 */
	public StravaTerrainType getTerrain() {
		return this.terrain;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the upcomingOccurrences
	 */
	public List<ZonedDateTime> getUpcomingOccurrences() {
		return this.upcomingOccurrences;
	}

	/**
	 * @return the womanOnly
	 */
	public Boolean getWomanOnly() {
		return this.womanOnly;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activityType == null) ? 0 : this.activityType.hashCode());
		result = (prime * result) + ((this.address == null) ? 0 : this.address.hashCode());
		result = (prime * result) + ((this.clubId == null) ? 0 : this.clubId.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.privateEvent == null) ? 0 : this.privateEvent.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.routeId == null) ? 0 : this.routeId.hashCode());
		result = (prime * result) + ((this.title == null) ? 0 : this.title.hashCode());
		result = (prime * result) + ((this.upcomingOccurrences == null) ? 0 : this.upcomingOccurrences.hashCode());
		result = (prime * result) + ((this.womanOnly == null) ? 0 : this.womanOnly.hashCode());
		return result;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(final StravaActivityType activityType) {
		this.activityType = activityType;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * @param clubId
	 *            the clubId to set
	 */
	public void setClubId(final Integer clubId) {
		this.clubId = clubId;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param privateEvent
	 *            the privateEvent to set
	 */
	public void setPrivateEvent(final Boolean privateEvent) {
		this.privateEvent = privateEvent;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(final Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * @param skillLevel
	 *            the skillLevel to set
	 */
	public void setSkillLevel(final StravaSkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	/**
	 * @param terrain
	 *            the terrain to set
	 */
	public void setTerrain(final StravaTerrainType terrain) {
		this.terrain = terrain;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @param upcomingOccurrences
	 *            the upcomingOccurrences to set
	 */
	public void setUpcomingOccurrences(final List<ZonedDateTime> upcomingOccurrences) {
		this.upcomingOccurrences = upcomingOccurrences;
	}

	/**
	 * @param womanOnly
	 *            the womanOnly to set
	 */
	public void setWomanOnly(final Boolean womanOnly) {
		this.womanOnly = womanOnly;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClubEvent [id=" + this.id + ", resourceState=" + this.resourceState + ", title=" + this.title + ", description=" + this.description //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", clubId=" + this.clubId + ", activityType=" + this.activityType + ", createdAt=" + this.createdAt + ", routeId=" + this.routeId //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", womanOnly=" + this.womanOnly + ", privateEvent=" + this.privateEvent + ", upcomingOccurrences=" + this.upcomingOccurrences + ", address=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.address + "]"; //$NON-NLS-1$
	}
}

package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaActivityType;
import javastrava.model.reference.StravaEventFrequency;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaSkillLevel;
import javastrava.model.reference.StravaTerrainType;
import javastrava.model.reference.StravaWeekOfMonth;

/**
 * <p>
 * Group Events are optionally recurring events for club members. Only club members can access private club events.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClubEvent implements StravaCacheableEntity<Integer> {
	/**
	 * Unique id of this event
	 */
	private Integer id;

	/**
	 * Resource state as returned by Strava
	 */
	private StravaResourceState resourceState;

	/**
	 * Title of the event
	 */
	private String title;

	/**
	 * Text description of the event
	 */
	private String description;

	/**
	 * Club that the event belongs to
	 */
	private StravaClub club;

	/**
	 * Unique id of the club that the event belongs to
	 */
	@Deprecated
	private Integer clubId;

	/**
	 * Type of activity for this event
	 */
	private StravaActivityType activityType;

	/**
	 * Date and time the event was created
	 */
	private ZonedDateTime createdAt;

	/**
	 * Unique ID of the Strava route associated with the
	 */
	@Deprecated
	private Integer routeId;

	/**
	 * Event is for women
	 */
	private Boolean womenOnly;

	/**
	 * Is this a private event (only club members can access private events)
	 */
	@SerializedName("private")
	private Boolean privateEvent;

	/**
	 * Skill level (casual, tempo, hammerfest)
	 */
	private StravaSkillLevel skillLevels;

	/**
	 * Terrain type (flat, rolling, etc)
	 */
	private StravaTerrainType terrain;

	/**
	 * List of upcoming occurrences
	 */
	private List<LocalDateTime> upcomingOccurrences;

	/**
	 * Location of the event
	 */
	private String address;

	/**
	 * <code>true</code> if the authenticated athlete has joined the next occurrence of the event
	 */
	private Boolean joined;

	/**
	 * The athlete organising the event
	 */
	private StravaAthlete organizingAthlete;

	/**
	 * The start location of the event
	 */
	private StravaMapPoint startLatlng;

	/**
	 * The route of the event
	 */
	private StravaRoute route;

	/**
	 * The timezone of the event
	 */
	private String zone;

	private StravaClubEventViewerPermissions viewerPermissions;

	private LocalDateTime startDatetime;

	private StravaEventFrequency frequency;

	private StravaWeekOfMonth weekOfMonth;

	private String dayOfWeek;

	private List<String> daysOfWeek;

	private Integer weeklyInterval;

	/**
	 * No-args constructor
	 */
	public StravaClubEvent() {
		// Empty
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
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
		if (this.club == null) {
			if (other.club != null) {
				return false;
			}
		} else if (!this.club.equals(other.club)) {
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
		if (this.dayOfWeek == null) {
			if (other.dayOfWeek != null) {
				return false;
			}
		} else if (!this.dayOfWeek.equals(other.dayOfWeek)) {
			return false;
		}
		if (this.daysOfWeek == null) {
			if (other.daysOfWeek != null) {
				return false;
			}
		} else if (!this.daysOfWeek.equals(other.daysOfWeek)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.frequency != other.frequency) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.joined == null) {
			if (other.joined != null) {
				return false;
			}
		} else if (!this.joined.equals(other.joined)) {
			return false;
		}
		if (this.organizingAthlete == null) {
			if (other.organizingAthlete != null) {
				return false;
			}
		} else if (!this.organizingAthlete.equals(other.organizingAthlete)) {
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
		if (this.route == null) {
			if (other.route != null) {
				return false;
			}
		} else if (!this.route.equals(other.route)) {
			return false;
		}
		if (this.routeId == null) {
			if (other.routeId != null) {
				return false;
			}
		} else if (!this.routeId.equals(other.routeId)) {
			return false;
		}
		if (this.skillLevels != other.skillLevels) {
			return false;
		}
		if (this.startDatetime == null) {
			if (other.startDatetime != null) {
				return false;
			}
		} else if (!this.startDatetime.equals(other.startDatetime)) {
			return false;
		}
		if (this.startLatlng == null) {
			if (other.startLatlng != null) {
				return false;
			}
		} else if (!this.startLatlng.equals(other.startLatlng)) {
			return false;
		}
		if (this.terrain != other.terrain) {
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
		if (this.viewerPermissions == null) {
			if (other.viewerPermissions != null) {
				return false;
			}
		} else if (!this.viewerPermissions.equals(other.viewerPermissions)) {
			return false;
		}
		if (this.weekOfMonth != other.weekOfMonth) {
			return false;
		}
		if (this.weeklyInterval == null) {
			if (other.weeklyInterval != null) {
				return false;
			}
		} else if (!this.weeklyInterval.equals(other.weeklyInterval)) {
			return false;
		}
		if (this.womenOnly == null) {
			if (other.womenOnly != null) {
				return false;
			}
		} else if (!this.womenOnly.equals(other.womenOnly)) {
			return false;
		}
		if (this.zone == null) {
			if (other.zone != null) {
				return false;
			}
		} else if (!this.zone.equals(other.zone)) {
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
	 * @return the club
	 */
	public StravaClub getClub() {
		return this.club;
	}

	/**
	 * @return the clubId
	 */
	@Deprecated
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
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	/**
	 * @return the daysOfWeek
	 */
	public List<String> getDaysOfWeek() {
		return this.daysOfWeek;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the frequency
	 */
	public StravaEventFrequency getFrequency() {
		return this.frequency;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the joined
	 */
	public Boolean getJoined() {
		return this.joined;
	}

	/**
	 * @return the organizingAthlete
	 */
	public StravaAthlete getOrganizingAthlete() {
		return this.organizingAthlete;
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
	 * @return the route
	 */
	public StravaRoute getRoute() {
		return this.route;
	}

	/**
	 * @return the routeId
	 */
	@Deprecated
	public Integer getRouteId() {
		return this.routeId;
	}

	/**
	 * @return the skillLevel
	 */
	public StravaSkillLevel getSkillLevels() {
		return this.skillLevels;
	}

	/**
	 * @return the startDatetime
	 */
	public LocalDateTime getStartDatetime() {
		return this.startDatetime;
	}

	/**
	 * @return the startLatlng
	 */
	public StravaMapPoint getStartLatlng() {
		return this.startLatlng;
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
	public List<LocalDateTime> getUpcomingOccurrences() {
		return this.upcomingOccurrences;
	}

	/**
	 * @return The viewer permissions
	 */
	public StravaClubEventViewerPermissions getViewerPermissions() {
		return this.viewerPermissions;
	}

	/**
	 * @return the weeklyInterval
	 */
	public Integer getWeeklyInterval() {
		return this.weeklyInterval;
	}

	/**
	 * @return the weekOfMonth
	 */
	public StravaWeekOfMonth getWeekOfMonth() {
		return this.weekOfMonth;
	}

	/**
	 * @return the womenOnly
	 */
	public Boolean getWomenOnly() {
		return this.womenOnly;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return this.zone;
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
		result = (prime * result) + ((this.activityType == null) ? 0 : this.activityType.hashCode());
		result = (prime * result) + ((this.address == null) ? 0 : this.address.hashCode());
		result = (prime * result) + ((this.club == null) ? 0 : this.club.hashCode());
		result = (prime * result) + ((this.clubId == null) ? 0 : this.clubId.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.dayOfWeek == null) ? 0 : this.dayOfWeek.hashCode());
		result = (prime * result) + ((this.daysOfWeek == null) ? 0 : this.daysOfWeek.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.frequency == null) ? 0 : this.frequency.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.joined == null) ? 0 : this.joined.hashCode());
		result = (prime * result) + ((this.organizingAthlete == null) ? 0 : this.organizingAthlete.hashCode());
		result = (prime * result) + ((this.privateEvent == null) ? 0 : this.privateEvent.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.route == null) ? 0 : this.route.hashCode());
		result = (prime * result) + ((this.routeId == null) ? 0 : this.routeId.hashCode());
		result = (prime * result) + ((this.skillLevels == null) ? 0 : this.skillLevels.hashCode());
		result = (prime * result) + ((this.startDatetime == null) ? 0 : this.startDatetime.hashCode());
		result = (prime * result) + ((this.startLatlng == null) ? 0 : this.startLatlng.hashCode());
		result = (prime * result) + ((this.terrain == null) ? 0 : this.terrain.hashCode());
		result = (prime * result) + ((this.title == null) ? 0 : this.title.hashCode());
		result = (prime * result) + ((this.upcomingOccurrences == null) ? 0 : this.upcomingOccurrences.hashCode());
		result = (prime * result) + ((this.viewerPermissions == null) ? 0 : this.viewerPermissions.hashCode());
		result = (prime * result) + ((this.weekOfMonth == null) ? 0 : this.weekOfMonth.hashCode());
		result = (prime * result) + ((this.weeklyInterval == null) ? 0 : this.weeklyInterval.hashCode());
		result = (prime * result) + ((this.womenOnly == null) ? 0 : this.womenOnly.hashCode());
		result = (prime * result) + ((this.zone == null) ? 0 : this.zone.hashCode());
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
	 * @param club
	 *            the club to set
	 */
	public void setClub(StravaClub club) {
		this.club = club;
	}

	/**
	 * @param clubId
	 *            the clubId to set
	 */
	@Deprecated
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
	 * @param dayOfWeek
	 *            the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @param daysOfWeek
	 *            the daysOfWeek to set
	 */
	public void setDaysOfWeek(List<String> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(StravaEventFrequency frequency) {
		this.frequency = frequency;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param joined
	 *            the joined to set
	 */
	public void setJoined(Boolean joined) {
		this.joined = joined;
	}

	/**
	 * @param organizingAthlete
	 *            the organizingAthlete to set
	 */
	public void setOrganizingAthlete(StravaAthlete organizingAthlete) {
		this.organizingAthlete = organizingAthlete;
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
	 * @param route
	 *            the route to set
	 */
	public void setRoute(StravaRoute route) {
		this.route = route;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	@Deprecated
	public void setRouteId(final Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * @param skillLevel
	 *            the skillLevel to set
	 */
	public void setSkillLevels(final StravaSkillLevel skillLevel) {
		this.skillLevels = skillLevel;
	}

	/**
	 * @param startDatetime
	 *            the startDatetime to set
	 */
	public void setStartDatetime(LocalDateTime startDatetime) {
		this.startDatetime = startDatetime;
	}

	/**
	 * @param startLatlng
	 *            the startLatlng to set
	 */
	public void setStartLatlng(StravaMapPoint startLatlng) {
		this.startLatlng = startLatlng;
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
	public void setUpcomingOccurrences(final List<LocalDateTime> upcomingOccurrences) {
		this.upcomingOccurrences = upcomingOccurrences;
	}

	/**
	 * @param viewerPermissions
	 *            The viewer permissions to set
	 */
	public void setViewerPermissions(StravaClubEventViewerPermissions viewerPermissions) {
		this.viewerPermissions = viewerPermissions;
	}

	/**
	 * @param weeklyInterval
	 *            the weeklyInterval to set
	 */
	public void setWeeklyInterval(Integer weeklyInterval) {
		this.weeklyInterval = weeklyInterval;
	}

	/**
	 * @param weekOfMonth
	 *            the weekOfMonth to set
	 */
	public void setWeekOfMonth(StravaWeekOfMonth weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	/**
	 * @param womenOnly
	 *            the womenOnly to set
	 */
	public void setWomenOnly(final Boolean womenOnly) {
		this.womenOnly = womenOnly;
	}

	/**
	 * @param zone
	 *            the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaClubEvent [id=" + this.id + ", resourceState=" + this.resourceState + ", title=" + this.title + ", description=" + this.description + ", club=" + this.club + ", clubId=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.clubId + ", activityType=" //$NON-NLS-1$
				+ this.activityType + ", createdAt=" + this.createdAt + ", routeId=" + this.routeId + ", womenOnly=" + this.womenOnly + ", privateEvent=" + this.privateEvent + ", skillLevels=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.skillLevels + ", terrain=" //$NON-NLS-1$
				+ this.terrain + ", upcomingOccurrences=" + this.upcomingOccurrences + ", address=" + this.address + ", joined=" + this.joined + ", organizingAthlete=" + this.organizingAthlete //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", startLatlng=" + this.startLatlng //$NON-NLS-1$
				+ ", route=" + this.route + ", zone=" + this.zone + ", viewerPermissions=" + this.viewerPermissions + ", startDatetime=" + this.startDatetime + ", frequency=" + this.frequency //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ ", weekOfMonth=" + this.weekOfMonth //$NON-NLS-1$
				+ ", dayOfWeek=" + this.dayOfWeek + ", daysOfWeek=" + this.daysOfWeek + ", weeklyInterval=" + this.weeklyInterval + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}

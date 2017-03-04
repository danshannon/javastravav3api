package javastrava.api.v3.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaRouteSubType;
import javastrava.api.v3.model.reference.StravaRouteType;
import javastrava.cache.StravaCacheable;

/**
 * <p>
 * Routes are manually-created paths made up of sections called legs. Currently it is only possible to create routes using the
 * Routebuilder web interface.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaRoute implements StravaCacheable<Integer>, StravaEntity {

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
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the athlete
	 */
	public StravaAthlete getAthlete() {
		return this.athlete;
	}

	/**
	 * @param athlete
	 *            the athlete to set
	 */
	public void setAthlete(StravaAthlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the elevationGain
	 */
	public Float getElevationGain() {
		return this.elevationGain;
	}

	/**
	 * @param elevationGain
	 *            the elevationGain to set
	 */
	public void setElevationGain(Float elevationGain) {
		this.elevationGain = elevationGain;
	}

	/**
	 * @return the map
	 */
	public StravaMap getMap() {
		return this.map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(StravaMap map) {
		this.map = map;
	}

	/**
	 * @return the type
	 */
	public StravaRouteType getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(StravaRouteType type) {
		this.type = type;
	}

	/**
	 * @return the subType
	 */
	public StravaRouteSubType getSubType() {
		return this.subType;
	}

	/**
	 * @param subType
	 *            the subType to set
	 */
	public void setSubType(StravaRouteSubType subType) {
		this.subType = subType;
	}

	/**
	 * @return the isPrivate
	 */
	public Boolean getIsPrivate() {
		return this.isPrivate;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 */
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @return the starred
	 */
	public Boolean getStarred() {
		return this.starred;
	}

	/**
	 * @param starred
	 *            the starred to set
	 */
	public void setStarred(Boolean starred) {
		this.starred = starred;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the segments
	 */
	public List<StravaSegment> getSegments() {
		return this.segments;
	}

	/**
	 * @param segments
	 *            the segments to set
	 */
	public void setSegments(List<StravaSegment> segments) {
		this.segments = segments;
	}
}

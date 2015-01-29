package com.danshannon.strava.api.service;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Gear;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * <p>{@link Gear} related services.</p>
 * 
 * <p>Gear represents equipment used during an {@link Activity}.</p>
 * 
 * <p>The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface GearServices {
	/**
	 * <p>Retrieve details about a specific item of {@link Gear}. The requesting {@link Athlete} must own the {@link Gear}. At this time it is not possible to view just anyone's gear type and usage.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/gear/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/gear/#show">http://strava.github.io/api/v3/gear/#show</a>
	 * 
	 * @param id The id of the {@link Gear} to be retrieved.
	 * @return Returns a detailed {@link Gear} representation.
	 * @throws UnauthorizedException If service token is invalid 
	 */
	public Gear getGear(String id) throws UnauthorizedException;
}

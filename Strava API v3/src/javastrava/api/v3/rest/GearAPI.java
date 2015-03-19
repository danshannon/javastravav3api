package javastrava.api.v3.rest;

import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * <p>
 * API definitions for the gear services endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface GearAPI {
	/**
	 * @see javastrava.api.v3.service.GearService#getGear(java.lang.String)
	 * 
	 * @param gearId Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException If the gear with the given id doesn't exist
	 */
	@GET("/gear/{id}")
	public StravaGear getGear(@Path("id") final String gearId) throws NotFoundException;

}

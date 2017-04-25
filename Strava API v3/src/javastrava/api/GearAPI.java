package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaGear;
import javastrava.service.exception.NotFoundException;
import retrofit.client.Response;
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
	 * @see javastrava.service.GearService#getGear(java.lang.String)
	 *
	 * @param gearId
	 *            Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 */
	@GET("/gear/{id}")
	public StravaGear getGear(@Path("id") final String gearId) throws NotFoundException;

	/**
	 * @see javastrava.service.GearService#getGear(java.lang.String)
	 *
	 * @param gearId
	 *            Gear identifier
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 */
	@GET("/gear/{id}")
	public void getGear(@Path("id") final String gearId, StravaAPICallback<StravaGear> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.GearService#getGear(java.lang.String)
	 *
	 * @param gearId
	 *            Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException
	 *             If the gear with the given id doesn't exist
	 */
	@GET("/gear/{id}")
	public Response getGearRaw(@Path("id") final String gearId) throws NotFoundException;

}

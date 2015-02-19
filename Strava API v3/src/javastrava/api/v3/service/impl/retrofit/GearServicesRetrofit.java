package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.model.StravaGear;
import javastrava.api.v3.service.exception.NotFoundException;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * <p>
 * Retrofit definitions for the gear services endpoints
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public interface GearServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see javastrava.api.v3.service.GearServices#getGear(java.lang.String)
	 * 
	 * @param id Gear identifier
	 * @return Details of the identified gear
	 * @throws NotFoundException If the gear with the given id doesn't exist
	 */
	@GET("/gear/{id}")
	public StravaGear getGear(@Path("id") final String id) throws NotFoundException;

}

package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import stravajava.api.v3.model.StravaGear;
import stravajava.api.v3.service.exception.NotFoundException;

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
	 * @see stravajava.api.v3.service.GearServices#getGear(java.lang.String)
	 */
	@GET("/gear/{id}")
	public StravaGear getGear(@Path("id") final String id) throws NotFoundException;

}

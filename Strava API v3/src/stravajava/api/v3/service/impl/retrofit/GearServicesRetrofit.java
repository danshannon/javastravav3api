package stravajava.api.v3.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;
import stravajava.api.v3.model.StravaGear;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

public interface GearServicesRetrofit {

	/**
	 * @see stravajava.api.v3.service.GearServices#getGear(java.lang.String)
	 */
	@GET("/gear/{id}")
	public StravaGear getGear(@Path("id") String id) throws NotFoundException, UnauthorizedException;

}

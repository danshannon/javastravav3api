package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.service.exception.NotFoundException;

/**
 * @author Dan Shannon
 *
 */
public interface SegmentEffortServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.BASIC;

	/**
	 * @see stravajava.api.v3.service.SegmentEffortServices#getSegmentEffort(java.lang.Long)
	 */
	@GET("/segment_efforts/{id}")
	public StravaSegmentEffort getSegmentEffort(@Path("id") Long id) throws NotFoundException;

}

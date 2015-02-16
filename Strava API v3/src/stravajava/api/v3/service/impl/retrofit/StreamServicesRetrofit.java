package stravajava.api.v3.service.impl.retrofit;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.service.StreamServices;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

/**
 * <p>
 * Retrofit definitions for {@link StreamServices} endpoints
 * </p>
 * @author Dan Shannon
 *
 */
public interface StreamServicesRetrofit {
	public static RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

	/**
	 * @see stravajava.api.v3.service.StreamServices#getActivityStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[],
	 *      stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/activities/{id}/streams/{types}")
	public StravaStream[] getActivityStreams(@Path("id") final Integer id, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see stravajava.api.v3.service.StreamServices#getEffortStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[],
	 *      stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public StravaStream[] getEffortStreams(@Path("id") final Long id, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see stravajava.api.v3.service.StreamServices#getSegmentStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[],
	 *      stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/segments/{id}/streams/{types}")
	public StravaStream[] getSegmentStreams(@Path("id") final Integer id, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

}

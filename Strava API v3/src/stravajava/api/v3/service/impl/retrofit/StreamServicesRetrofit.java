package stravajava.api.v3.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;

/**
 * @author Dan Shannon
 *
 */
public interface StreamServicesRetrofit {

	/**
	 * @see stravajava.api.v3.service.StreamServices#getActivityStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/activities/{id}/streams/{types}")
	public StravaStream[] getActivityStreams(@Path("id") String id, @Path("types") StravaStreamType[] types, @Query("resolution") StravaStreamResolutionType resolution,
			@Query("series_type") StravaStreamSeriesDownsamplingType seriesType);

	/**
	 * @see stravajava.api.v3.service.StreamServices#getEffortStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public StravaStream[] getEffortStreams(@Path("id") String id, @Path("types") StravaStreamType[] types, @Query("resolution") StravaStreamResolutionType resolution,
			@Query("series_type") StravaStreamSeriesDownsamplingType seriesType);

	/**
	 * @see stravajava.api.v3.service.StreamServices#getSegmentStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)
	 */
	@GET("/segments/{id}/streams/{types}")
	public StravaStream[] getSegmentStreams(@Path("id") String id, @Path("types") StravaStreamType[] types, @Query("resolution") StravaStreamResolutionType resolution,
			@Query("series_type") StravaStreamSeriesDownsamplingType seriesType);

}

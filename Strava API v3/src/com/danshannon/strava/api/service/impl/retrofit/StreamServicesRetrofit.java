package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.danshannon.strava.api.model.Stream;
import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;

/**
 * @author Dan Shannon
 *
 */
public interface StreamServicesRetrofit {

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getActivityStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@GET("/activities/{id}/streams/{types}")
	public Stream[] getActivityStreams(@Path("id") String id, @Path("types") StreamType[] types, @Query("resolution") StreamResolutionType resolution,
			@Query("series_type") StreamSeriesDownsamplingType seriesType);

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getEffortStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public Stream[] getEffortStreams(@Path("id") String id, @Path("types") StreamType[] types, @Query("resolution") StreamResolutionType resolution,
			@Query("series_type") StreamSeriesDownsamplingType seriesType);

	/**
	 * @see com.danshannon.strava.api.service.StreamServices#getSegmentStreams(java.lang.String, com.danshannon.strava.api.model.reference.StreamType[], com.danshannon.strava.api.model.reference.StreamResolutionType, com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType)
	 */
	@GET("/segments/{id}/streams/{types}")
	public Stream[] getSegmentStreams(@Path("id") String id, @Path("types") StreamType[] types, @Query("resolution") StreamResolutionType resolution,
			@Query("series_type") StreamSeriesDownsamplingType seriesType);

}

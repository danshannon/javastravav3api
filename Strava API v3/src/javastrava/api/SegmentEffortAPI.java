package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaSegmentEffort;
import javastrava.service.exception.NotFoundException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * <p>
 * API definition of segment effort services endpoints on the Strava API
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface SegmentEffortAPI {
	/**
	 * @see javastrava.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 *
	 * @param segmentEffortId
	 *            Effort identifier
	 * @return Effort details
	 * @throws NotFoundException
	 *             If the effort with the given id doesn't exist
	 */
	@GET("/segment_efforts/{id}")
	public StravaSegmentEffort getSegmentEffort(@Path("id") final Long segmentEffortId) throws NotFoundException;

	/**
	 * @see javastrava.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 *
	 * @param segmentEffortId
	 *            Effort identifier
	 * @param callback
	 *            The callback to execute on completion
	 * @throws NotFoundException
	 *             If the effort with the given id doesn't exist
	 */
	@GET("/segment_efforts/{id}")
	public void getSegmentEffort(@Path("id") final Long segmentEffortId, final StravaAPICallback<StravaSegmentEffort> callback) throws NotFoundException;

	/**
	 * @see javastrava.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 *
	 * @param segmentEffortId
	 *            Effort identifier
	 * @return Effort details
	 * @throws NotFoundException
	 *             If the effort with the given id doesn't exist
	 */
	@GET("/segment_efforts/{id}")
	public Response getSegmentEffortRaw(@Path("id") final Long segmentEffortId) throws NotFoundException;

}

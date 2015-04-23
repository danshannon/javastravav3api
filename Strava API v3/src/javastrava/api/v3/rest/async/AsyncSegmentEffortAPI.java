package javastrava.api.v3.rest.async;

import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.service.exception.NotFoundException;
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
public interface AsyncSegmentEffortAPI {
	/**
	 * @see javastrava.api.v3.service.SegmentEffortService#getSegmentEffort(java.lang.Long)
	 *
	 * @param segmentEffortId Effort identifier
	 * @param callback The callback to execute on completion
	 * @throws NotFoundException If the effort with the given id doesn't exist
	 */
	@GET("/segment_efforts/{id}")
	public void getSegmentEffort(@Path("id") final Long segmentEffortId, final StravaAPICallback<StravaSegmentEffort> callback) throws NotFoundException;

}

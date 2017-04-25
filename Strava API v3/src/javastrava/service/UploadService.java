package javastrava.service;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import javastrava.model.StravaUploadResponse;
import javastrava.model.reference.StravaActivityType;
import javastrava.service.exception.UnauthorizedException;

/**
 * <p>
 * Uploading to Strava is an asynchronous process. A file is uploaded using a multipart/form-data POST request which performs
 * initial checks on the data and enqueues the file for processing. The activity will not appear in other API requests until it has
 * finished processing successfully.
 * </p>
 *
 * <p>
 * Processing status may be checked by polling Strava. A one-second or longer polling interval is recommended. The mean processing
 * time is currently around 8 seconds. Once processing is complete, Strava will respond to polling requests with the activity's ID.
 * </p>
 *
 * <p>
 * Errors can occur during the submission or processing steps and may be due to malformed activity data or duplicate data
 * submission.
 * </p>
 *
 * <p>
 * Strava supports FIT, TCX and GPX file types as described below. New file types are not on the road map. Developers are encouraged
 * to use one of these types as it will also maximize compatibility with other fitness applications.
 * </p>
 *
 * <p>
 * All files are required to include a time with each trackpoint or record, as defined by the file format. Information such as
 * lat/lng, elevation, heartrate, etc. is optional. Manual creation of activities without a data file is not currently supported by
 * the API.
 * </p>
 *
 * <p>
 * If you feel your file is compatible with the standards but is still not uploading to Strava, please verify that it works with
 * other fitness applications before contacting support.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface UploadService extends StravaService {
	/**
	 * <p>
	 * Upon upload, Strava will respond with an upload ID. You may use this ID to poll the status of your upload. Strava recommends
	 * polling no more than once a second. Polling more frequently is unnecessary. The mean processing time is around 8 seconds.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/uploads/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/uploads/#get-status">http://strava.github.io/api/v3/uploads/#get-status</a>
	 *
	 * @param uploadId
	 *            Upload id originally returned when the upload was done
	 * @return Upload response indicating current status of the upload
	 * @throws UnauthorizedException
	 *             If the authenticated user does not have write access
	 */
	public StravaUploadResponse checkUploadStatus(final Long uploadId) throws UnauthorizedException;

	/**
	 * <p>
	 * Upon upload, Strava will respond with an upload ID. You may use this ID to poll the status of your upload. Strava recommends
	 * polling no more than once a second. Polling more frequently is unnecessary. The mean processing time is around 8 seconds.
	 * </p>
	 *
	 * <p>
	 * URL GET https://www.strava.com/api/v3/uploads/:id
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/uploads/#get-status">http://strava.github.io/api/v3/uploads/#get-status</a>
	 *
	 * @param uploadId
	 *            Upload id originally returned when the upload was done
	 * @return Upload response indicating current status of the upload
	 * @throws UnauthorizedException
	 *             If the authenticated user does not have write access
	 */
	public CompletableFuture<StravaUploadResponse> checkUploadStatusAsync(final Long uploadId) throws UnauthorizedException;

	/**
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 *
	 * <p>
	 * Posting a file for upload will enqueue it for processing. Initial checks will be done for malformed data and duplicates.
	 * </p>
	 *
	 * <p>
	 * URL POST https://www.strava.com/api/v3/uploads
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/uploads/#post-file">http://strava.github.io/api/v3/uploads/#post-file</a>
	 *
	 * @param activityType
	 *            (Optional) Type of activity being uploaded
	 * @param name
	 *            (Optional) if not provided, will be populated using start date and location, if available
	 * @param description
	 *            (Optional)
	 * @param _private
	 *            (Optional) set to 1 to mark the resulting activity as private, 'view_private' permissions will be necessary to
	 *            view the activity
	 * @param trainer
	 *            (Optional) activities without lat/lng info in the file are auto marked as stationary, set to 1 to force
	 * @param commute
	 *            (Optional) set to 1 to mark as commute
	 * @param dataType
	 *            possible values: fit, fit.gz, tcx, tcx.gz, gpx, gpx.gz
	 * @param externalId
	 *            (Optional) data filename will be used by default but should be a unique identifier
	 * @param file
	 *            the actual activity data, if gzipped the data_type must end with .gz
	 * @return Returns an Upload Status object. This object will include an English language status. If success, it will indicate
	 *         the data is still processing. If there was an error, it will describe the error, potentially containing HTML. Upon a
	 *         successful submission the request will return 201 Created. If there was an error the request will return 400 Bad
	 *         Request.
	 */
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description,
			final Boolean _private, final Boolean trainer, final Boolean commute, final String dataType, final String externalId,
			final File file);

	/**
	 * <p>
	 * Requires write permissions, as requested during the authorization process.
	 * </p>
	 *
	 * <p>
	 * Posting a file for upload will enqueue it for processing. Initial checks will be done for malformed data and duplicates.
	 * </p>
	 *
	 * <p>
	 * URL POST https://www.strava.com/api/v3/uploads
	 * </p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/uploads/#post-file">http://strava.github.io/api/v3/uploads/#post-file</a>
	 *
	 * @param activityType
	 *            (Optional) Type of activity being uploaded
	 * @param name
	 *            (Optional) if not provided, will be populated using start date and location, if available
	 * @param description
	 *            (Optional)
	 * @param _private
	 *            (Optional) set to 1 to mark the resulting activity as private, 'view_private' permissions will be necessary to
	 *            view the activity
	 * @param trainer
	 *            (Optional) activities without lat/lng info in the file are auto marked as stationary, set to 1 to force
	 * @param commute
	 *            (Optional) set to 1 to mark as commute
	 * @param dataType
	 *            possible values: fit, fit.gz, tcx, tcx.gz, gpx, gpx.gz
	 * @param externalId
	 *            (Optional) data filename will be used by default but should be a unique identifier
	 * @param file
	 *            the actual activity data, if gzipped the data_type must end with .gz
	 * @return Returns an Upload Status object. This object will include an English language status. If success, it will indicate
	 *         the data is still processing. If there was an error, it will describe the error, potentially containing HTML. Upon a
	 *         successful submission the request will return 201 Created. If there was an error the request will return 400 Bad
	 *         Request.
	 */
	public CompletableFuture<StravaUploadResponse> uploadAsync(final StravaActivityType activityType, final String name,
			final String description, final Boolean _private, final Boolean trainer, final Boolean commute, final String dataType,
			final String externalId, final File file);
}

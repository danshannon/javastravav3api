package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;

/**
 * <p>
 * Streams is the Strava term for the raw data associated with an activity.
 * </p>
 * 
 * <p>
 * All streams for a given {@link StravaActivity activity} or {@link StravaSegmentEffort segment effort} will be the same length and
 * the values at a given index correspond to the same time.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStream {
	/**
	 * Type of stream data
	 */
	private StravaStreamType					type;
	/**
	 * Raw data (either this or {@link #mapPoints} or {@link #moving} will be populated, depending on the {@link #type} of stream).
	 */
	private List<Float>							data;
	/**
	 * Raw GPS co-ordinates (either this or {@link #data} or {@link #moving} will be populated, depending on the {@link #type} of
	 * stream).
	 */
	private List<StravaMapPoint>				mapPoints;
	/**
	 * Boolean data stream indicating whether athlete was moving or not (either this or {@link #data} or {@link #mapPoints} will be populated, depending on the {@link #type} of stream).
	 */
	private List<Boolean>						moving;
	/**
	 * Method of downsampling applied by Strava when returning the stream (if appropriate) - either by distance or by time
	 */
	private StravaStreamSeriesDownsamplingType	seriesType;
	/**
	 * Number of data points in the complete stream
	 */
	private Integer								originalSize;
	/**
	 * Reduced resolution of this stream representation (if appropriate)
	 */
	private StravaStreamResolutionType			resolution;
}

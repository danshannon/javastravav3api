package javastrava.api.v3.model;

import java.util.List;

import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;

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
	/**
	 * @return the type
	 */
	public StravaStreamType getType() {
		return this.type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final StravaStreamType type) {
		this.type = type;
	}
	/**
	 * @return the data
	 */
	public List<Float> getData() {
		return this.data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(final List<Float> data) {
		this.data = data;
	}
	/**
	 * @return the mapPoints
	 */
	public List<StravaMapPoint> getMapPoints() {
		return this.mapPoints;
	}
	/**
	 * @param mapPoints the mapPoints to set
	 */
	public void setMapPoints(final List<StravaMapPoint> mapPoints) {
		this.mapPoints = mapPoints;
	}
	/**
	 * @return the moving
	 */
	public List<Boolean> getMoving() {
		return this.moving;
	}
	/**
	 * @param moving the moving to set
	 */
	public void setMoving(final List<Boolean> moving) {
		this.moving = moving;
	}
	/**
	 * @return the seriesType
	 */
	public StravaStreamSeriesDownsamplingType getSeriesType() {
		return this.seriesType;
	}
	/**
	 * @param seriesType the seriesType to set
	 */
	public void setSeriesType(final StravaStreamSeriesDownsamplingType seriesType) {
		this.seriesType = seriesType;
	}
	/**
	 * @return the originalSize
	 */
	public Integer getOriginalSize() {
		return this.originalSize;
	}
	/**
	 * @param originalSize the originalSize to set
	 */
	public void setOriginalSize(final Integer originalSize) {
		this.originalSize = originalSize;
	}
	/**
	 * @return the resolution
	 */
	public StravaStreamResolutionType getResolution() {
		return this.resolution;
	}
	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(final StravaStreamResolutionType resolution) {
		this.resolution = resolution;
	}
}

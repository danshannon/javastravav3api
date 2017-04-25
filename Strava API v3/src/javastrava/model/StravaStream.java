package javastrava.model;

import java.util.List;

import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaStreamType;

/**
 * <p>
 * Streams is the Strava term for the raw data associated with an activity.
 * </p>
 *
 * <p>
 * All streams for a given {@link StravaActivity activity} or {@link StravaSegmentEffort segment effort} will be the same length and the values at a given index correspond to the same time.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaStream implements StravaEntity {
	/**
	 * Type of stream data
	 */
	private StravaStreamType					type;
	/**
	 * Raw data (either this or {@link #mapPoints} or {@link #moving} will be populated, depending on the {@link #type} of stream).
	 */
	private List<Float>							data;
	/**
	 * Raw GPS co-ordinates (either this or {@link #data} or {@link #moving} will be populated, depending on the {@link #type} of stream).
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
	 * No args constructor
	 */
	public StravaStream() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaStream)) {
			return false;
		}
		final StravaStream other = (StravaStream) obj;
		if (this.data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!this.data.equals(other.data)) {
			return false;
		}
		if (this.mapPoints == null) {
			if (other.mapPoints != null) {
				return false;
			}
		} else if (!this.mapPoints.equals(other.mapPoints)) {
			return false;
		}
		if (this.moving == null) {
			if (other.moving != null) {
				return false;
			}
		} else if (!this.moving.equals(other.moving)) {
			return false;
		}
		if (this.originalSize == null) {
			if (other.originalSize != null) {
				return false;
			}
		} else if (!this.originalSize.equals(other.originalSize)) {
			return false;
		}
		if (this.resolution != other.resolution) {
			return false;
		}
		if (this.seriesType != other.seriesType) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	/**
	 * @return the data
	 */
	public List<Float> getData() {
		return this.data;
	}

	/**
	 * @return the mapPoints
	 */
	public List<StravaMapPoint> getMapPoints() {
		return this.mapPoints;
	}

	/**
	 * @return the moving
	 */
	public List<Boolean> getMoving() {
		return this.moving;
	}

	/**
	 * @return the originalSize
	 */
	public Integer getOriginalSize() {
		return this.originalSize;
	}

	/**
	 * @return the resolution
	 */
	public StravaStreamResolutionType getResolution() {
		return this.resolution;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the seriesType
	 */
	public StravaStreamSeriesDownsamplingType getSeriesType() {
		return this.seriesType;
	}

	/**
	 * @return the type
	 */
	public StravaStreamType getType() {
		return this.type;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
		result = (prime * result) + ((this.mapPoints == null) ? 0 : this.mapPoints.hashCode());
		result = (prime * result) + ((this.moving == null) ? 0 : this.moving.hashCode());
		result = (prime * result) + ((this.originalSize == null) ? 0 : this.originalSize.hashCode());
		result = (prime * result) + ((this.resolution == null) ? 0 : this.resolution.hashCode());
		result = (prime * result) + ((this.seriesType == null) ? 0 : this.seriesType.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final List<Float> data) {
		this.data = data;
	}

	/**
	 * @param mapPoints
	 *            the mapPoints to set
	 */
	public void setMapPoints(final List<StravaMapPoint> mapPoints) {
		this.mapPoints = mapPoints;
	}

	/**
	 * @param moving
	 *            the moving to set
	 */
	public void setMoving(final List<Boolean> moving) {
		this.moving = moving;
	}

	/**
	 * @param originalSize
	 *            the originalSize to set
	 */
	public void setOriginalSize(final Integer originalSize) {
		this.originalSize = originalSize;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(final StravaStreamResolutionType resolution) {
		this.resolution = resolution;
	}

	/**
	 * @param seriesType
	 *            the seriesType to set
	 */
	public void setSeriesType(final StravaStreamSeriesDownsamplingType seriesType) {
		this.seriesType = seriesType;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final StravaStreamType type) {
		this.type = type;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaStream [type=" + this.type + ", data=" + this.data + ", mapPoints=" + this.mapPoints + ", moving=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ this.moving + ", seriesType=" //$NON-NLS-1$
				+ this.seriesType + ", originalSize=" + this.originalSize + ", resolution=" + this.resolution + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}

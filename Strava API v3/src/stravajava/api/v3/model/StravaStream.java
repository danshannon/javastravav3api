package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;
import stravajava.model.MapPoint;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaStream {
	public StravaStream() {
		// Required
		super();
	}
	
	private StravaStreamType type;
	private List<Float> data; 
	private List<MapPoint> mapPoints;
	private StravaStreamSeriesDownsamplingType seriesType;
	private Integer originalSize;
	private StravaStreamResolutionType resolution;
}

package stravajava.api.v3.model;

import java.util.List;

import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;
import lombok.Data;

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
	private List<List<Float>> data; //?????????????????????????????????????
	private StravaStreamSeriesDownsamplingType seriesType;
	private Integer originalSize;
	private StravaStreamResolutionType resolution;
}

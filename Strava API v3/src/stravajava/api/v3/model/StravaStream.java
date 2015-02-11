package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;

/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStream {
	private StravaStreamType type;
	private List<Float> data;
	private List<StravaMapPoint> mapPoints;
	private List<Boolean> moving;
	private StravaStreamSeriesDownsamplingType seriesType;
	private Integer originalSize;
	private StravaStreamResolutionType resolution;
}

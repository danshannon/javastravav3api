package com.danshannon.strava.api.model;

import java.util.List;

import lombok.Data;

import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;

/**
 * @author Dan Shannon
 *
 */
@Data
public class Stream {
	public Stream() {
		// Required
		super();
	}
	
	private StreamType type;
	private List<List<Float>> data; //?????????????????????????????????????
	private StreamSeriesDownsamplingType seriesType;
	private Integer originalSize;
	private StreamResolutionType resolution;
}

package com.danshannon.strava.api.model;

import java.util.List;

import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;

/**
 * @author Dan Shannon
 *
 */
public class Stream {
	private StreamType type;
	private List<List<Float>> data; //?????????????????????????????????????
	private StreamSeriesDownsamplingType seriesType;
	private Integer originalSize;
	private StreamResolutionType resolution;
	/**
	 * @return the type
	 */
	public StreamType getType() {
		return this.type;
	}
	/**
	 * @return the data
	 */
	public List<List<Float>> getData() {
		return this.data;
	}
	/**
	 * @return the seriesType
	 */
	public StreamSeriesDownsamplingType getSeriesType() {
		return this.seriesType;
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
	public StreamResolutionType getResolution() {
		return this.resolution;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(StreamType type) {
		this.type = type;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<List<Float>> data) {
		this.data = data;
	}
	/**
	 * @param seriesType the seriesType to set
	 */
	public void setSeriesType(StreamSeriesDownsamplingType seriesType) {
		this.seriesType = seriesType;
	}
	/**
	 * @param originalSize the originalSize to set
	 */
	public void setOriginalSize(Integer originalSize) {
		this.originalSize = originalSize;
	}
	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(StreamResolutionType resolution) {
		this.resolution = resolution;
	}
	
	
}

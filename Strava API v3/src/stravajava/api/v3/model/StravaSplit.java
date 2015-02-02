package stravajava.api.v3.model;

import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaSplit {
	public StravaSplit() {
		// Required
		super();
	}
	
	private Float distance;
	private Integer elapsedTime;
	private Float elevationDifference;
	private Integer movingTime;
	private Integer split;
}

package javastrava.api.v3.model;

import javastrava.api.v3.service.ActivityService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Split time data associated with runs. Comes in metric (1km) and imperial (1 mile) versions. See {@link ActivityService#getActivity(Integer)}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaSplit {
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Elapsed time for the split in seconds (including time spent stopped)
	 */
	private Integer elapsedTime;
	/**
	 * elevation difference in metres
	 */
	private Float elevationDifference;
	/**
	 * Moving time for the split in seconds (excluding time spent stopped)
	 */
	private Integer movingTime;
	/**
	 * Order of the split within the run
	 */
	private Integer split;
}

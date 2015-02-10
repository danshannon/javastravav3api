package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStatisticsEntry {
	private Integer count;
	private Float distance;
	private Integer movingTime;
	private Integer elapsedTime;
	private Float elevationGain;
	private Integer achievementCount;
}

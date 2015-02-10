package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaStatistics {
	private Float biggestRideDistance;
	private Float biggestClimbElevationGain;
	private StravaStatisticsEntry recentRunTotals;
	private StravaStatisticsEntry ytdRideTotals;
	private StravaStatisticsEntry ytdRunTotals;
	private StravaStatisticsEntry allRideTotals;
	private StravaStatisticsEntry allRunTotals;
}

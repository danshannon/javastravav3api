package javastrava.api.v3.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Athlete's statistics for a segment, returned by Strava with a segment effort
 * </p>
 * @author Dan Shannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaAthleteSegmentStats {
	private Integer effortCount;
	private Integer prElapsedTime;
	private LocalDate prDate;
}

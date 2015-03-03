package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaAthleteSegmentStats {
	private Integer effortCount;
	private Integer prElapsedTime;
	// TODO This is a workaround for issue javastrava-api #28 (https://github.com/danshannon/javastravav3api/issues/28). Should be a Date.
	private String prDate;
}
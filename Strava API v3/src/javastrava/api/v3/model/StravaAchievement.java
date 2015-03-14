package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Authenticated user's achievement on a given segment effort
 * </p>
 * @see StravaSegmentEffort#getAchievements()
 * @author Dan Shannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaAchievement {
	private Integer typeId;
	private String type;
	private Integer rank;
}

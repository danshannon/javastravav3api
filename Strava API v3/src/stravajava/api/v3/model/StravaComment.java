package stravajava.api.v3.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.model.reference.StravaResourceState;

/**
 * <p>
 * Record of an individual comment made on an activity
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaComment {
	/**
	 * Strava's unique identifier for the comment
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Identifier of the activity on which this is a comment
	 */
	private Integer activityId;
	/**
	 * Text of the comment. Allegedly Strava supports markdown in comments!
	 */
	private String text;
	/**
	 * Athlete who MADE the comment
	 */
	private StravaAthlete athlete;
	/**
	 * Date and time the comment was posted
	 */
	private Date createdAt;
}

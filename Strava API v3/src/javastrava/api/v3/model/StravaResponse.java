package javastrava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Representation of the response received from Strava in error situations (most commonly when resources are not found or there is
 * an authorisation issue)
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaResponse {
	/**
	 * Text message describing the overall error
	 */
	private String					message;
	/**
	 * List of error details
	 */
	private List<StravaAPIError>	errors;
}

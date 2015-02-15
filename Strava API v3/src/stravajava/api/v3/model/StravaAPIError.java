package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Error details returned in a {@link StravaResponse} when an error is returned by the API
 * 
 * @author Dan Shannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaAPIError {
	private String resource;
	private String field;
	private String code;
}

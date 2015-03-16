package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Photo(s) associated with a specific activity
 * </p>
 * @author Dan Shannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaActivityPhotos {
	private StravaPhoto primary;
	private Integer count;
}

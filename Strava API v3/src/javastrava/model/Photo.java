package javastrava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Photo {
	private String id;
	private Activity activity;
}

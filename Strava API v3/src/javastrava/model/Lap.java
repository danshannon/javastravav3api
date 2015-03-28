package javastrava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Lap extends AbstractSubActivity {
	private Integer lapNumber;
}

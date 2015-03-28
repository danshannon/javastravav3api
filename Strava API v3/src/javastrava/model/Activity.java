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
public class Activity extends AbstractActivity {
	private Athlete athlete;
	private Gear gear;
}

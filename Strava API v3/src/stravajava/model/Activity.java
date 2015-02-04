package stravajava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dshannon
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Activity extends AbstractActivity {
		private Athlete athlete;
		private Gear gear;
}

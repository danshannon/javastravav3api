package stravajava.model;

import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public class Activity extends AbstractActivity {
		private Athlete athlete;
		private Gear gear;
}

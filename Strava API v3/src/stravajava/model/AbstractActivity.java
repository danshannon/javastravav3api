package stravajava.model;

import java.util.Calendar;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dshannon
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public abstract class AbstractActivity {
	private String id;
	private ActivityMap map;
	private Calendar startTime;
	private Float distance;
}

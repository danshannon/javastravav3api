package stravajava.model;

import java.util.Calendar;

import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public abstract class AbstractActivity {
	private String id;
	private ActivityMap map;
	private Calendar startTime;
	private Float distance;
}

package stravajava.model;

import java.util.List;

import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public class Club {
	private String id;
	private List<Athlete> members;
}

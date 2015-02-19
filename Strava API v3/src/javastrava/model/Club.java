package javastrava.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dshannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Club {
	private String id;
	private List<Athlete> members;
}

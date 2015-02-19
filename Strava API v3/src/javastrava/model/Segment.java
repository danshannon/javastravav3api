package javastrava.model;

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
public class Segment {
	private String id;
	private SegmentMap map;
}

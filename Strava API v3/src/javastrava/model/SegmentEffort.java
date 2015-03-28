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
public class SegmentEffort extends AbstractSubActivity {
	private Segment segment;
}

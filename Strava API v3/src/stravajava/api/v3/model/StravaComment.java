package stravajava.api.v3.model;

import java.util.Date;

import stravajava.api.v3.model.reference.StravaResourceState;
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
public class StravaComment {
	private Integer id;
	private StravaResourceState resourceState;
	private Integer activityId;
	private String text;
	private StravaAthlete athlete;
	private Date createdAt;
}

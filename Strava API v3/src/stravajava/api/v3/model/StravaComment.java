package stravajava.api.v3.model;

import java.util.Date;

import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author dshannon
 *
 */
@Data
public class StravaComment {
	public StravaComment() {
		// Required
		super();
	}
	
	private Integer id;
	private StravaResourceState resourceState;
	private Integer activityId;
	private String text;
	private StravaAthlete athlete;
	private Date createdAt;
}

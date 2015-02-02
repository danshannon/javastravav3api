package stravajava.api.v3.model;

import stravajava.api.v3.model.reference.StravaFrameType;
import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * <p>StravaGear represents equipment used during an {@link StravaActivity}.</p>
 * 
 * <p>The object is returned in summary or detailed {@link StravaResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaGear {
	public StravaGear() {
		// Required
		super();
	}
	
	private String id;
	private Boolean primary;
	private String name;
	private Float distance;
	private String brandName;
	private String modelName;
	private StravaFrameType frameType;
	private String description;
	private StravaResourceState resourceState;
}

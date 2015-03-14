package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaFrameType;
import javastrava.api.v3.model.reference.StravaGearType;
import javastrava.api.v3.model.reference.StravaResourceState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * StravaGear represents equipment used during an {@link StravaActivity}. Is either a bike, or shoes at present.
 * </p>
 * 
 * <p>
 * The object is returned in summary or detailed {@link StravaResourceState representations}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaGear {
	/**
	 * Unique identifier for the gear. Is prefixed with 'b' for bikes when returning via the API
	 */
	private String id;
	/**
	 * Is set to <code>true</code> if this is the athlete's default bike / shoes
	 */
	private Boolean primary;
	/**
	 * Name given to the gear by the owner
	 */
	private String name;
	/**
	 * Total distance in metres
	 */
	private Float distance;
	/**
	 * Brand name
	 */
	private String brandName;
	/**
	 * Model name
	 */
	private String modelName;
	/**
	 * (Bikes only) 1 -> mtb, 2 -> cross, 3 -> road, 4 -> time trial
	 */
	private StravaFrameType frameType;
	/**
	 * Test description
	 */
	private String description;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	
	private StravaGearType gearType;
	
	/**
	 * @param id The id of the gear
	 */
	public void setId(final String id) {
		this.id = id;
		if (id != null) {
			this.gearType = StravaGearType.create(id.substring(0, 0));
		}
	}
}

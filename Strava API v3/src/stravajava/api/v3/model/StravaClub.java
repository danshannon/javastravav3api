package stravajava.api.v3.model;

import stravajava.api.v3.model.reference.StravaClubType;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.model.reference.StravaSportType;
import lombok.Data;

/**
 * <p>Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned in summary or detailed {@link StravaResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaClub {
	public StravaClub() {
		// Required
		super();
	}
	
	private Integer id;
	private StravaResourceState resourceState;
	private String name;
	private String profileMedium;
	private String profile;
	private String description;
	private StravaClubType clubType;
	private StravaSportType sportType;
	private String city;
	private String state;
	private String country;
	/**
	 * NB is "private" in the API
	 */
	private Boolean privateClub;
	private Integer memberCount;
}

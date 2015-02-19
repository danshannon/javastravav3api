package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaClubType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSportType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Clubs represent groups of athletes on Strava. They can be public or private. Only members of private clubs can access their details. The object is returned
 * in summary or detailed {@link StravaResourceState representations}.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaClub {
	/**
	 * Strava's unique identifier for this club
	 */
	private Integer id;
	/**
	 * State of this resource on Strava
	 */
	private StravaResourceState resourceState;
	/**
	 * Club name
	 */
	private String name;
	/**
	 * URL to a 62x62 pixel profile picture
	 */
	private String profileMedium;
	/**
	 * URL to a 124x124 pixel profile picture
	 */
	private String profile;
	/**
	 * Description of the club
	 */
	private String description;
	/**
	 * Type of club: casual_club, racing_team, shop, company, other
	 */
	private StravaClubType clubType;
	/**
	 * cycling, running, triathlon, other
	 */
	private StravaSportType sportType;
	/**
	 * City that the club is based in
	 */
	private String city;
	/**
	 * State, territory, county, canton etc. that the club is based in
	 */
	private String state;
	/**
	 * Country that the club is based in
	 */
	private String country;
	/**
	 * Is set to <code>true</code> if the club is private (and therefore you require permission to join)
	 */
	@SerializedName("private")
	private Boolean privateClub;
	/**
	 * Number of club members (calculated by Strava, not checked or re-calculated by javastrava)
	 */
	private Integer memberCount;
}

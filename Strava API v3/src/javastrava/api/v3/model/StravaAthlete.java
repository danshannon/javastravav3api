package javastrava.api.v3.model;

import java.time.ZonedDateTime;
import java.util.List;

import javastrava.api.v3.model.reference.StravaFollowerState;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaResourceState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Detailed representation of an StravaAthlete
 * 
 * See http://strava.github.io/api/v3/athlete/
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaAthlete {
	private Integer id;
	private StravaResourceState resourceState;
	private String firstname;
	private String lastname;
	private String profileMedium;
	private String profile;
	private String city;
	private String state;
	private String country;
	private StravaGender sex;
	private StravaFollowerState friend;
	private StravaFollowerState follower;
	private Boolean premium;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	private Boolean approveFollowers;
	private Integer followerCount;
	private Integer friendCount;
	private Integer mutualFriendCount;
	private String datePreference;
	private StravaMeasurementMethod measurementPreference;
	private String email;
	private Integer ftp;
	private List<StravaClub> clubs;
	private List<StravaGear> bikes;
	private List<StravaGear> shoes;
	private Float weight;
	private Integer badgeTypeId;
}

package stravajava.api.v3.model;

import java.util.Date;
import java.util.List;

import stravajava.api.v3.model.reference.StravaFollowerState;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.model.reference.StravaMeasurementMethod;
import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * Detailed representation of an StravaAthlete
 * 
 * See http://strava.github.io/api/v3/athlete/
 * 
 * @author dshannon
 *
 */
@Data
public class StravaAthlete {
	public StravaAthlete() {
		// Required
		super();
	}
	
	private Integer id;
	private StravaResourceState resourceState;
	private String firstName;
	private String lastName;
	private String profileMedium;
	private String profile;
	private String city;
	private String state;
	private String country;
	private StravaGender sex;
	private StravaFollowerState friend;
	private StravaFollowerState follower;
	private Boolean premium;
	private Date createdAt;
	private Date updatedAt;
	private Boolean approveFollowers;
	private Integer followerCount;
	private Integer friendCount;
	private Integer mutualFriendCount;
	private String dateFormatPreference;
	private StravaMeasurementMethod measurementPreference;
	private String email;
	private Integer ftp;
	private List<StravaClub> clubs;
	private List<StravaGear> bikes;
	private List<StravaGear> shoes;
	private Float weight;
}

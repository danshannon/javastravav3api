package stravajava.api.v3.model;

import stravajava.api.v3.service.ClubServices;
import lombok.Data;

/**
 * <p>Container for response from club membership join and leave</p>
 * 
 * <p>See {@link ClubServices#joinClub(Integer)} and {@link ClubServices#leaveClub(Integer)}
 * 
 * @author Dan Shannon
 *
 */
@Data
public class StravaClubMembershipResponse {
	public StravaClubMembershipResponse() {
		// Required
		super();
	}
	
	private Boolean success;
	private Boolean active;
}

package test.api.model;

import stravajava.api.v3.model.StravaClubMembershipResponse;
import test.utils.BeanTest;


/**
 * @author dshannon
 *
 */
public class StravaClubMembershipResponseTest extends BeanTest<StravaClubMembershipResponse> {

	@Override
	protected Class<StravaClubMembershipResponse> getClassUnderTest() {
		return StravaClubMembershipResponse.class;
	}
	
}

/**
 * 
 */
package test.api.service.impl.retrofit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.service.StreamServices;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.StreamServicesImpl;
import test.utils.TestUtils;

/**
 * @author danshannon
 *
 */
public class StreamServicesImplTest {

	/**
	 * Test method for {@link stravajava.api.v3.service.impl.retrofit.StreamServicesImpl#implementation(java.lang.String)}.
	 */
	@Test
	public void testImplementation_validToken() {
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Didn't get a service implementation using a valid token",service);
	}
	
	@Test
	public void testImplementation_invalidToken() {
		StreamServices service = StreamServicesImpl.implementation(TestUtils.INVALID_TOKEN);
		try {
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected behaviour
		}
		fail("Got a usable implementation from an invalid token");
	}

	/**
	 * Test method for {@link stravajava.api.v3.service.impl.retrofit.StreamServicesImpl#getActivityStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}.
	 * @throws UnauthorizedException 
	 */
	@Test
	public void testGetActivityStreams_validActivityAuthenticatedUser() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(streams);
	}

	/**
	 * Test method for {@link stravajava.api.v3.service.impl.retrofit.StreamServicesImpl#getEffortStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}.
	 */
	@Test
	public void testGetEffortStreams() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link stravajava.api.v3.service.impl.retrofit.StreamServicesImpl#getSegmentStreams(java.lang.String, stravajava.api.v3.model.reference.StravaStreamType[], stravajava.api.v3.model.reference.StravaStreamResolutionType, stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}.
	 */
	@Test
	public void testGetSegmentStreams() {
		fail("Not yet implemented");
	}

	private StreamServices getService() {
		return StreamServicesImpl.implementation(TestUtils.getValidToken());
	}
	
	private StreamServices getServiceWithoutWriteAccess() {
		return StreamServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
	}
}

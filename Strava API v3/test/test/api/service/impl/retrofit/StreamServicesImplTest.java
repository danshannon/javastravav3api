/**
 * 
 */
package test.api.service.impl.retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.service.StreamServices;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.api.v3.service.impl.retrofit.StreamServicesImpl;

import org.junit.Test;

import test.utils.TestUtils;

/**
 * @author danshannon
 *
 */
public class StreamServicesImplTest {

	/**
	 * Test method for {@link javastrava.api.v3.service.impl.retrofit.StreamServicesImpl#implementation(java.lang.String)}.
	 * 
	 * @throws UnauthorizedException
	 */
	@Test
	public void testImplementation_validToken() throws UnauthorizedException {
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		assertNotNull("Didn't get a service implementation using a valid token", service);
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(streams);
	}

	@Test
	public void testImplementation_invalidToken() {
		try {
			StreamServices service = StreamServicesImpl.implementation(TestUtils.INVALID_TOKEN);
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected behaviour
			return;
		}
		fail("Got a usable implementation from an invalid token");
	}

	@Test
	public void testImplementation_implementationIsCached() {
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		StreamServices service2 = StreamServicesImpl.implementation(TestUtils.getValidToken());
		assertEquals("Retrieved multiple service instances for the same token - should only be one", service, service2);
	}

	@Test
	public void testImplementation_differentImplementationIsNotCached() {
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		StreamServices service2 = StreamServicesImpl.implementation(TestUtils.getValidTokenWithoutWriteAccess());
		assertFalse(service == service2);
	}

	/**
	 * Test method for
	 * {@link javastrava.api.v3.service.impl.retrofit.StreamServicesImpl#getActivityStreams(java.lang.String, javastrava.api.v3.model.reference.StravaStreamType[], javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}
	 * .
	 * 
	 * @throws UnauthorizedException
	 */
	// 1. Valid activity for the authenticated user
	@Test
	public void testGetActivityStreams_validActivityAuthenticatedUser() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(streams);
	}

	// 2. Invalid activity
	@Test
	public void testGetActivityStreams_invalidActivity() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_INVALID);
		assertNull(streams);
	}

	// 3. Valid activity for other user
	@Test
	public void testGetActivityStreams_validActivityUnauthenticatedUser() {
		StreamServices service = getService();
		try {
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_UNAUTHENTICATED_USER);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		fail("Shouldn't be able to return activity streams for activities that don't belong to the authenticated user");
	}

	// 4. All stream types
	@Test
	public void testGetActivityStreams_allStreamTypes() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
		assertNotNull(streams);
		int size = 0;
		for (StravaStream stream : streams) {
			if (size == 0) {
				size = stream.getOriginalSize();
			}
			if (stream.getType() == StravaStreamType.MAPPOINT) {
				assertEquals(size, stream.getMapPoints().size());
			} else if (stream.getType() == StravaStreamType.MOVING) {
				assertEquals(size, stream.getMoving().size());
			} else {
				assertEquals(size, stream.getData().size());
			}
			assertNotNull(stream.getType());
		}
	}

	// 5. Only one stream type
	@Test
	public void testGetActivityStreams_oneStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, null, null, StravaStreamType.DISTANCE);
		assertNotNull(streams);
		assertEquals(1, streams.size());
		assertEquals(StravaStreamType.DISTANCE, streams.get(0).getType());
	}

	// 6. Downsampled by time
	@Test
	public void testGetActivityStreams_downsampledByTime() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN) {
				List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, resolutionType,
						StravaStreamSeriesDownsamplingType.TIME);
				assertNotNull(streams);
				for (StravaStream stream : streams) {
					assertEquals(resolutionType, stream.getResolution());
					if (stream.getType() == StravaStreamType.MAPPOINT) {
						assertTrue(resolutionType.getSize() >= stream.getMapPoints().size());
					} else if (stream.getType() == StravaStreamType.MOVING) {
						assertTrue(resolutionType.getSize() >= stream.getMoving().size());
					} else {
						assertTrue(resolutionType.getSize() >= stream.getData().size());
					}
				}
			}
		}
	}

	// 7. Downsampled by distance
	@Test
	public void testGetActivityStreams_downsampledByDistance() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN && resolutionType != null) {
				List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, resolutionType,
						StravaStreamSeriesDownsamplingType.DISTANCE);
				assertNotNull(streams);
				for (StravaStream stream : streams) {
					assertEquals(resolutionType, stream.getResolution());
					if (stream.getType() == StravaStreamType.MAPPOINT) {
						assertTrue(resolutionType.getSize() >= stream.getMapPoints().size());
					} else if (stream.getType() == StravaStreamType.MOVING) {
						assertTrue(resolutionType.getSize() >= stream.getMoving().size());
					} else {
						assertTrue(resolutionType.getSize() >= stream.getData().size());
					}
				}
			}
		}
	}

	// 8. Invalid stream type
	@Test
	public void testGetActivityStreams_invalidStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, null, null, StravaStreamType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Should have thrown an illegal argument exception");
	}

	// 9. Invalid downsample resolution
	@Test
	public void testGetActivityStreams_invalidDownsampleResolution() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, StravaStreamResolutionType.UNKNOWN, null);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample resolution");
	}

	// 10. Invalid downsample type (i.e. not distance or time)
	@Test
	public void testGetActivityStreams_invalidDownsampleType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, StravaStreamResolutionType.LOW, StravaStreamSeriesDownsamplingType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample type");
	}

	/**
	 * Test method for
	 * {@link javastrava.api.v3.service.impl.retrofit.StreamServicesImpl#getEffortStreams(java.lang.String, javastrava.api.v3.model.reference.StravaStreamType[], javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}
	 * .
	 */
	// 1. Valid effort for the authenticated user
	@Test
	public void testGetEffortStreams_validEffortAuthenticatedUser() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID);
		assertNotNull(streams);
	}

	// 2. Invalid effort
	@Test
	public void testGetEffortStreams_invalidEffort() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_INVALID_ID);
		assertNull(streams);
	}

	// 3. Valid effort for other user
	@Test
	public void testGetEffortStreams_validEffortUnauthenticatedUser() {
		StreamServices service = getService();
		try {
			service.getEffortStreams(TestUtils.SEGMENT_EFFORT_OTHER_USER_PRIVATE_ID);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		fail("Shouldn't be able to return effort streams for activities that don't belong to the authenticated user");
	}

	// 4. All stream types
	@Test
	public void testGetAEffortStreams_allStreamTypes() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID);
		assertNotNull(streams);
		int size = 0;
		for (StravaStream stream : streams) {
			if (size == 0) {
				size = stream.getOriginalSize();
			}
			if (stream.getType() == StravaStreamType.MAPPOINT) {
				assertEquals(size, stream.getMapPoints().size());
			} else if (stream.getType() == StravaStreamType.MOVING) {
				assertEquals(size, stream.getMoving().size());
			} else {
				assertEquals(size, stream.getData().size());
			}
			assertNotNull(stream.getType());
		}
	}

	// 5. Only one stream type
	@Test
	public void testGetEffortStreams_oneStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, null, null, StravaStreamType.DISTANCE);
		assertNotNull(streams);
		assertEquals(1, streams.size());
		assertEquals(StravaStreamType.DISTANCE, streams.get(0).getType());
	}

	// 6. Downsampled by time
	@Test
	public void testGetEffortStreams_downsampledByTime() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN) {
				List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, resolutionType,
						StravaStreamSeriesDownsamplingType.TIME);
				assertNotNull(streams);
				for (StravaStream stream : streams) {
					assertEquals(resolutionType, stream.getResolution());
					if (stream.getType() == StravaStreamType.MAPPOINT) {
						assertTrue(resolutionType.getSize() >= stream.getMapPoints().size());
					} else if (stream.getType() == StravaStreamType.MOVING) {
						assertTrue(resolutionType.getSize() >= stream.getMoving().size());
					} else {
						assertTrue(resolutionType.getSize() >= stream.getData().size());
					}
				}
			}
		}
	}

	// 7. Downsampled by distance
	@Test
	public void testGetEffortStreams_downsampledByDistance() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN && resolutionType != null) {
				List<StravaStream> streams = service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, resolutionType,
						StravaStreamSeriesDownsamplingType.DISTANCE);
				assertNotNull(streams);
				for (StravaStream stream : streams) {
					assertEquals(resolutionType, stream.getResolution());
					if (stream.getType() == StravaStreamType.MAPPOINT) {
						assertTrue(resolutionType.getSize() >= stream.getMapPoints().size());
					} else if (stream.getType() == StravaStreamType.MOVING) {
						assertTrue(resolutionType.getSize() >= stream.getMoving().size());
					} else {
						assertTrue(resolutionType.getSize() >= stream.getData().size());
					}
				}
			}
		}
	}

	// 8. Invalid stream type
	@Test
	public void testGetEffortStreams_invalidStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, null, null, StravaStreamType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Should have got an IllegalArgumentException, but didn't");
	}

	// 9. Invalid downsample resolution
	@Test
	public void testGetEffortStreams_invalidDownsampleResolution() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, StravaStreamResolutionType.UNKNOWN, null);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample resolution");
	}

	// 10. Invalid downsample type (i.e. not distance or time)
	@Test
	public void testGetEffortStreams_invalidDownsampleType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getEffortStreams(TestUtils.SEGMENT_EFFORT_VALID_ID, StravaStreamResolutionType.LOW, StravaStreamSeriesDownsamplingType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample type");
	}

	/**
	 * Test method for
	 * {@link javastrava.api.v3.service.impl.retrofit.StreamServicesImpl#getSegmentStreams(java.lang.String, javastrava.api.v3.model.reference.StravaStreamType[], javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType)}
	 * .
	 */
	@Test
	// 1. Valid segment for the authenticated user
	public void testGetSegmentStreams_validSegment() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(streams);
	}

	// 2. Invalid segment
	@Test
	public void testGetSegmentStreams_invalidSegment() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getSegmentStreams(TestUtils.SEGMENT_INVALID_ID);
		assertNull(streams);
	}

	// 3. Valid segment which is private and belongs to another user
	@Test
	public void testGetSegmentStreams_validSegmentUnauthenticatedUser() {
		StreamServices service = getService();
		try {
			service.getSegmentStreams(TestUtils.SEGMENT_OTHER_USER_PRIVATE_ID);
		} catch (UnauthorizedException e) {
			// Expected
			return;
		}
		fail("Shouldn't be able to return activity streams for private segments that don't belong to the authenticated user");
	}

	// 4. All stream types
	@Test
	public void testGetSegmentStreams_allStreamTypes() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(streams);
		int size = 0;
		for (StravaStream stream : streams) {
			if (size == 0) {
				size = stream.getOriginalSize();
			}
			if (stream.getType() == StravaStreamType.MAPPOINT) {
				assertEquals(size, stream.getMapPoints().size());
			} else if (stream.getType() == StravaStreamType.MOVING) {
				assertEquals(size, stream.getMoving().size());
			} else {
				assertEquals(size, stream.getData().size());
			}
			assertNotNull(stream.getType());
		}
	}

	// 5. Only one stream type
	@Test
	public void testGetSegmentStreams_oneStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		List<StravaStream> streams = service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, null, null, StravaStreamType.DISTANCE);
		assertNotNull(streams);
		assertEquals(1, streams.size());
		assertEquals(StravaStreamType.DISTANCE, streams.get(0).getType());
	}

	// 6. Downsampled by time - can't be done for segment streams as there's no time element
	@Test
	public void testGetSegmentStreams_downsampledByTime() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN) {
				try {
					service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, resolutionType, StravaStreamSeriesDownsamplingType.TIME);
				} catch (IllegalArgumentException e) {
					// expected
					return;
				}
				fail("Can't return a segment stream which is downsampled by TIME!");
				// assertNotNull(streams);
				// for (StravaStream stream : streams) {
				// assertEquals(resolutionType,stream.getResolution());
				// assertEquals(StravaStreamSeriesDownsamplingType.TIME,stream.getSeriesType());
				// if (stream.getType() == StravaStreamType.MAPPOINT) {
				// assertTrue("Expected max resolution of " + resolutionType.getSize() + ", but got " + stream.getMapPoints().size() +
				// " map points!",resolutionType.getSize() >= stream.getMapPoints().size());
				// } else if (stream.getType() == StravaStreamType.MOVING) {
				// assertTrue("Expected max resolution of " + resolutionType.getSize() + ", but got " + stream.getMoving().size() +
				// " moving data points!",resolutionType.getSize() >= stream.getMoving().size());
				// } else {
				// assertTrue("Expected max resolution of " + resolutionType.getSize() + ", but got " + stream.getData().size() +
				// " data points!",resolutionType.getSize() >= stream.getData().size());
				// }
				// }
			}
		}
	}

	// 7. Downsampled by distance
	@Test
	public void testGetSegmentStreams_downsampledByDistance() throws UnauthorizedException {
		StreamServices service = getService();
		for (StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
			if (resolutionType != StravaStreamResolutionType.UNKNOWN && resolutionType != null) {
				List<StravaStream> streams = service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, resolutionType, StravaStreamSeriesDownsamplingType.DISTANCE);
				assertNotNull(streams);
				for (StravaStream stream : streams) {
					assertEquals(resolutionType, stream.getResolution());
					if (stream.getType() == StravaStreamType.MAPPOINT) {
						assertTrue(resolutionType.getSize() >= stream.getMapPoints().size());
					} else if (stream.getType() == StravaStreamType.MOVING) {
						assertTrue(resolutionType.getSize() >= stream.getMoving().size());
					} else {
						assertTrue(resolutionType.getSize() >= stream.getData().size());
					}
				}
			}
		}
	}

	// 8. Invalid stream type
	@Test
	public void testGetSegmentStreams_invalidStreamType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, null, null, StravaStreamType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Should have got an IllegalArgumentException, but didn't");
	}

	// 9. Invalid downsample resolution
	@Test
	public void testGetSegmentStreams_invalidDownsampleResolution() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, StravaStreamResolutionType.UNKNOWN, null);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample resolution");
	}

	// 10. Invalid downsample type (i.e. not distance or time)
	@Test
	public void testGetSegmentStreams_invalidDownsampleType() throws UnauthorizedException {
		StreamServices service = getService();
		try {
			service.getSegmentStreams(TestUtils.SEGMENT_VALID_ID, StravaStreamResolutionType.LOW, StravaStreamSeriesDownsamplingType.UNKNOWN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Didn't throw an exception when asking for an invalid downsample type");
	}

	private StreamServices getService() {
		return StreamServicesImpl.implementation(TestUtils.getValidToken());
	}
}

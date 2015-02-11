package test.util.impl.gson.serializer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.service.StreamServices;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.api.v3.service.impl.retrofit.StreamServicesImpl;
import stravajava.util.exception.JsonSerialisationException;
import test.utils.TestUtils;

public class StravaStreamSerializerTest extends SerializerTest<StravaStream> {
	@Test
	@Override
	public void testRoundTrip() throws UnauthorizedException, JsonSerialisationException {
		// Get a stream
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, StravaStreamResolutionType.LOW,
				StravaStreamSeriesDownsamplingType.DISTANCE);
		assertNotNull(streams);
		assertTrue(streams.size() > 0);
		for (StravaStream stream : streams) {
			// Serialize
			String element = this.util.serialise(stream);
			// Then de-serialize
			StravaStream returned = this.util.deserialise(element, StravaStream.class);
			// Then make sure they are the same
			assertNotNull(returned);
			assertTrue(returned.equals(stream));
		}

	}

	@Override
	public void testDeserialiseInputStream() throws JsonSerialisationException {
		// Get a stream
		StreamServices service = StreamServicesImpl.implementation(TestUtils.getValidToken());
		List<StravaStream> streams = service.getActivityStreams(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, StravaStreamResolutionType.LOW,
				StravaStreamSeriesDownsamplingType.DISTANCE);
		assertNotNull(streams);
		assertTrue(streams.size() > 0);
		for (StravaStream stream : streams) {
			// Serialize
			String element = this.util.serialise(stream);
			InputStream is = new ByteArrayInputStream(element.getBytes());
			// Then de-serialize
			StravaStream returned = this.util.deserialise(is, StravaStream.class);
			// Then make sure they are the same
			assertNotNull(returned);
			assertTrue(returned.equals(stream));
		}

	}

	@Override
	public Class<StravaStream> getClassUnderTest() {
		return StravaStream.class;
	}

}

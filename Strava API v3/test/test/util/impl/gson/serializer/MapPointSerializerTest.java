package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javastrava.api.v3.model.StravaMapPoint;
import javastrava.util.exception.JsonSerialisationException;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class MapPointSerializerTest extends SerializerTest<StravaMapPoint> {
	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		StravaMapPoint point = new StravaMapPoint(135.4f, -40f);
		String serialised = this.util.serialise(point);
		StravaMapPoint deserialised = this.util.deserialise(serialised, StravaMapPoint.class);
		assertEquals(point, deserialised);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaMapPoint prompt = this.util.deserialise("", StravaMapPoint.class);
		assertNull(prompt);
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#testDeserialiseInputStream()
	 */
	@Override
	public void testDeserialiseInputStream() throws JsonSerialisationException {
		StravaMapPoint point = new StravaMapPoint(111.11f, -43f);
		String serialised = this.util.serialise(point);
		InputStream is = new ByteArrayInputStream(serialised.getBytes());
		StravaMapPoint deserialised = this.util.deserialise(is, StravaMapPoint.class);
		assertEquals(point, deserialised);

	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<StravaMapPoint> getClassUnderTest() {
		return StravaMapPoint.class;
	}

}

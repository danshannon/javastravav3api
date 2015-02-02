package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.StravaMapPoint;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class MapPointSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		StravaMapPoint point = new StravaMapPoint(135.4f,-40f);
		String serialised = this.util.serialise(point);
		StravaMapPoint deserialised = this.util.deserialise(serialised, StravaMapPoint.class);
		assertEquals(point,deserialised);
	}
	
	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaMapPoint prompt = this.util.deserialise("", StravaMapPoint.class);
		assertNull(prompt);
	}

}

package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ResourceStateSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaResourceState type : StravaResourceState.values()) {
			String serialized = this.util.serialise(type);
			StravaResourceState deserialized = this.util.deserialise(serialized, StravaResourceState.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		String serialized = "75";
		StravaResourceState deserialized = this.util.deserialise(serialized, StravaResourceState.class);
		assertEquals(deserialized, StravaResourceState.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaResourceState prompt = this.util.deserialise("", StravaResourceState.class);
		assertNull(prompt);
	}

}

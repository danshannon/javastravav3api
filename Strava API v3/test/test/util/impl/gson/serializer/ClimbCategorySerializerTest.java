package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ClimbCategorySerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaClimbCategory type : StravaClimbCategory.values()) {
			String serialized = this.util.serialise(type);
			StravaClimbCategory deserialized = this.util.deserialise(serialized, StravaClimbCategory.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		String serialized = "75";
		StravaClimbCategory deserialized = this.util.deserialise(serialized, StravaClimbCategory.class);
		assertEquals(deserialized, StravaClimbCategory.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaClimbCategory prompt = this.util.deserialise("", StravaClimbCategory.class);
		assertNull(prompt);
	}

}

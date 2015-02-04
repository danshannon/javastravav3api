package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaWorkoutType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class WorkoutTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaWorkoutType type : StravaWorkoutType.values()) {
			String serialized = this.util.serialise(type);
			StravaWorkoutType deserialized = this.util.deserialise(serialized, StravaWorkoutType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		String serialized = "75";
		StravaWorkoutType deserialized = this.util.deserialise(serialized, StravaWorkoutType.class);
		assertEquals(deserialized, StravaWorkoutType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaWorkoutType prompt = this.util.deserialise("", StravaWorkoutType.class);
		assertNull(prompt);
	}

}

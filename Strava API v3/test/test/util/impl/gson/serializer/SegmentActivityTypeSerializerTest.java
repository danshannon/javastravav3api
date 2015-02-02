package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaSegmentActivityType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class SegmentActivityTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaSegmentActivityType type : StravaSegmentActivityType.values()) {
			String serialized = this.util.serialise(type);
			StravaSegmentActivityType deserialized = this.util.deserialise(serialized, StravaSegmentActivityType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaSegmentActivityType deserialized = this.util.deserialise(serialized, StravaSegmentActivityType.class);
		assertEquals(deserialized, StravaSegmentActivityType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaSegmentActivityType prompt = this.util.deserialise("", StravaSegmentActivityType.class);
		assertNull(prompt);
	}

}

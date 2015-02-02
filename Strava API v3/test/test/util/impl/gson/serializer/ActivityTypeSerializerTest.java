package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ActivityTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaActivityType ageGroup : StravaActivityType.values()) {
			String serialized = this.util.serialise(ageGroup);
			StravaActivityType deserialized = this.util.deserialise(serialized, StravaActivityType.class);
			assertEquals(ageGroup, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaActivityType deserialized = this.util.deserialise(serialized, StravaActivityType.class);
		assertEquals(deserialized, StravaActivityType.UNKNOWN);
	}
	
	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaActivityType prompt = this.util.deserialise("", StravaActivityType.class);
		assertNull(prompt);
	}

}

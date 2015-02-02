package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaActivityZoneType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ActivityZoneTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaActivityZoneType type : StravaActivityZoneType.values()) {
			String serialized = this.util.serialise(type);
			StravaActivityZoneType deserialized = this.util.deserialise(serialized, StravaActivityZoneType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaActivityZoneType deserialized = this.util.deserialise(serialized, StravaActivityZoneType.class);
		assertEquals(deserialized, StravaActivityZoneType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaActivityZoneType prompt = this.util.deserialise("", StravaActivityZoneType.class);
		assertNull(prompt);
	}

}

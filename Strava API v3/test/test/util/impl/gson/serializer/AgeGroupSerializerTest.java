package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaAgeGroup;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class AgeGroupSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaAgeGroup ageGroup : StravaAgeGroup.values()) {
			String serialized = this.util.serialise(ageGroup);
			StravaAgeGroup deserialized = this.util.deserialise(serialized, StravaAgeGroup.class);
			assertEquals(ageGroup, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaAgeGroup deserialized = this.util.deserialise(serialized, StravaAgeGroup.class);
		assertEquals(deserialized, StravaAgeGroup.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaAgeGroup group = this.util.deserialise("", StravaAgeGroup.class);
		assertNull(group);
	}

}

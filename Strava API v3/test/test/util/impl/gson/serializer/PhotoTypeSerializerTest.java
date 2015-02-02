package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaPhotoType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class PhotoTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaPhotoType type : StravaPhotoType.values()) {
			String serialized = this.util.serialise(type);
			StravaPhotoType deserialized = this.util.deserialise(serialized, StravaPhotoType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaPhotoType deserialized = this.util.deserialise(serialized, StravaPhotoType.class);
		assertEquals(deserialized, StravaPhotoType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaPhotoType prompt = this.util.deserialise("", StravaPhotoType.class);
		assertNull(prompt);
	}

}

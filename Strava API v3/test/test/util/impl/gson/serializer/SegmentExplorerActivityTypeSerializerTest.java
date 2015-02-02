package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerActivityTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
			String serialized = this.util.serialise(type);
			StravaSegmentExplorerActivityType deserialized = this.util.deserialise(serialized, StravaSegmentExplorerActivityType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaSegmentExplorerActivityType deserialized = this.util.deserialise(serialized, StravaSegmentExplorerActivityType.class);
		assertEquals(deserialized, StravaSegmentExplorerActivityType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaSegmentExplorerActivityType prompt = this.util.deserialise("", StravaSegmentExplorerActivityType.class);
		assertNull(prompt);
	}

}

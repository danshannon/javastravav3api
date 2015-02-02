package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class StreamSeriesDownsamplingTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaStreamSeriesDownsamplingType type : StravaStreamSeriesDownsamplingType.values()) {
			String serialized = this.util.serialise(type);
			StravaStreamSeriesDownsamplingType deserialized = this.util.deserialise(serialized, StravaStreamSeriesDownsamplingType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaStreamSeriesDownsamplingType deserialized = this.util.deserialise(serialized, StravaStreamSeriesDownsamplingType.class);
		assertEquals(deserialized, StravaStreamSeriesDownsamplingType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaStreamSeriesDownsamplingType prompt = this.util.deserialise("", StravaStreamSeriesDownsamplingType.class);
		assertNull(prompt);
	}

}

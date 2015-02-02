package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.model.reference.StravaMeasurementMethod;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class MeasurementMethodSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws JsonSerialisationException {
		for (StravaMeasurementMethod type : StravaMeasurementMethod.values()) {
			String serialized = this.util.serialise(type);
			StravaMeasurementMethod deserialized = this.util.deserialise(serialized, StravaMeasurementMethod.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StravaMeasurementMethod deserialized = this.util.deserialise(serialized, StravaMeasurementMethod.class);
		assertEquals(deserialized, StravaMeasurementMethod.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		StravaMeasurementMethod prompt = this.util.deserialise("", StravaMeasurementMethod.class);
		assertNull(prompt);
	}

}

package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.SegmentActivityType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

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
	public void testRoundTrip() throws ServiceException {
		for (SegmentActivityType type : SegmentActivityType.values()) {
			String serialized = this.util.serialise(type);
			SegmentActivityType deserialized = this.util.deserialise(serialized, SegmentActivityType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		SegmentActivityType deserialized = this.util.deserialise(serialized, SegmentActivityType.class);
		assertEquals(deserialized, SegmentActivityType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		SegmentActivityType prompt = this.util.deserialise("", SegmentActivityType.class);
		assertNull(prompt);
	}

}

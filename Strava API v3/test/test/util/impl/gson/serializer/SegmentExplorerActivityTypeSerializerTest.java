package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.SegmentExplorerActivityType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

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
	public void testRoundTrip() throws ServiceException {
		for (SegmentExplorerActivityType type : SegmentExplorerActivityType.values()) {
			String serialized = this.util.serialise(type);
			SegmentExplorerActivityType deserialized = this.util.deserialise(serialized, SegmentExplorerActivityType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		SegmentExplorerActivityType deserialized = this.util.deserialise(serialized, SegmentExplorerActivityType.class);
		assertEquals(deserialized, SegmentExplorerActivityType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		SegmentExplorerActivityType prompt = this.util.deserialise("", SegmentExplorerActivityType.class);
		assertNull(prompt);
	}

}

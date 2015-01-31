package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class StreamResolutionTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (StreamResolutionType type : StreamResolutionType.values()) {
			String serialized = this.util.serialise(type);
			StreamResolutionType deserialized = this.util.deserialise(serialized, StreamResolutionType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		StreamResolutionType deserialized = this.util.deserialise(serialized, StreamResolutionType.class);
		assertEquals(deserialized, StreamResolutionType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		StreamResolutionType prompt = this.util.deserialise("", StreamResolutionType.class);
		assertNull(prompt);
	}

}

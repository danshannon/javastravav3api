package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.ActivityZoneType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

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
	public void testRoundTrip() throws ServiceException {
		for (ActivityZoneType type : ActivityZoneType.values()) {
			String serialized = this.util.serialise(type);
			ActivityZoneType deserialized = this.util.deserialise(serialized, ActivityZoneType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		ActivityZoneType deserialized = this.util.deserialise(serialized, ActivityZoneType.class);
		assertEquals(deserialized, ActivityZoneType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		ActivityZoneType prompt = this.util.deserialise("", ActivityZoneType.class);
		assertNull(prompt);
	}

}

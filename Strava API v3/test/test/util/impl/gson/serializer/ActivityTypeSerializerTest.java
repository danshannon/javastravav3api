package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ActivityTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (ActivityType ageGroup : ActivityType.values()) {
			String serialized = this.util.serialise(ageGroup);
			ActivityType deserialized = this.util.deserialise(serialized, ActivityType.class);
			assertEquals(ageGroup, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		ActivityType deserialized = this.util.deserialise(serialized, ActivityType.class);
		assertEquals(deserialized, ActivityType.UNKNOWN);
	}
	
	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		ActivityType prompt = this.util.deserialise("", ActivityType.class);
		assertNull(prompt);
	}

}

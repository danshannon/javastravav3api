package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.FollowerState;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class FollowerStateSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (FollowerState type : FollowerState.values()) {
			String serialized = this.util.serialise(type);
			FollowerState deserialized = this.util.deserialise(serialized, FollowerState.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		FollowerState deserialized = this.util.deserialise(serialized, FollowerState.class);
		assertEquals(deserialized, FollowerState.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		FollowerState prompt = this.util.deserialise("", FollowerState.class);
		assertNull(prompt);
	}

}

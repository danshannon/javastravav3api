package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.ClubType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ClubTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (ClubType type : ClubType.values()) {
			String serialized = this.util.serialise(type);
			ClubType deserialized = this.util.deserialise(serialized, ClubType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		ClubType deserialized = this.util.deserialise(serialized, ClubType.class);
		assertEquals(deserialized, ClubType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		ClubType prompt = this.util.deserialise("", ClubType.class);
		assertNull(prompt);
	}

}

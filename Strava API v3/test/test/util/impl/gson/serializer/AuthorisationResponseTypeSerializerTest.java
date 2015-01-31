package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class AuthorisationResponseTypeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (AuthorisationResponseType type : AuthorisationResponseType.values()) {
			String serialized = this.util.serialise(type);
			AuthorisationResponseType deserialized = this.util.deserialise(serialized, AuthorisationResponseType.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		AuthorisationResponseType deserialized = this.util.deserialise(serialized, AuthorisationResponseType.class);
		assertEquals(deserialized, AuthorisationResponseType.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		AuthorisationResponseType prompt = this.util.deserialise("", AuthorisationResponseType.class);
		assertNull(prompt);
	}

}

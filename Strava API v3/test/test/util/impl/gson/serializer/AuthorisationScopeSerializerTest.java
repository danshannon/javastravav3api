package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class AuthorisationScopeSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (AuthorisationScope type : AuthorisationScope.values()) {
			String serialized = this.util.serialise(type);
			AuthorisationScope deserialized = this.util.deserialise(serialized, AuthorisationScope.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		AuthorisationScope deserialized = this.util.deserialise(serialized, AuthorisationScope.class);
		assertEquals(deserialized, AuthorisationScope.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		AuthorisationScope prompt = this.util.deserialise("", AuthorisationScope.class);
		assertNull(prompt);
	}

}

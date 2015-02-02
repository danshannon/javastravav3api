package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import stravajava.api.v3.auth.ref.AuthorisationScope;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

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
	public void testRoundTrip() throws JsonSerialisationException {
		for (AuthorisationScope type : AuthorisationScope.values()) {
			String serialized = this.util.serialise(type);
			AuthorisationScope deserialized = this.util.deserialise(serialized, AuthorisationScope.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		AuthorisationScope deserialized = this.util.deserialise(serialized, AuthorisationScope.class);
		assertEquals(deserialized, AuthorisationScope.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		AuthorisationScope prompt = this.util.deserialise("", AuthorisationScope.class);
		assertNull(prompt);
	}

}

package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ResourceStateSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (ResourceState type : ResourceState.values()) {
			String serialized = this.util.serialise(type);
			ResourceState deserialized = this.util.deserialise(serialized, ResourceState.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "75";
		ResourceState deserialized = this.util.deserialise(serialized, ResourceState.class);
		assertEquals(deserialized, ResourceState.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		ResourceState prompt = this.util.deserialise("", ResourceState.class);
		assertNull(prompt);
	}

}

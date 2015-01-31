package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class ClimbCategorySerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (ClimbCategory type : ClimbCategory.values()) {
			String serialized = this.util.serialise(type);
			ClimbCategory deserialized = this.util.deserialise(serialized, ClimbCategory.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "75";
		ClimbCategory deserialized = this.util.deserialise(serialized, ClimbCategory.class);
		assertEquals(deserialized, ClimbCategory.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		ClimbCategory prompt = this.util.deserialise("", ClimbCategory.class);
		assertNull(prompt);
	}

}

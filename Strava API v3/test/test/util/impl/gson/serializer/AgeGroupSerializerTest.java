package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.AgeGroup;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class AgeGroupSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (AgeGroup ageGroup : AgeGroup.values()) {
			String serialized = this.util.serialise(ageGroup);
			AgeGroup deserialized = this.util.deserialise(serialized, AgeGroup.class);
			assertEquals(ageGroup, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		AgeGroup deserialized = this.util.deserialise(serialized, AgeGroup.class);
		assertEquals(deserialized, AgeGroup.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		AgeGroup group = this.util.deserialise("", AgeGroup.class);
		assertNull(group);
	}

}

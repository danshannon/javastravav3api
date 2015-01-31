package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;
import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.WeightClass;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class WeightClassSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		for (WeightClass type : WeightClass.values()) {
			String serialized = this.util.serialise(type);
			WeightClass deserialized = this.util.deserialise(serialized, WeightClass.class);
			assertEquals(type, deserialized);
		}
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
		WeightClass deserialized = this.util.deserialise(serialized, WeightClass.class);
		assertEquals(deserialized, WeightClass.UNKNOWN);
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		WeightClass prompt = this.util.deserialise("", WeightClass.class);
		assertNull(prompt);
	}

}

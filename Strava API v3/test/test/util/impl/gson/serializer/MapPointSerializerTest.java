package test.util.impl.gson.serializer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.danshannon.strava.api.model.MapPoint;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class MapPointSerializerTest {
	private JsonUtil util;
	
	@Before
	public void before() {
		this.util = new JsonUtilImpl();
	}

	@Test
	public void testRoundTrip() throws ServiceException {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testDeserializeUnknownValue() throws ServiceException {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testNullDeserialisationSafety() throws ServiceException {
		MapPoint prompt = this.util.deserialise("", MapPoint.class);
		assertNull(prompt);
	}

}

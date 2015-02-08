package test.util.impl.gson.serializer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.jfairy.Fairy;
import org.junit.Test;

import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

public abstract class SerializerTest<T> {
	protected JsonUtil util = new JsonUtilImpl();
	
	@Test
	public abstract void testRoundTrip() throws JsonSerialisationException;
	
	@Test
	public abstract void testDeserializeUnknownValue() throws JsonSerialisationException;
	
	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		T value = this.util.deserialise("", getClassUnderTest());
		assertNull(value);
		value = this.util.deserialise("null", getClassUnderTest());
		assertNull(value);
	}
	
	@Test
	public void testDeserialisationOfBadData() throws JsonSerialisationException {
		String badData = Fairy.create().textProducer().paragraph();
		try {
			this.util.deserialise(badData, getClassUnderTest());
		} catch (JsonSerialisationException e) {
			// Expected
			return;
		}
		fail("Should have thrown a JsonSerialisationException when deserialising string '" + badData + "' to " + getClassUnderTest().getName());

	}
	
	@Test
	public abstract void testDeserialiseInputStream() throws JsonSerialisationException;
	
	public abstract Class<T> getClassUnderTest();
	
	
}

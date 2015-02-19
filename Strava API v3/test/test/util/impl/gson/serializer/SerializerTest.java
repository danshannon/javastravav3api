package test.util.impl.gson.serializer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javastrava.util.JsonUtil;
import javastrava.util.exception.JsonSerialisationException;
import javastrava.util.impl.gson.JsonUtilImpl;

import org.jfairy.Fairy;
import org.junit.Test;

public abstract class SerializerTest<T> {
	protected JsonUtil util = new JsonUtilImpl();

	@Test
	public abstract void testRoundTrip() throws JsonSerialisationException;

	@Test
	public void testNullDeserialisationSafety() throws JsonSerialisationException {
		T value = this.util.deserialise("", getClassUnderTest());
		assertNull(value);
		value = this.util.deserialise("null", getClassUnderTest());
		assertNull(value);
	}

	@Test
	public void testDeserialiseNullString() throws JsonSerialisationException {
		String nullString = null;
		T value = this.util.deserialise(nullString, getClassUnderTest());
		assertNull(value);
	}

	@Test
	public void testDeserialiseNullInputStream() throws JsonSerialisationException {
		InputStream is = new ByteArrayInputStream("".getBytes());
		is = null;
		T value = this.util.deserialise(is, getClassUnderTest());
		assertNull(value);
	}

	@Test
	public void testSerialiseNull() throws JsonSerialisationException {
		T value = null;
		String stringValue = this.util.serialise(value);
		T comparison = this.util.deserialise(stringValue, getClassUnderTest());
		assertNull(comparison);
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

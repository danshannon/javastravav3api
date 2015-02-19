package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javastrava.util.exception.JsonSerialisationException;

import org.junit.Test;

public abstract class EnumSerializerTest<T extends Enum<T>> extends SerializerTest<T> {

	@Override
	public void testRoundTrip() throws JsonSerialisationException {
		for (Object enumValue : getClassUnderTest().getEnumConstants()) {
			@SuppressWarnings("unchecked")
			T value = (T) enumValue;
			String serialized = this.util.serialise(value);
			T deserialized = this.util.deserialise(serialized, value.getDeclaringClass());
			assertEquals(value, deserialized);
		}
	}

	protected abstract T getUnknownValue();

	@Test
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		String serialized = getUnknownValue().toString();
		T deserialized = this.util.deserialise(serialized, getClassUnderTest());
		assertEquals(deserialized, getUnknownValue());
	}

	@Test
	public void testDeserializeUnexpectedValue() throws JsonSerialisationException {
		String serialized = "-999";
		T deserialized = this.util.deserialise(serialized, getClassUnderTest());
		assertEquals(deserialized, getUnknownValue());
	}

	@Override
	public void testDeserialiseInputStream() throws JsonSerialisationException {
		for (Object enumValue : getClassUnderTest().getEnumConstants()) {
			@SuppressWarnings("unchecked")
			T value = (T) enumValue;
			String text = "\"" + value.toString() + "\"";
			InputStream is = new ByteArrayInputStream(text.getBytes());
			T deserialised = this.util.deserialise(is, getClassUnderTest());
			assertEquals(deserialised, value);
		}
	}

}

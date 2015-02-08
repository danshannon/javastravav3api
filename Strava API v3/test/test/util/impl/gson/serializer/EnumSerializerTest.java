package test.util.impl.gson.serializer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.jfairy.Fairy;
import org.jfairy.producer.text.TextProducer;

import stravajava.util.exception.JsonSerialisationException;

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

	@Override
	public void testDeserializeUnknownValue() throws JsonSerialisationException {
		TextProducer text = Fairy.create().textProducer();
		String serialized = "\"" + text.word(2) + "\"";
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

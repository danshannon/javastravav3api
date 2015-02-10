package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaMeasurementMethod;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Dan Shannon
 *
 */
public class MeasurementMethodSerializer implements JsonSerializer<StravaMeasurementMethod>, JsonDeserializer<StravaMeasurementMethod>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaMeasurementMethod deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		return StravaMeasurementMethod.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaMeasurementMethod method, Type type, JsonSerializationContext context) {
		return context.serialize(method.getValue());
	}

}

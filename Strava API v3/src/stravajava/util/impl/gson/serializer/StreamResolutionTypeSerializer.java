package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaStreamResolutionType;

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
public class StreamResolutionTypeSerializer implements JsonSerializer<StravaStreamResolutionType>, JsonDeserializer<StravaStreamResolutionType>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaStreamResolutionType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		return (json == null ? null : StravaStreamResolutionType.create(json.getAsString()));
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaStreamResolutionType resolutionType, Type type, JsonSerializationContext context) {
		return (resolutionType == null ? null : context.serialize(resolutionType.getValue()));
	}

}

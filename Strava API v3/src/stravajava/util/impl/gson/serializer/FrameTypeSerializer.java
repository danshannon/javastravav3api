package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaFrameType;

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
public class FrameTypeSerializer implements JsonSerializer<StravaFrameType>, JsonDeserializer<StravaFrameType>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaFrameType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) { return null; }
		return StravaFrameType.create(json.getAsInt());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaFrameType frameType, Type type, JsonSerializationContext context) {
		if (frameType == null) { return null; }
		return context.serialize(frameType.getValue());
	}

}

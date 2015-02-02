package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaClimbCategory;

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
public class ClimbCategorySerializer implements JsonSerializer<StravaClimbCategory>, JsonDeserializer<StravaClimbCategory>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaClimbCategory deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) { return null; }
		return StravaClimbCategory.create(json.getAsInt());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaClimbCategory climbCategory, Type type, JsonSerializationContext context) {
		if (climbCategory == null) { return null; }
		return context.serialize(climbCategory.getValue());
	}

}

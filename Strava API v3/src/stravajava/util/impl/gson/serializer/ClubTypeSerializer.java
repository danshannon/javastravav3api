package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaClubType;

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
public class ClubTypeSerializer implements JsonSerializer<StravaClubType>, JsonDeserializer<StravaClubType>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaClubType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) { return null; }
		return StravaClubType.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaClubType clubType, Type type, JsonSerializationContext context) {
		if (clubType == null) { return null; }
		return context.serialize(clubType.getValue());
	}

}

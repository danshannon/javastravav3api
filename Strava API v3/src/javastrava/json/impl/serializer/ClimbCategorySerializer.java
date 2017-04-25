package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaClimbCategory;

/**
 * @author Dan Shannon
 *
 */
public class ClimbCategorySerializer implements JsonSerializer<StravaClimbCategory>, JsonDeserializer<StravaClimbCategory> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaClimbCategory deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		try {
			return StravaClimbCategory.create(Integer.valueOf(json.getAsInt()));
		} catch (NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaClimbCategory climbCategory, final Type type, final JsonSerializationContext context) {
		return context.serialize(climbCategory.getValue());
	}

}

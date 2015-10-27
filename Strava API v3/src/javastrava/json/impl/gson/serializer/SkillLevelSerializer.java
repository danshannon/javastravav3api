package javastrava.json.impl.gson.serializer;

import java.lang.reflect.Type;

import javastrava.api.v3.model.reference.StravaSkillLevel;

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
public class SkillLevelSerializer implements JsonSerializer<StravaSkillLevel>, JsonDeserializer<StravaSkillLevel> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaSkillLevel deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		try {
			final StravaSkillLevel activityType = StravaSkillLevel.create(Integer.valueOf(json.getAsInt()));
			return activityType;
		} catch (final NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaSkillLevel skillLevel, final Type type, final JsonSerializationContext context) {
		return context.serialize(skillLevel.getValue());
	}

}

package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaSkillLevel;

/**
 * Bespoke enum serialiser for StravaSkillLevel
 *
 * @author Dan Shannon
 *
 */
public class SkillLevelSerializer implements JsonSerializer<StravaSkillLevel>, JsonDeserializer<StravaSkillLevel> {

	@Override
	public StravaSkillLevel deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		try {
			final StravaSkillLevel activityType = StravaSkillLevel.create(Integer.valueOf(json.getAsInt()));
			return activityType;
		} catch (final NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(final StravaSkillLevel skillLevel, final Type type, final JsonSerializationContext context) {
		return context.serialize(skillLevel.getValue());
	}

}

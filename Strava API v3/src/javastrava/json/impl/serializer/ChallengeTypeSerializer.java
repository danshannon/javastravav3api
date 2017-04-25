package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaChallengeType;

/**
 * @author Dan Shannon
 *
 */
public class ChallengeTypeSerializer implements JsonSerializer<StravaChallengeType>, JsonDeserializer<StravaChallengeType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaChallengeType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		final StravaChallengeType activityType = StravaChallengeType.create(json.getAsString());
		return activityType;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaChallengeType activityType, final Type type, final JsonSerializationContext context) {
		return context.serialize(activityType.getValue());
	}

}

package javastrava.json.impl.gson.serializer;

import java.lang.reflect.Type;

import javastrava.api.v3.model.webhook.reference.StravaSubscriptionObjectType;

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
public class SubscriptionObjectTypeSerializer implements JsonSerializer<StravaSubscriptionObjectType>, JsonDeserializer<StravaSubscriptionObjectType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaSubscriptionObjectType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		final StravaSubscriptionObjectType activityType = StravaSubscriptionObjectType.create(Integer.valueOf(json.getAsInt()));
		return activityType;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaSubscriptionObjectType skillLevel, final Type type, final JsonSerializationContext context) {
		return context.serialize(skillLevel.getValue());
	}

}

package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.webhook.reference.StravaSubscriptionAspectType;

/**
 * @author Dan Shannon
 *
 */
public class SubscriptionAspectTypeSerializer implements JsonSerializer<StravaSubscriptionAspectType>, JsonDeserializer<StravaSubscriptionAspectType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaSubscriptionAspectType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		try {
			final StravaSubscriptionAspectType activityType = StravaSubscriptionAspectType.create(Integer.valueOf(json.getAsInt()));
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
	public JsonElement serialize(final StravaSubscriptionAspectType skillLevel, final Type type, final JsonSerializationContext context) {
		return context.serialize(skillLevel.getValue());
	}

}

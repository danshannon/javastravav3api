package javastrava.json.impl.gson.serializer;

import java.lang.reflect.Type;

import javastrava.api.v3.model.reference.StravaPhotoSource;

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
public class StravaPhotoSourceSerializer implements JsonSerializer<StravaPhotoSource>, JsonDeserializer<StravaPhotoSource> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaPhotoSource deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		final StravaPhotoSource photoSource = StravaPhotoSource.create(Integer.valueOf(json.getAsInt()));
		return photoSource;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaPhotoSource activityType, final Type type, final JsonSerializationContext context) {
		return context.serialize(activityType.getValue());
	}

}

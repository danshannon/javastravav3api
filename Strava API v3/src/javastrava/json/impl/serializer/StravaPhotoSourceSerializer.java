package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaPhotoSource;

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
		StravaPhotoSource photoSource = null;
		try {
			photoSource = StravaPhotoSource.create(Integer.valueOf(json.getAsInt()));
		} catch (NumberFormatException e) {
			throw new JsonParseException(e);
		}
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

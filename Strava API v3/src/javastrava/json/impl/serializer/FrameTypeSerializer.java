package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import javastrava.config.Messages;
import javastrava.model.reference.StravaFrameType;

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
public class FrameTypeSerializer implements JsonSerializer<StravaFrameType>, JsonDeserializer<StravaFrameType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaFrameType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		try {
			return StravaFrameType.create(Integer.valueOf(json.getAsInt()));
		} catch (NumberFormatException e) {
			throw new JsonParseException(String.format(Messages.string("JsonUtilImpl.couldNotDeserialiseInteger"), json.getAsString()), e); //$NON-NLS-1$
		}
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaFrameType frameType, final Type type, final JsonSerializationContext context) {
		return context.serialize(frameType.getValue());
	}

}

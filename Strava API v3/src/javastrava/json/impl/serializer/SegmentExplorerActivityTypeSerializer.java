package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaSegmentExplorerActivityType;

/**
 * @author Dan Shannon
 *
 */
public class SegmentExplorerActivityTypeSerializer implements JsonSerializer<StravaSegmentExplorerActivityType>,
		JsonDeserializer<StravaSegmentExplorerActivityType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaSegmentExplorerActivityType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		return StravaSegmentExplorerActivityType.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaSegmentExplorerActivityType segmentExplorerActivityType, final Type type,
			final JsonSerializationContext context) {
		return context.serialize(segmentExplorerActivityType.getValue());
	}

}

/**
 *
 */
package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaEventFrequency;

/**
 * @author Dan Shannon
 *
 */
public class EventFrequencySerializer implements JsonSerializer<StravaEventFrequency>, JsonDeserializer<StravaEventFrequency> {

	@Override
	public StravaEventFrequency deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		final StravaEventFrequency frequency = StravaEventFrequency.create(json.getAsString());
		return frequency;
	}

	@Override
	public JsonElement serialize(StravaEventFrequency frequency, Type type, JsonSerializationContext context) {
		return context.serialize(frequency.getValue());
	}

}

package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaRunningRaceType;

/**
 * Bespoke JSON serializer for {@link StravaRunningRaceType}
 *
 * @author Dan Shannon
 *
 */
public class RunningRaceTypeSerializer implements JsonSerializer<StravaRunningRaceType>, JsonDeserializer<StravaRunningRaceType> {

	@Override
	public StravaRunningRaceType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			final StravaRunningRaceType raceType = StravaRunningRaceType.create(Integer.valueOf(json.getAsInt()));
			return raceType;
		} catch (final NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(StravaRunningRaceType raceType, Type type, JsonSerializationContext context) {
		return context.serialize(raceType.getValue());
	}

}

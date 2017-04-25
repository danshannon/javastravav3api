package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaTerrainType;

/**
 * Bespoke enum serialiser for StravaTerrainType
 *
 * @author Dan Shannon
 *
 */
public class TerrainTypeSerializer implements JsonSerializer<StravaTerrainType>, JsonDeserializer<StravaTerrainType> {
	@Override
	public StravaTerrainType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			final StravaTerrainType terrainType = StravaTerrainType.create(Integer.valueOf(json.getAsInt()));
			return terrainType;
		} catch (final NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(StravaTerrainType terrainType, Type type, JsonSerializationContext context) {
		return context.serialize(terrainType.getValue());
	}

}

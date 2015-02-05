package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamType;
import stravajava.model.MapPoint;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author dshannon
 *
 */
public class StravaStreamSerializer implements JsonSerializer<StravaStream>, JsonDeserializer<StravaStream> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaStream deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject json = (JsonObject) element;
		
		// Get the type, as that will determine what the deserialization should do
		StravaStreamType streamType = StravaStreamType.valueOf(json.get("type").getAsString());
		
		JsonArray array = json.getAsJsonArray("data");
		List<MapPoint> points = null;
		List<Float> data = null;
		if (streamType == StravaStreamType.MAPPOINT) {
			// TODO deserialize the data array as map points
			points = Arrays.asList(context.deserialize(array, MapPoint[].class));
		} else {
			data = Arrays.asList(context.deserialize(array, Float[].class)); 
		}
		
		return null;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaStream stream, Type type, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}

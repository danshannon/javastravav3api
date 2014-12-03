package com.danshannon.strava.util.impl.gson.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.danshannon.strava.api.model.MapPoint;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author dshannon
 *
 */
public class MapPointSerializer implements JsonDeserializer<MapPoint>, JsonSerializer<MapPoint> {

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(MapPoint point, Type type, JsonSerializationContext context) {
		ArrayList<Float> list = new ArrayList<Float>();
		list.add(point.getLatitude());
		list.add(point.getLongitude());
		return context.serialize(list);
	}

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public MapPoint deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonArray array = element.getAsJsonArray();
		return new MapPoint(array.get(0).getAsFloat(), array.get(1).getAsFloat());
	}

}

package javastrava.json.impl.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.StravaMapPoint;

/**
 * @author Dan Shannon
 *
 */
public class MapPointSerializer implements JsonDeserializer<StravaMapPoint>, JsonSerializer<StravaMapPoint> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaMapPoint deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonArray array = element.getAsJsonArray();
		return new StravaMapPoint(Float.valueOf(array.get(0).getAsFloat()), Float.valueOf(array.get(1).getAsFloat()));
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaMapPoint point, final Type type, final JsonSerializationContext context) {
		ArrayList<Float> list = new ArrayList<Float>();
		list.add(point.getLatitude());
		list.add(point.getLongitude());
		return context.serialize(list);
	}

}

package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import stravajava.api.v3.model.StravaMapPoint;
import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;

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
		JsonObject json = null;
		try {
			json = (JsonObject) element;
		} catch (ClassCastException e) { // happens if it's a primitive, or if it's crap data
			throw new JsonParseException(e);
		}

		// Get the type, as that will determine what the deserialization should do
		StravaStreamType streamType = (StravaStreamType) context.deserialize(json.get("type"), StravaStreamType.class);

		JsonArray array = json.getAsJsonArray("data");
		List<StravaMapPoint> points = null;
		List<Float> data = null;
		List<Boolean> moving = null;
		if (streamType == StravaStreamType.MAPPOINT) {
			points = new ArrayList<StravaMapPoint>();
			for (JsonElement arrayElement : array) {
				Float latitude = arrayElement.getAsJsonArray().get(0).getAsFloat();
				Float longitude = arrayElement.getAsJsonArray().get(1).getAsFloat();
				StravaMapPoint point = new StravaMapPoint(latitude, longitude);
				points.add(point);
			}
		} else if (streamType == StravaStreamType.MOVING) {
			moving = new ArrayList<Boolean>();
			for (JsonElement arrayElement : array) {
				Boolean bool = arrayElement.getAsBoolean();
				moving.add(bool);
			}
		} else {
			data = new ArrayList<Float>();
			for (JsonElement arrayElement : array) {
				Float dataElement = arrayElement.getAsFloat();
				data.add(dataElement);
			}
		}

		StravaStream stream = new StravaStream();
		stream.setData(data);
		stream.setMapPoints(points);
		stream.setMoving(moving);
		stream.setOriginalSize(json.get("original_size").getAsInt());
		stream.setResolution((StravaStreamResolutionType) context.deserialize(json.get("resolution"), StravaStreamResolutionType.class));
		stream.setSeriesType((StravaStreamSeriesDownsamplingType) context.deserialize(json.get("series_type"), StravaStreamSeriesDownsamplingType.class));
		stream.setType(streamType);
		return stream;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaStream stream, Type type, JsonSerializationContext context) {
		JsonObject element = new JsonObject();
		element.add("original_size", context.serialize(stream.getOriginalSize()));
		element.add("resolution", context.serialize(stream.getResolution()));
		element.add("series_type", context.serialize(stream.getSeriesType()));
		element.add("type", context.serialize(stream.getType()));
		JsonArray dataArray = new JsonArray();
		element.add("data", dataArray);
		if (stream.getType() == StravaStreamType.MAPPOINT) {
			for (StravaMapPoint point : stream.getMapPoints()) {
				dataArray.add(context.serialize(point));
			}
		} else if (stream.getType() == StravaStreamType.MOVING) {
			for (Boolean moving : stream.getMoving()) {
				dataArray.add(context.serialize(moving));
			}
		} else {
			for (Number number : stream.getData()) {
				dataArray.add(context.serialize(number));
			}
		}
		return element;
	}

}

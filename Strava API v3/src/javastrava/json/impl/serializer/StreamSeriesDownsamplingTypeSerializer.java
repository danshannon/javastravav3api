package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaStreamSeriesDownsamplingType;

/**
 * @author Dan Shannon
 *
 */
public class StreamSeriesDownsamplingTypeSerializer implements JsonSerializer<StravaStreamSeriesDownsamplingType>,
		JsonDeserializer<StravaStreamSeriesDownsamplingType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaStreamSeriesDownsamplingType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		return StravaStreamSeriesDownsamplingType.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaStreamSeriesDownsamplingType downsamplingType, final Type type, final JsonSerializationContext context) {
		return context.serialize(downsamplingType.getValue());
	}

}

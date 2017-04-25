package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaWeightClass;

/**
 * @author Dan Shannon
 *
 */
public class WeightClassSerializer implements JsonSerializer<StravaWeightClass>, JsonDeserializer<StravaWeightClass> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaWeightClass deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		return StravaWeightClass.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaWeightClass weightClass, final Type type, final JsonSerializationContext context) {
		return context.serialize(weightClass.getValue());
	}

}

package com.danshannon.strava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import com.danshannon.strava.api.model.reference.WeightClass;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Dan Shannon
 *
 */
public class WeightClassSerializer implements JsonSerializer<WeightClass>, JsonDeserializer<WeightClass>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public WeightClass deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		return WeightClass.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(WeightClass weightClass, Type type, JsonSerializationContext context) {
		return context.serialize(weightClass.getValue());
	}

}

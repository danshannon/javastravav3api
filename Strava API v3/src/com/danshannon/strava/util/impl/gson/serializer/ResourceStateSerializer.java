package com.danshannon.strava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import com.danshannon.strava.api.model.reference.ResourceState;
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
public class ResourceStateSerializer implements JsonSerializer<ResourceState>, JsonDeserializer<ResourceState>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public ResourceState deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) { return null; }
		return ResourceState.create(json.getAsInt());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(ResourceState resourceState, Type type, JsonSerializationContext context) {
		if (resourceState == null) { return null; }
		return context.serialize(resourceState.getValue());
	}

}

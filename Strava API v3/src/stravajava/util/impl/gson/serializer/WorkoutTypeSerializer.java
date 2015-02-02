package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.model.reference.StravaWorkoutType;

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
public class WorkoutTypeSerializer implements JsonSerializer<StravaWorkoutType>, JsonDeserializer<StravaWorkoutType>{

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaWorkoutType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		return (json == null ? null : StravaWorkoutType.create(json.getAsInt()));
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaWorkoutType workoutType, Type type, JsonSerializationContext context) {
		return (workoutType == null ? null : context.serialize(workoutType.getValue()));
	}

}

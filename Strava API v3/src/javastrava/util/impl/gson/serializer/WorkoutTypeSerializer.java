package javastrava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import javastrava.api.v3.model.reference.StravaWorkoutType;

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
public class WorkoutTypeSerializer implements JsonSerializer<StravaWorkoutType>, JsonDeserializer<StravaWorkoutType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaWorkoutType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		try {
			return StravaWorkoutType.create(json.getAsInt());
		} catch (NumberFormatException e) {
			throw new JsonParseException("Could not parse '" + json.getAsString() + "' as an integer", e);
		}
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaWorkoutType workoutType, final Type type, final JsonSerializationContext context) {
		return context.serialize(workoutType.getValue());
	}

}

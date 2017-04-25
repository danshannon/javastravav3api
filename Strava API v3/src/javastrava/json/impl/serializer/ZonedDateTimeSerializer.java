package javastrava.json.impl.serializer;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Dan Shannon
 *
 */
public class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public ZonedDateTime deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		return ZonedDateTime.parse(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final ZonedDateTime src, final Type srcType, final JsonSerializationContext context) {
		return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME));
	}

}

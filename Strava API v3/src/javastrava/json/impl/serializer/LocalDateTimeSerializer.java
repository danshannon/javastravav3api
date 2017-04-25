package javastrava.json.impl.serializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public LocalDateTime deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_DATE_TIME);
		// return LocalDateTime.ofInstant(Instant.parse(json.getAsString()), ZoneOffset.UTC);
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final LocalDateTime src, final Type srcType, final JsonSerializationContext context) {
		return new JsonPrimitive(src.atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME));
	}

}

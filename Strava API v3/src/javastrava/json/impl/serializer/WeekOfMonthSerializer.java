package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.reference.StravaWeekOfMonth;

/**
 * Bespoke enum serialiser for {@link StravaWeekOfMonth}
 *
 * @author Dan Shannon
 *
 */
public class WeekOfMonthSerializer implements JsonSerializer<StravaWeekOfMonth>, JsonDeserializer<StravaWeekOfMonth> {

	@Override
	public StravaWeekOfMonth deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			final StravaWeekOfMonth week = StravaWeekOfMonth.create(Integer.valueOf(json.getAsInt()));
			return week;
		} catch (final NumberFormatException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(StravaWeekOfMonth week, Type type, JsonSerializationContext context) {
		return context.serialize(week.getValue());
	}

}

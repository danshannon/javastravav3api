package javastrava.json.impl.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.api.v3.model.reference.StravaClubMembershipStatus;

public class ClubMembershipStatusSerializer
		implements JsonSerializer<StravaClubMembershipStatus>, JsonDeserializer<StravaClubMembershipStatus> {

	@Override
	public StravaClubMembershipStatus deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		StravaClubMembershipStatus status = StravaClubMembershipStatus.create(json.getAsString());
		return status;
	}

	@Override
	public JsonElement serialize(StravaClubMembershipStatus status, Type type, JsonSerializationContext context) {
		return context.serialize(status.getValue());
	}

}

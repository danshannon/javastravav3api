package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import stravajava.api.v3.auth.ref.AuthorisationResponseType;

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
public class AuthorisationResponseTypeSerializer implements JsonSerializer<AuthorisationResponseType>, JsonDeserializer<AuthorisationResponseType>{
	
	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public AuthorisationResponseType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		if (json == null) { return null; }
		return AuthorisationResponseType.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(AuthorisationResponseType authorisationResponseType, Type type, JsonSerializationContext context) {
		if (authorisationResponseType == null) { return null; }
		return context.serialize(authorisationResponseType.getId());
	}

}

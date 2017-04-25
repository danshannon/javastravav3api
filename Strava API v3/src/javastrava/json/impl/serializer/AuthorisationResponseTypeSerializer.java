package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.auth.ref.AuthorisationResponseType;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationResponseTypeSerializer implements JsonSerializer<AuthorisationResponseType>,
		JsonDeserializer<AuthorisationResponseType> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public AuthorisationResponseType deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		return AuthorisationResponseType.create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final AuthorisationResponseType authorisationResponseType, final Type type, final JsonSerializationContext context) {
		return context.serialize(authorisationResponseType.getId());
	}

}

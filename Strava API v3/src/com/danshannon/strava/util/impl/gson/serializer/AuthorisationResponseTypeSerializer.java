package com.danshannon.strava.util.impl.gson.serializer;

import java.lang.reflect.Type;

import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
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
	 * Required by GSON serialiser
	 * @param id The String value of the id
	 * @return An instance of {@link AuthorisationResponseType} corresponding to the id, or {@link #UNKNOWN} if no such instance is available.
	 */
	public static AuthorisationResponseType create(String id) {
		for (AuthorisationResponseType type : AuthorisationResponseType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return AuthorisationResponseType.UNKNOWN;
	}
	
	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public AuthorisationResponseType deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		return create(json.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(AuthorisationResponseType authorisationResponseType, Type type, JsonSerializationContext context) {
		return context.serialize(authorisationResponseType.getId());
	}

}

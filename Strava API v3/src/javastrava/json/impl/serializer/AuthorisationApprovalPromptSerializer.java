package javastrava.json.impl.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.auth.ref.AuthorisationApprovalPrompt;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationApprovalPromptSerializer implements JsonSerializer<AuthorisationApprovalPrompt>,
		JsonDeserializer<AuthorisationApprovalPrompt> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public AuthorisationApprovalPrompt deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context)
			throws JsonParseException {
		return AuthorisationApprovalPrompt.create(element.getAsString());
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final AuthorisationApprovalPrompt prompt, final Type type, final JsonSerializationContext context) {
		return context.serialize(prompt.getId());
	}

}

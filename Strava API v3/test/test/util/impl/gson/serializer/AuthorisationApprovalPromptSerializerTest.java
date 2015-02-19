package test.util.impl.gson.serializer;

import javastrava.api.v3.auth.ref.AuthorisationApprovalPrompt;

/**
 * @author dshannon
 *
 */
public class AuthorisationApprovalPromptSerializerTest extends EnumSerializerTest<AuthorisationApprovalPrompt> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected AuthorisationApprovalPrompt getUnknownValue() {
		return AuthorisationApprovalPrompt.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<AuthorisationApprovalPrompt> getClassUnderTest() {
		return AuthorisationApprovalPrompt.class;
	}

}

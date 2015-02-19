package test.util.impl.gson.serializer;

import javastrava.api.v3.auth.ref.AuthorisationResponseType;

/**
 * @author dshannon
 *
 */
public class AuthorisationResponseTypeSerializerTest extends EnumSerializerTest<AuthorisationResponseType> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected AuthorisationResponseType getUnknownValue() {
		return AuthorisationResponseType.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<AuthorisationResponseType> getClassUnderTest() {
		return AuthorisationResponseType.class;
	}
}

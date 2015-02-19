package test.util.impl.gson.serializer;

import javastrava.api.v3.auth.ref.AuthorisationScope;

/**
 * @author dshannon
 *
 */
public class AuthorisationScopeSerializerTest extends EnumSerializerTest<AuthorisationScope> {

	/**
	 * @see test.util.impl.gson.serializer.EnumSerializerTest#getUnknownValue()
	 */
	@Override
	protected AuthorisationScope getUnknownValue() {
		return AuthorisationScope.UNKNOWN;
	}

	/**
	 * @see test.util.impl.gson.serializer.SerializerTest#getClassUnderTest()
	 */
	@Override
	public Class<AuthorisationScope> getClassUnderTest() {
		return AuthorisationScope.class;
	}
}

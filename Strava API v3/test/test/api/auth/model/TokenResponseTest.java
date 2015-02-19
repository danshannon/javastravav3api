package test.api.auth.model;

import javastrava.api.v3.auth.model.TokenResponse;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class TokenResponseTest extends BeanTest<TokenResponse> {

	@Override
	protected Class<TokenResponse> getClassUnderTest() {
		return TokenResponse.class;
	}
}

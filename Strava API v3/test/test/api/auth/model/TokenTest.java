package test.api.auth.model;

import javastrava.api.v3.auth.model.Token;
import test.utils.BeanTest;

public class TokenTest extends BeanTest<Token> {

	@Override
	protected Class<Token> getClassUnderTest() {
		return Token.class;
	}

}

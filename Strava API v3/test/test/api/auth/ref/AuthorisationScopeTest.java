package test.api.auth.ref;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import stravajava.api.v3.auth.ref.AuthorisationScope;

/**
 * @author dshannon
 *
 */
public class AuthorisationScopeTest {
	@Test
	public void testGetId() {
		for (AuthorisationScope type : AuthorisationScope.values()) {
			assertNotNull(type.getId());
			assertEquals(type, AuthorisationScope.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (AuthorisationScope type : AuthorisationScope.values()) {
			assertNotNull(type.getDescription());
		}
	}

}

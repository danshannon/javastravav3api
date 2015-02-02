package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaComment;

/**
 * @author dshannon
 *
 */
public class CommentTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaComment.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaComment.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

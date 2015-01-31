package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.Comment;

/**
 * @author dshannon
 *
 */
public class CommentTest {

	@Test
	public void test() {
		new BeanTester().testBean(Comment.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(Comment.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

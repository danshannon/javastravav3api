package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaSplit;

/**
 * @author dshannon
 *
 */
public class SplitTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaSplit.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaSplit.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaSegmentExplorerResponse;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaSegmentExplorerResponse.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaSegmentExplorerResponse.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

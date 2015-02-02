package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import stravajava.api.v3.model.StravaSegmentExplorerResponseSegment;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerSegmentTest {

	@Test
	public void test() {
		new BeanTester().testBean(StravaSegmentExplorerResponseSegment.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(StravaSegmentExplorerResponseSegment.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

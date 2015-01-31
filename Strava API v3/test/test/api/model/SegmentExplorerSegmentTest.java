package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.SegmentExplorerSegment;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerSegmentTest {

	@Test
	public void test() {
		new BeanTester().testBean(SegmentExplorerSegment.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(SegmentExplorerSegment.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

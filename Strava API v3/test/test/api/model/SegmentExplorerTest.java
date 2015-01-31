package test.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.danshannon.strava.api.model.SegmentExplorer;

/**
 * @author dshannon
 *
 */
public class SegmentExplorerTest {

	@Test
	public void test() {
		new BeanTester().testBean(SegmentExplorer.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifier.forClass(SegmentExplorer.class).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

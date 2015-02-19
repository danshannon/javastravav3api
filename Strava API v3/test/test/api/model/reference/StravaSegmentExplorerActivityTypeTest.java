package test.api.model.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;

import org.junit.Test;

/**
 * @author dshannon
 *
 */
public class StravaSegmentExplorerActivityTypeTest {
	@Test
	public void testGetId() {
		for (StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
			assertNotNull(type.getId());
			assertEquals(type, StravaSegmentExplorerActivityType.create(type.getId()));
		}
	}

	@Test
	public void testGetDescription() {
		for (StravaSegmentExplorerActivityType type : StravaSegmentExplorerActivityType.values()) {
			assertNotNull(type.getDescription());
		}
	}

}

package test.utils.equalsverifier;

import javastrava.api.v3.model.StravaSegment;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import test.utils.meanbean.factory.StravaSegmentFactory;

public class EqualsVerifierUtil {
	public static <T> void testClass(Class<T> class1) {
		EqualsVerifier.forClass(class1).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
	}

	public static <T> void testSubclass(Class<T> class1) {
		StravaSegmentFactory factory = new StravaSegmentFactory();
		StravaSegment seg1 = factory.create();
		StravaSegment seg2 = factory.create();
		seg2.setId(2);
		EqualsVerifier.forClass(class1).withRedefinedSuperclass().withPrefabValues(StravaSegment.class, seg1, seg2)
				.suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
	}
}

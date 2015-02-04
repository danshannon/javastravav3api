package test.utils.equalsverifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class EqualsVerifierUtil {
	public static <T> void testClass(Class<T> class1) {
		EqualsVerifier.forClass(class1).suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
	
	public static <T> void testSubclass(Class<T> class1) {
		EqualsVerifier.forClass(class1).withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE,Warning.NONFINAL_FIELDS).verify();
	}
}

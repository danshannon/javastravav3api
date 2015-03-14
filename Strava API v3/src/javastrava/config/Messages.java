package javastrava.config;

import java.util.ResourceBundle;

/**
 * Internationalisable messages
 * @author Dan Shannon
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	/**
	 * Get the value of a property in the resource bundle
	 * @param key Name of the property
	 * @return The value of the property
	 */
	public static String getString(final String key) {
		return RESOURCE_BUNDLE.getString(key);
		
	}
}

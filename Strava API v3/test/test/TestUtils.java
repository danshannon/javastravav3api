package test;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author dshannon
 *
 */
public class TestUtils {
	public static Properties loadPropertiesFile(String file) throws IOException {
		Properties properties = new Properties();
		URL url = ActivityServicesImplTest.class.getClassLoader().getResource(file); 
		properties.load(url.openStream());			
		return properties;
	}
}

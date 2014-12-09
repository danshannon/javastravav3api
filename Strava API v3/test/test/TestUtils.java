package test;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.reference.ActivityType;

/**
 * @author Dan Shannon
 *
 */
public class TestUtils {
	public static Properties loadPropertiesFile(String file) throws IOException {
		Properties properties = new Properties();
		URL url = ActivityServicesImplTest.class.getClassLoader().getResource(file); 
		properties.load(url.openStream());			
		return properties;
	}

	/**
	 * @return
	 */
	public static Activity createDefaultActivityForCreation() {
		Activity activity = new Activity();
		activity.setName("TO BE DELETED");
		activity.setType(ActivityType.RIDE);
		activity.setStartDateLocal(new Date());
		activity.setElapsedTime(1000);
		activity.setDescription("Created by Strava API v3 Java");
		activity.setDistance(1000.1F);
		return activity;
	}
}

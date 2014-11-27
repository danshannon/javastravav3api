package com.danshannon.strava.api.service.impl.retrofit;

import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.service.ActivityServices;

/**
 * @author dshannon
 *
 */
public class ActivityServicesImplTest {
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetActivity() {
		String token = "41267b80282a3212eb7e8393276185513bf29647";
		Integer id = 0;
		Boolean includeAllEfforts = null;
		
		// Get the service for the token
		ActivityServices service = ActivityServicesImpl.implementation(token);
		
		// Test with a bad activity
		Activity activity = service.getActivity(id, includeAllEfforts);
		assertNull("Invalid activity id should return a null activity", activity); 
	}

}

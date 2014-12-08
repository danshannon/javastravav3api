/**
 * 
 */
package test;

import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.service.impl.retrofit.AthleteServicesImpl;

/**
 * <p>Unit tests for {@link AthleteServicesImpl}</p>
 * 
 * @author Dan Shannon
 *
 */
public class AthleteServicesImplTest {
	private static String VALID_TOKEN; 
	private static String INVALID_TOKEN;
	private static String VALID_TOKEN_WITHOUT_WRITE_ACCESS;
	private static final String PROPERTIES_FILE = "test-config.properties";

	/**
	 * <p>Loads the properties from the test configuration file</p>
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties properties = TestUtils.loadPropertiesFile(PROPERTIES_FILE);
		VALID_TOKEN = properties.getProperty("test.activityServicesImplTest.validToken");
		INVALID_TOKEN = properties.getProperty("test.activityServicesImplTest.invalidToken");
		VALID_TOKEN_WITHOUT_WRITE_ACCESS = properties.getProperty("test.activityServicesImplTest.validTokenWithoutWriteAccess");
	}
	
	@Test
	public void testImplementation_validToken(String token) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testImplementation_invalidToken(String token) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testImplementation_revokedToken(String token) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testImplementation_implementationIsCached(String token) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testImplementation_differentImplementationIsNotCached(String token) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAuthenticatedAthlete() {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAthlete_validAthlete(Integer id) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAthlete_invalidAthlete(Integer id) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAthlete_privateAthlete(Integer id) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateAuthenticatedAthlete(String city, String state, String country, Gender sex, Float weight) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Valid athlete with some KOM's
	// 2. Valid athlete with no KOM's
	// 2. Invalid athlete
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListAthleteKOMs(Integer id, Integer page, Integer perPage) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Some friends (at least 2)
	// 2. No friends
	// 3. Paging - size only (including test for max page size)
	// 4. Paging - size and page
	// 5. Paging - out of range high
	// 6. Paging - out of range low
	@Test
	public void testListAuthenticatedAthleteFriends(Integer page, Integer perPage) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}
	
	// Test cases
	// 1. Valid athlete - at least 2 friends
	// 2. Invalid athlete
	// 3. Valid athlete - no friends 
	// 4. Paging - size only (including test for max page size)
	// 5. Paging - size and page
	// 6. Paging - out of range high
	// 7. Paging - out of range low
	@Test
	public void testListAthleteFriends(Integer id, Integer page, Integer perPage) {
		// TODO Not yet implemented
		fail("Not yet implemented");
	}

	// Test cases
	// 1. Valid athlete - at least 1 common friend
	// 2. Invalid other athlete
	// 3. Valid athlete - no common friends
	// 4. Paging - size only (including test for max page size)
	// 5. Paging - size and page
	// 6. Paging - out of range high
	// 7. Paging - out of range low
	@Test
	public void testListAthletesBothFollowing(Integer id, Integer page, Integer perPage) {
		// TODO Not yet implemented
		fail("Not yet implemented");		
	}
}

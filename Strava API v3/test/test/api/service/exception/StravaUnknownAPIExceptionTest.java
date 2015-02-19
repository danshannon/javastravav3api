package test.api.service.exception;

import static org.junit.Assert.assertEquals;
import javastrava.api.v3.model.StravaResponse;
import javastrava.api.v3.service.exception.StravaUnknownAPIException;

import org.junit.Test;

public class StravaUnknownAPIExceptionTest {

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor_normal() {
		StravaUnknownAPIException e = new StravaUnknownAPIException("Test",new StravaResponse(),new IllegalArgumentException());
		try {
			throw e;
		} catch (StravaUnknownAPIException ex) {
			// Expected
			return;
		}
	}
	
	@Test
	public void testConstructor_nullSafety() {
		new StravaUnknownAPIException(null, null, null);
	}
	
	@Test
	public void testGetSetResponse() {
		StravaResponse response = new StravaResponse();
		response.setMessage("Test");
		StravaUnknownAPIException e = new StravaUnknownAPIException("Test",new StravaResponse(),null);
		e.setResponse(response);
		StravaResponse testResponse = e.getResponse();
		assertEquals(response,testResponse);
	}
	
}

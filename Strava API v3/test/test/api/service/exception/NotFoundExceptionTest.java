package test.api.service.exception;

import static org.junit.Assert.assertEquals;
import javastrava.api.v3.model.StravaResponse;
import javastrava.api.v3.service.exception.NotFoundException;

import org.junit.Test;

public class NotFoundExceptionTest {

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor_normal() {
		NotFoundException e = new NotFoundException(new StravaResponse(),new IllegalArgumentException());
		try {
			throw e;
		} catch (NotFoundException ex) {
			// Expected
			return;
		}
	}
	
	@Test
	public void testConstructor_nullSafety() {
		new NotFoundException(null, null);
	}
	
	@Test
	public void testGetSetResponse() {
		StravaResponse response = new StravaResponse();
		response.setMessage("Test");
		NotFoundException e = new NotFoundException(new StravaResponse(),null);
		e.setResponse(response);
		StravaResponse testResponse = e.getResponse();
		assertEquals(response,testResponse);
	}

}

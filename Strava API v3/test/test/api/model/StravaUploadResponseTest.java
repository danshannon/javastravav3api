package test.api.model;

import javastrava.api.v3.model.StravaUploadResponse;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaUploadResponseTest extends BeanTest<StravaUploadResponse> {

	@Override
	protected Class<StravaUploadResponse> getClassUnderTest() {
		return StravaUploadResponse.class;
	}
}

package test.api.model;

import javastrava.api.v3.model.StravaComment;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaCommentTest extends BeanTest<StravaComment> {

	@Override
	protected Class<StravaComment> getClassUnderTest() {
		return StravaComment.class;
	}
}

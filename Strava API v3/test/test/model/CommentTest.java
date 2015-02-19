package test.model;

import javastrava.model.Comment;
import test.utils.BeanTest;

public class CommentTest extends BeanTest<Comment> {

	@Override
	protected Class<Comment> getClassUnderTest() {
		return Comment.class;
	}

}

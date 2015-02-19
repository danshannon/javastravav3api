package test.model;

import javastrava.model.Photo;
import test.utils.BeanTest;

public class PhotoTest extends BeanTest<Photo> {

	@Override
	protected Class<Photo> getClassUnderTest() {
		return Photo.class;
	}

}

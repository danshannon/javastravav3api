package test.utils;

import org.junit.Test;

import test.utils.equalsverifier.EqualsVerifierUtil;
import test.utils.meanbean.MeanBean;

public abstract class BeanTest<T> {
	protected abstract Class<T> getClassUnderTest();

	@Test
	public void testGettersAndSetters() {
		MeanBean.testBean(getClassUnderTest());
	}

	@Test
	public void testEqualsMethod() {
		EqualsVerifierUtil.testSubclass(getClassUnderTest());
	}
}

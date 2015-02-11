package test.api.model;

import stravajava.api.v3.model.StravaSplit;
import test.utils.BeanTest;

/**
 * @author dshannon
 *
 */
public class StravaSplitTest extends BeanTest<StravaSplit> {

	@Override
	protected Class<StravaSplit> getClassUnderTest() {
		return StravaSplit.class;
	}
}

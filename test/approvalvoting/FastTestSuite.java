package approvalvoting;

import approvalvoting.client.ChoiceTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/** This test suite should only include non-browser tests. */
public class FastTestSuite extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Fast tests");
		suite.addTestSuite(ChoiceTest.class);
		return suite;
	}
}

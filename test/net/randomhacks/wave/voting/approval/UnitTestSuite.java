package net.randomhacks.wave.voting.approval;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.randomhacks.wave.voting.approval.client.ChoiceTest;
import net.randomhacks.wave.voting.approval.client.ChoicesModelTest;

/** This test suite should only include non-browser tests. */
public class UnitTestSuite extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Fast tests");
		suite.addTestSuite(ChoiceTest.class);
		suite.addTestSuite(ChoicesModelTest.class);
		return suite;
	}
}

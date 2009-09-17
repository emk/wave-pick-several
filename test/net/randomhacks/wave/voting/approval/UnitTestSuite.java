package net.randomhacks.wave.voting.approval;

import net.randomhacks.wave.voting.approval.client.ChoiceTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/** This test suite should only include non-browser tests. */
public class UnitTestSuite extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Fast tests");
		suite.addTestSuite(ChoiceTest.class);
		return suite;
	}
}

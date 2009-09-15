package approvalvoting;

import junit.framework.Test;
import junit.framework.TestSuite;

import approvalvoting.client.ApprovalVotingTest;

import com.google.gwt.junit.tools.GWTTestSuite;

public class ApprovalVotingTestSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ApprovalVoting gadget");
		//suite.addTestSuite(FastTestSuite.class);
		suite.addTestSuite(ApprovalVotingTest.class);
		return suite;
	}
}

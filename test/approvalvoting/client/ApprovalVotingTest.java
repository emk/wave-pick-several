package approvalvoting.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class ApprovalVotingTest extends GWTTestCase {

	/** Must refer to a valid module that sources this class. */
	public String getModuleName() {
		return "approvalvoting.ApprovalVoting";
	}

	public void testShouldBeLaunchable() {
		ApprovalVoting app = new ApprovalVoting(); 
		app.onModuleLoad();
	}

}

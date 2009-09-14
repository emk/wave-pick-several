package approvalvoting.client;

import com.google.gwt.core.client.EntryPoint;

public class ApprovalVoting implements EntryPoint {
		
	public void onModuleLoad() {
		ChoicesModel model = new ChoicesModel();
		new ChoicesTable(model);
	}
}

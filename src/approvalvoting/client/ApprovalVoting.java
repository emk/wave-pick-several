package approvalvoting.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ApprovalVoting implements EntryPoint {
		
	public void onModuleLoad() {
		ChoicesModel model = new ChoicesModel();
		RootPanel.get().add(new ChoicesTable(model));
	}
}

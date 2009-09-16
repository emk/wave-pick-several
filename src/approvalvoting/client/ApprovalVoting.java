package approvalvoting.client;

import com.google.gwt.gadgets.client.DynamicHeightFeature;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.NeedsDynamicHeight;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;

@ModulePrefs(
	title = "Approval Voting",
	author = "Eric Kidd",
	author_email = "eric.kidd@gmail.com")
public class ApprovalVoting extends Gadget<UserPreferences> implements NeedsDynamicHeight {
	ChoicesTable table;
	DynamicHeightFeature dynamicHeightFeature;

	public void initializeFeature(DynamicHeightFeature feature) {
		dynamicHeightFeature = feature;
	}

	@Override
	protected void init(UserPreferences preferences) {
		ChoicesModel model = new ChoicesModel();
		table = new ChoicesTable(model);
		dynamicHeightFeature.getContentDiv().add(table);
		table.setDynamicHeightFeature(dynamicHeightFeature);
	}

}

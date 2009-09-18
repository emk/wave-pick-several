package net.randomhacks.wave.voting.approval.client;

import net.randomhacks.wave.gadgets.client.NeedsWave;
import net.randomhacks.wave.gadgets.client.WaveFeature;

import com.google.gwt.gadgets.client.DynamicHeightFeature;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.NeedsDynamicHeight;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;

@ModulePrefs(
	title = "Approval Voting",
	author = "Eric Kidd",
	author_email = "eric.kidd@gmail.com")
public class ApprovalVoting extends Gadget<UserPreferences>
	implements NeedsDynamicHeight, NeedsWave
{
	DynamicHeightFeature dynamicHeightFeature;
	WaveFeature waveFeature;
	ChoicesTable table;
	
	public void initializeFeature(DynamicHeightFeature feature) {
		dynamicHeightFeature = feature;
	}

	public void initializeFeature(WaveFeature feature) {
		waveFeature = feature;
	}

	@Override
	protected void init(UserPreferences preferences) {
		// Initialize our model.
		ChoicesModel model = new ChoicesModel(waveFeature.getState());
		waveFeature.addStateListener(model);
		
		// Initialize our GUI.
		table = new ChoicesTable(model);
		dynamicHeightFeature.getContentDiv().add(table);
		table.setDynamicHeightFeature(dynamicHeightFeature);
	}
}

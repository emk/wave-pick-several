// PickSeveral - Approval voting wave gadget
// Copyright 2009 Eric Kidd
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.randomhacks.wave.voting.approval.client;

import net.randomhacks.wave.gadgets.client.NeedsWave;
import net.randomhacks.wave.gadgets.client.Wave;
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
	Wave wave;
	ChoicesTable table;
	
	public void initializeFeature(DynamicHeightFeature feature) {
		dynamicHeightFeature = feature;
	}

	public void initializeFeature(WaveFeature feature) {
		wave = feature.getWave();
	}

	@Override
	protected void init(UserPreferences preferences) {
		// Initialize our model.
		ChoicesModel model = new ChoicesModel(wave);
		
		// Initialize our GUI.
		table = new ChoicesTable(model);
		dynamicHeightFeature.getContentDiv().add(table);
		table.setDynamicHeightFeature(dynamicHeightFeature);
	}
}

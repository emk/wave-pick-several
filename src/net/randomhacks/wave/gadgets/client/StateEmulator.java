// net.randomhacks.wave.gadgets - Wave gadget library
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

package net.randomhacks.wave.gadgets.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This class is only used for testing gadget implementations. */
public class StateEmulator extends State {
	HashMap<String,String> state = new HashMap<String, String>();

	public String get(String key) {
		return state.get(key);
	}

	public ArrayList<String> getKeys() {
		ArrayList<String> result = new ArrayList<String>();
		for (String key : state.keySet())
			result.add(key);
		return result;
	}

	public void submitDelta(Map<String, String> delta) {
		for (Map.Entry<String, String> entry : delta.entrySet())
			state.put(entry.getKey(), entry.getValue());
	}

	public void submitDelta(String key, String value) {
		state.put(key, value);
	}

	public String escape(String string) {	
		return string.replace("%", "%25").replace(":", "%3A");
	}
}

package net.randomhacks.wave.gadgets.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This class is only used for testing gadget implementations. */
public class StateEmulator implements State {
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

package net.randomhacks.wave.gadgets.client;

import java.util.ArrayList;
import java.util.Map;

public abstract class State {

	/** Get a list of all keys stored in this gadget's state. */
	public abstract ArrayList<String> getKeys();

	/** Get the value associated with the specified key. */
	public abstract String get(String key);

	/** Write a set of key/value pairs to this gadget's state. */
	public abstract void submitDelta(Map<String, String> delta);

	/** Submit a single-item delta to this gadget's state. */
	public abstract void submitDelta(String key, String value);

	/**
	 * Escape the colon and percent characters in a string.  This
	 * makes it easier to build compound keys where the parts are
	 * separated by a colon.
	 */
	public abstract String escape(String string);
}
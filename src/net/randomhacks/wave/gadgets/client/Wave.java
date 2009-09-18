package net.randomhacks.wave.gadgets.client;

public abstract class Wave {
	/** Notified when a change occurs in the gadget's state. */
	public interface StateListener {
		void onStateChange();
	}

	/**
	 * Get the user currently viewing this gadget, or null, if the
	 * user can't be determined.
	 */
	public abstract Participant getViewer();

	/** The state object for this gadget. */
	public abstract State getState();

	/**
	 * Add a new listener which will be called whenever the state changes.
	 */
	public abstract void addStateListener(StateListener listener);
}
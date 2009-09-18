package net.randomhacks.wave.gadgets.client;

import java.util.ArrayList;

import com.google.gwt.gadgets.client.GadgetFeature;

/** Provides access to Wave-specific APIs. */
public class WaveFeature implements GadgetFeature {
	/** Notified when a change occurs in the gadget's state. */
	public interface StateListener {
		void onStateChange(State state);
	}
	
	private boolean callbacksAreInitialized = false;
	private ArrayList<StateListener> stateListeners =
		new ArrayList<StateListener>();

	/**
	 * Get the user currently viewing this gadget, or null, if the
	 * user can't be determined.
	 */
	public native Participant getViewer() /*-{
		return wave.getViewer();
	}-*/;
	
	/** The state object for this gadget. */
	public State getState() {
		return new StateImpl();
	}

	/**
	 * Add a new listener which will be called whenever the state changes.
	 */
	public void addStateListener(StateListener listener) {
		maybeInitializeCallbacks();
		stateListeners.add(listener);
	}

	private void maybeInitializeCallbacks() {
		if (!callbacksAreInitialized) {
			callbacksAreInitialized = true;
			initializeCallbacks();
		}
	}

	private native void initializeCallbacks() /*-{
		wave.setStateCallback(
			this.@net.randomhacks.wave.gadgets.client.WaveFeature::notifyStateListeners(),
			this);
	}-*/;

	@SuppressWarnings("unused")
	private void notifyStateListeners() {
		State state = getState();
		for (StateListener listener : stateListeners)
			listener.onStateChange(state);
	}
}

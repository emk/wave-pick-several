package net.randomhacks.wave.gadgets.client;

public class WaveEmulator extends Wave {
	State state = new StateEmulator();

	@Override
	public void addStateListener(StateListener listener) {
		// Do nothing; we'll notify objects manually.
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public Participant getViewer() {
		return new Participant("viewer@example.com", "Wave Viewer",
				"http://example.com/viewer.jpg");
	}
}

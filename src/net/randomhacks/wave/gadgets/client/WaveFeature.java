package net.randomhacks.wave.gadgets.client;

import com.google.gwt.gadgets.client.GadgetFeature;

/** Passed to a wave-based gadget on startup. */
public class WaveFeature implements GadgetFeature {
	private Wave wave = new WaveImpl();
	
	/** Get the wave associated with this gadget. */
	public Wave getWave() {
		return wave;
	}
}

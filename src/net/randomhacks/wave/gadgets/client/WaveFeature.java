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

import com.google.gwt.gadgets.client.GadgetFeature;

/** Passed to a wave-based gadget on startup. */
public class WaveFeature implements GadgetFeature {
	private Wave wave = new WaveImpl();
	
	/** Get the wave associated with this gadget. */
	public Wave getWave() {
		return wave;
	}
}

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

	@Override
	public boolean isPlayback() {
		return true;
	}
}

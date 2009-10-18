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

	/** Is this a playback of a previously created wave? */
	public abstract boolean isPlayback();
}
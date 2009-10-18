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

import com.google.gwt.core.client.JavaScriptObject;

/** A participant in a wave. */
public class Participant {
	/** Used internally to represent a raw JavaScript participant object. */
	static final class JsParticipant extends JavaScriptObject {
		protected JsParticipant() { }
		
		/** A unique identifier for this participant. */
		public native String getId() /*-{
			return this.getId();
		}-*/;

		/** A human-readable name for this participant. */
		public native String getDisplayName() /*-{
			return this.getDisplayName();
		}-*/;

		/** The URL of a thumbnail image of this participant. */
		public native String getThumbnailUrl() /*-{
			return this.getThumbnailUrl();
		}-*/;
	}
	
	private String id;
	private String displayName;
	private String thumbnailUrl;
	
	/** Create a new Participant. */
	Participant(String id, String displayName, String thumbnailUrl) {
		this.id = id;
		this.displayName = displayName;
		this.thumbnailUrl = thumbnailUrl;
	}

	/** Create a new Participant from a JavaScript object. */
	Participant(JsParticipant participant) {
		this.id = participant.getId();
		this.displayName = participant.getDisplayName();
		this.thumbnailUrl = participant.getThumbnailUrl();
	}

	/** A unique identifier for this participant. */
	public String getId() {
		return id;
	}

	/** A human-readable name for this participant. */
	public String getDisplayName() {
		return displayName;
	}

	/** The URL of a thumbnail image of this participant. */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
}

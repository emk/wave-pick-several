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

package net.randomhacks.wave.gadgets.client;

import com.google.gwt.core.client.JavaScriptObject;

/** A participant in a wave. */
public final class Participant extends JavaScriptObject {
	protected Participant() { }
	
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

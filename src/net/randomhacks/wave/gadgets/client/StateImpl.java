package net.randomhacks.wave.gadgets.client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/** An interface for reading and writing the stored state of a gadget. */
class StateImpl extends State {
	
	// An internal wrapper around $wnd.wave.getState().
	private static final class JsState extends JavaScriptObject {
		@SuppressWarnings("unused")
		protected JsState() {}
		
		native String get(String key) /*-{
			return this.get(key);
		}-*/;
		
		native JsArrayString getKeys() /*-{
		    return this.getKeys();
		}-*/;
		
		native void submitDelta(JavaScriptObject delta) /*-{
			this.submitDelta(delta);
		}-*/;
		
		native void submitDelta(String key, String value) /*-{
			var delta = {};
			delta[key] = value;
			this.submitDelta(delta);
		}-*/;
	}
	
	private native JsState getJsState() /*-{
		return wave.getState();
	}-*/;
	
	public ArrayList<String> getKeys() {
		JsArrayString keys = getJsState().getKeys();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < keys.length(); ++i)
			result.add(keys.get(i));
		return result;
	}
	
	public String get(String key) {
		return getJsState().get(key);
	}
	
	public void submitDelta(Map<String,String> delta) {
		JavaScriptObject jsDelta = JavaScriptObject.createObject();
		for (Map.Entry<String,String> entry : delta.entrySet())
			putKeyValue(jsDelta, entry.getKey(), entry.getValue());
		getJsState().submitDelta(jsDelta);
	}

	private native void putKeyValue(JavaScriptObject jsDelta,
			String key, String value) /*-{
		jsDelta[key] = value;
	}-*/;

	public void submitDelta(String key, String value) {
		getJsState().submitDelta(key, value);
	}

	public native String escape(String string) /*-{
		return string.replace("%", "%25").replace(":", "%3A");
	}-*/;
}

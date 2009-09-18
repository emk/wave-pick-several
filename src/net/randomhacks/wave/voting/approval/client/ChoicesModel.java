package net.randomhacks.wave.voting.approval.client;

import java.util.ArrayList;
import java.util.TreeMap;

import net.randomhacks.wave.gadgets.client.State;
import net.randomhacks.wave.gadgets.client.Wave;
import net.randomhacks.wave.gadgets.client.Wave.StateListener;

class ChoicesModel implements StateListener {
	interface Listener {
		void notifyChoicesChanged(ArrayList<Choice> choicesModel);
	}
	
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	private Wave wave;
	
	public ChoicesModel(Wave wave) {
		this.wave = wave;
		this.wave.addStateListener(this);
	}

	/** Add a new choice that users can vote for. */
	public void addChoice(String choiceName) {
		String key = choiceName.toLowerCase();
		State state = wave.getState();
		state.submitDelta("choiceName:" + state.escape(key), choiceName);
	}

	/** Specify whether the current user has voted for this choice. */
	public void setChosen(String choiceName, boolean isChosen) {
		String viewerId = wave.getViewer().getId();
		String key = choiceName.toLowerCase();
		State state = wave.getState();
		state.submitDelta(
				"chosen:" + state.escape(key) + ":" + state.escape(viewerId),
				isChosen ? "" : null);
	}
	
	/** Add a listener which will be notified of state changes. */
	void addListener(Listener listener) {
		listeners.add(listener);
	}

	/** Called when the gadget's underlying state changes. */
	public void onStateChange() {
		State state = wave.getState();
		String viewerId = wave.getViewer().getId();
			
		ArrayList<String> stateKeys = state.getKeys(); 
		TreeMap<String,Choice> choiceMap = new TreeMap<String, Choice>();

		// Build our Choice objects.
		for (String stateKey : stateKeys) {
			String components[] = stateKey.split(":");
			if (components[0].equals("choiceName"))
				choiceMap.put(components[1], new Choice(state.get(stateKey)));
		}
		
		// Count the votes.
		for (String stateKey : stateKeys) {
			String components[] = stateKey.split(":");
			if (components[0].equals("chosen")) {
				Choice choice = choiceMap.get(components[1]);
				choice.votes += 1;
				if (components[2].equals(state.escape(viewerId)))
					choice.wasChosenByMe = true;
			}
		}

		// Convert our map to a sorted list.
		ArrayList<Choice> choices = new ArrayList<Choice>();
		for (Choice choice : choiceMap.values())
			choices.add(choice);

		calculateWinners(choices);
		notifyListeners(choices);
	}

	private void calculateWinners(ArrayList<Choice> choices) {
		int maxVotes = 0;
		for (Choice choice : choices)
			if (choice.votes > maxVotes)
				maxVotes = choice.votes;
		for (Choice choice : choices)
			choice.isWinner = (maxVotes > 0 && choice.votes == maxVotes);
	}

	private void notifyListeners(ArrayList<Choice> choices) {
		for (Listener listener : listeners)
			listener.notifyChoicesChanged(choices);
	}
}

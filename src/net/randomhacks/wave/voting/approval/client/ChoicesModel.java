package net.randomhacks.wave.voting.approval.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import net.randomhacks.wave.gadgets.client.State;
import net.randomhacks.wave.gadgets.client.Wave;
import net.randomhacks.wave.gadgets.client.Wave.StateListener;

class ChoicesModel implements StateListener {
	interface Listener {
		void notifyChoicesChanged(ArrayList<Choice> choicesModel);
	}
	
	private Wave wave;
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	public ChoicesModel(Wave wave) {
		this.wave = wave;
		this.wave.addStateListener(this);
	}

	private String choiceKey(String choiceName) {
		return wave.getState().escape(choiceName.toLowerCase());
	}
	
	private String viewerKey() {
		return wave.getState().escape(wave.getViewer().getId());
	}
	
	/** Add a new choice that users can vote for. */
	public void addChoice(String choiceName) {
		String key = "choiceName:" + choiceKey(choiceName);
		wave.getState().submitDelta(key, choiceName);
	}

	/** Specify whether the current user has voted for this choice. */
	public void setChosen(String choiceName, boolean isChosen) {
		String key = "chosen:" + choiceKey(choiceName) + ":" + viewerKey();
		wave.getState().submitDelta(key, isChosen ? "" : null);
	}
	
	/** Called when the gadget's underlying state changes. */
	public void onStateChange() {
		ArrayList<String> stateKeys = wave.getState().getKeys(); 
		HashMap<String,Choice> choiceMap = new HashMap<String, Choice>();

		buildChoiceObjects(stateKeys, choiceMap);		
		countVotesAndMarkMyOwn(stateKeys, choiceMap);

		ArrayList<Choice> choices = convertChoiceMapToSortedList(choiceMap);

		calculateWinners(choices);
		notifyListeners(choices);
	}

	private void buildChoiceObjects(ArrayList<String> stateKeys,
			HashMap<String, Choice> choiceMap) {
		State state = wave.getState();
		for (String stateKey : stateKeys) {
			String components[] = stateKey.split(":");
			if (components[0].equals("choiceName"))
				choiceMap.put(components[1], new Choice(state.get(stateKey)));
		}
	}

	private void countVotesAndMarkMyOwn(ArrayList<String> stateKeys,
			HashMap<String, Choice> choiceMap) {
		String viewerKey = viewerKey();
		for (String stateKey : stateKeys) {
			String components[] = stateKey.split(":");
			if (components[0].equals("chosen")) {
				Choice choice = choiceMap.get(components[1]);
				choice.votes += 1;
				if (components[2].equals(viewerKey))
					choice.wasChosenByMe = true;
			}
		}
	}

	private ArrayList<Choice> convertChoiceMapToSortedList(
			HashMap<String, Choice> choiceMap) {
		ArrayList<Choice> choices = new ArrayList<Choice>();
		for (Choice choice : choiceMap.values())
			choices.add(choice);
		Collections.sort(choices, new Comparator<Choice>() {
			public int compare(Choice c1, Choice c2) {
				return c1.name.compareToIgnoreCase(c2.name);
			}
		});
		return choices;
	}

	private void calculateWinners(ArrayList<Choice> choices) {
		int maxVotes = 0;
		for (Choice choice : choices)
			if (choice.votes > maxVotes)
				maxVotes = choice.votes;
		for (Choice choice : choices)
			choice.isWinner = (maxVotes > 0 && choice.votes == maxVotes);
	}

	/** Add a listener which will be notified of state changes. */
	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyListeners(ArrayList<Choice> choices) {
		for (Listener listener : listeners)
			listener.notifyChoicesChanged(choices);
	}
}

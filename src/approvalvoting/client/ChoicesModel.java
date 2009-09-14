package approvalvoting.client;

import java.util.ArrayList;
import java.util.TreeMap;

class ChoicesModel {
	interface Listener {
		void notifyChoicesChanged(ArrayList<Choice> choicesModel);
	}
	
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	private TreeMap<String,Choice> choices = new TreeMap<String,Choice>();
	
	void addListener(Listener listener) {
		listeners.add(listener);
		notifyListeners();
	}

	void addChoice(String choiceName) {
		String key = choiceName.toLowerCase();
		if (!choices.containsKey(key))
			choices.put(key, new Choice(choiceName));
		notifyListeners();
	}

	public void setChosen(String choiceName, boolean isChosen) {
		String key = choiceName.toLowerCase();
		Choice choice = choices.get(key);
		choice.wasChosenByMe = isChosen;
		choice.votes = isChosen ? 1 : 0;
		calculateWinners();
		notifyListeners();
	}
	
	private void calculateWinners() {
		int maxVotes = 0;
		for (Choice choice : choices.values())
			if (choice.votes > maxVotes)
				maxVotes = choice.votes;
		for (Choice choice : choices.values())
			choice.isWinner = (maxVotes > 0 && choice.votes == maxVotes);
	}

	private void notifyListeners() {
		ArrayList<Choice> choicesList = new ArrayList<Choice>();
		for (Choice choice : choices.values())
			choicesList.add(choice);
		
		for (Listener listener : listeners)
			listener.notifyChoicesChanged(choicesList);
	}
}

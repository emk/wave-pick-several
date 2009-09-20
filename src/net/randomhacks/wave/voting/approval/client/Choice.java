package net.randomhacks.wave.voting.approval.client;

public class Choice {
	String key;
	String name;
	int votes;
	boolean wasChosenByMe;
	boolean isWinner;
	
	public Choice(String key, String name) {
		this.key = key;
		this.name = name;
		this.votes = 0;
		this.wasChosenByMe = false;
		this.isWinner = false;
	}
	
	public String toString() {
		return key + ": [" + (wasChosenByMe ? "X" : "_") + "] " + name +
			"(" + Integer.toString(votes) + ")" + (isWinner ? "*" : ""); 
	}
}

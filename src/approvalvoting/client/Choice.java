package approvalvoting.client;

public class Choice {
	String name;
	int votes;
	boolean wasChosenByMe;
	boolean isWinner;
	
	public Choice(String name) {
		this.name = name;
		this.votes = 0;
		this.wasChosenByMe = false;
		this.isWinner = false;
	}
}

package net.randomhacks.wave.voting.approval.client;

import net.randomhacks.wave.voting.approval.client.Choice;
import junit.framework.TestCase;

public class ChoiceTest extends TestCase {
	public void testNewInstanceShouldHaveNameAndDefaultValues() {
		Choice choice = new Choice("name", "Name");
		assertEquals("name", choice.key);
		assertEquals("Name", choice.name);
		assertEquals(0, choice.votes);
		assertEquals(false, choice.wasChosenByMe);
		assertEquals(false, choice.isWinner);
	}
}

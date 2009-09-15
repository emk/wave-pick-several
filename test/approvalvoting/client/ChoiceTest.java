package approvalvoting.client;

import junit.framework.TestCase;

public class ChoiceTest extends TestCase {
	public void testNewInstanceShouldHaveNameAndDefaultValues() {
		Choice choice = new Choice("Name");
		assertEquals("Name", choice.name);
		assertEquals(0, choice.votes);
		assertEquals(false, choice.wasChosenByMe);
		assertEquals(false, choice.isWinner);
	}
}

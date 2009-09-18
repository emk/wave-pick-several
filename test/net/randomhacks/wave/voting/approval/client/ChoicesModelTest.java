package net.randomhacks.wave.voting.approval.client;

import java.util.ArrayList;

import net.randomhacks.wave.gadgets.client.FakeWaveGadgetState;
import net.randomhacks.wave.gadgets.client.WaveGadgetState;
import junit.framework.TestCase;

// Note that any test involving 'model' is a low-level data storage test, and
// rely on it always returning the same answer in the future.  Changing the
// values expected in these tests will break the instances of this gadget
// already in use.
public class ChoicesModelTest extends TestCase {

	private WaveGadgetState state;
	private ChoicesModel model;

	protected void setUp() throws Exception {
		state = new FakeWaveGadgetState();
		model = new ChoicesModel("user:@example.com", state);
	}
	
	public void testOnlyColonAndPercentShouldBeEscapedInStateKeys() {
		model.addChoice("ABC:%&= ");
		assertEquals("ABC:%&= ", state.get("choiceName:abc%3A%25&= "));
	}
	
	public void testAddingAChoiceShouldAddAStateKey() {
		model.addChoice("Foo");
		assertEquals("Foo", state.get("choiceName:foo"));
	}
	
	public void testVotingShouldAddAStateKey() {
		model.addChoice("Foo:Bar");
		model.setChosen("Foo:Bar", true);
		assertEquals("", state.get("chosen:foo%3Abar:user%3A@example.com")); 
	}
	
	public void testUnvotingShouldClearAStateKey() {
		model.addChoice("Foo:Bar");
		state.submitDelta("chosen:foo%3Abar:user%3A@example.com", "");
		model.setChosen("Foo:Bar", false);
		assertNull(state.get("chosen:foo%3Abar")); 
	}
	
	public void testChoicesShouldBeRecreatedFromState() {
		state.submitDelta("choiceName:pizza", "Pizza");
		state.submitDelta("chosen:pizza:user%3A@example.com", "");
		state.submitDelta("chosen:pizza:sally@example.com", "");
		
		state.submitDelta("choiceName:sandwiches", "Sandwiches");
		state.submitDelta("chosen:sandwiches:sally@example.com", "");
		state.submitDelta("chosen:sandwiches:frank@example.com", "");
		state.submitDelta("chosen:sandwiches:rachel@example.com", "");

		state.submitDelta("choiceName:bagels", "Bagels");
		
		// TODO - Make sure we ignore impossible options.
		//state.submitDelta("chosen:crepes:rachel@example.com", "");
		
		TestListener listener = new TestListener();
		model.addListener(listener);
		model.onStateChange(state);
		assertTrue(listener.wasNotified);
	}

	class TestListener implements ChoicesModel.Listener {
		boolean wasNotified = false;
		
		public void notifyChoicesChanged(ArrayList<Choice> choices) {
			wasNotified = true;
			assertEquals(3, choices.size());
			assertChoiceEquals("Bagels",     0, false, false, choices.get(0));
			assertChoiceEquals("Pizza",      2, true,  false, choices.get(1));
			assertChoiceEquals("Sandwiches", 3, false, true,  choices.get(2));
		}

	};

	private static void assertChoiceEquals(String name, int votes, boolean wasChosenByMe, boolean isWinner, Choice choice) {
		assertEquals(name, choice.name);
		assertEquals(votes, choice.votes);
		assertEquals(wasChosenByMe, choice.wasChosenByMe);
		assertEquals(isWinner, choice.isWinner);
	}

	// TODO - Deal with null viewerId.
}

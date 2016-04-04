package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public class FruitCountingGoalTest extends BaseTest {
	private FruitCountingGoal goal;

	@Before
	public void setup() {
		initMocks(this);
		goal = new FruitCountingGoal(1, actor);
	}

	@Test
	public void whenItHitsTheGoal_ItNotifies() throws Exception {
		goal.addListeners();

		goal.fire(newFruitEatenEvent());

		verify(actor).fire(eventCaptor.capture());
		assertEquals(SnakeEvent.Type.win, eventCaptor.getValue().getType());
	}

	private SnakeEvent newFruitEatenEvent() {
		return new SnakeEvent(null, SnakeEvent.Type.addTail);
	}
}

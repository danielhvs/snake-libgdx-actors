package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public class FruitCountingGoalTest extends BaseTest {
	@Spy
	private FruitCountingGoal goal;

	@Before
	public void setup() {
		goal = new FruitCountingGoal(1);
		initMocks(this);
	}

	@Test
	public void whenItHitsTheGoal_ItNotifies() throws Exception {
		goal.addListeners();

		goal.fire(newFruitEatenEvent());

		verify(goal, times(2)).fire(eventCaptor.capture());
		assertEquals(SnakeEvent.Type.win, eventCaptor.getValue().getType());
	}

	private SnakeEvent newFruitEatenEvent() {
		return new SnakeEvent(null, SnakeEvent.Type.addTail);
	}
}

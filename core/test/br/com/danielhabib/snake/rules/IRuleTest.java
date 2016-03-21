package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public abstract class IRuleTest extends BaseTest {
	@Before
	public void setup() {
		superSetup();
		when(stage.getActors()).thenReturn(Array.with(actor));
	}

	@Test
	public void fireAnEvent_WhenItsCalled() throws Exception {
		IRule rules = newInstanceOfIRule();

		rules.fireEvent(actor);

		verify(actor).fire(eventCaptor.capture());

		assertEvent(eventCaptor.getValue());
	}

	abstract void assertEvent(SnakeEvent capture) throws Exception;

	abstract IRule newInstanceOfIRule();
}

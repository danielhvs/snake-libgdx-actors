package br.com.danielhabib.snake.rules;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CompositeInputListenerTest extends BaseTest {
	@Mock
	InputListener listener1;
	@Mock
	InputListener listener2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itCanStoreManyListenersAndCallsEachOneInOrder() throws Exception {
		CompositeInputListener listeners = new CompositeInputListener(listener1, listener2);

		listeners.touchDown(null, 0.0f, 1.0f, 2, 3);

		verify(listener1).touchDown((InputEvent) isNull(), eq(0.0f), eq(1.0f), eq(2), eq(3));
		verify(listener2).touchDown((InputEvent) isNull(), eq(0.0f), eq(1.0f), eq(2), eq(3));
	}
}

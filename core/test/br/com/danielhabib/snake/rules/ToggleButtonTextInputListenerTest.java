package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ToggleButtonTextInputListenerTest extends BaseTest {
	@Mock
	TextButton button;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void itTogglesIfTheButtonIsPressed() throws Exception {
		when(button.getText()).thenReturn("on");
		ToggleButtonTextInputListener listener = new ToggleButtonTextInputListener(button, "on", "off");

		touchDown(listener);

		verify(button).setText(Matchers.eq("off"));
	}


}

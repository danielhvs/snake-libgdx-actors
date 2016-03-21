package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.danielhabib.snake.sound.SoundConfigInputListener;
import br.com.danielhabib.snake.sound.SoundManager;

public class SoundConfigInputListenerTest extends BaseTest {
	@Mock
	SoundManager soundManager;

	@Test
	public void itDisablesSoundsWhenTheCommandIsToTurnOff() throws Exception {
		Mockito.when(soundManager.isEnabled()).thenReturn(true);
		SoundConfigInputListener listener = new SoundConfigInputListener(soundManager);

		touchDown(listener);

		verify(soundManager).disable();
	}

	@Test
	public void itEnablesSoundsWhenTheCommandIsToTurnOn() throws Exception {
		Mockito.when(soundManager.isEnabled()).thenReturn(false);
		SoundConfigInputListener listener = new SoundConfigInputListener(soundManager);

		touchDown(listener);

		verify(soundManager).enable();
	}
}

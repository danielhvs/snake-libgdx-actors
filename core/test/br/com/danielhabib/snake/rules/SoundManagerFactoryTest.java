package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.sound.SoundManagerFactory;
import br.com.danielhabib.snake.sound.SoundReader;

public class SoundManagerFactoryTest {
	@Mock
	SoundReader soundReader;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itStoresSoundsConfiguration() throws Exception {
		SoundManagerFactory factory = new SoundManagerFactory();

		SoundManager manager = factory.newSoundManager(soundReader);

		verify(soundReader, atLeast(1)).readSound(Mockito.anyString());
	}
}

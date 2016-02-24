package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.audio.Sound;

import br.com.danielhabib.snake.rules.SnakeEvent.Type;

public class SoundManagerTest {
	@Mock
	SoundReader soundReader;

	@Mock
	Sound sound;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itMapsTheEventToASound() throws Exception {
		when(soundReader.readSound("tail.wav")).thenReturn(sound);
		SoundManager sounds = new SoundManager(soundReader);

		sounds.put(Type.addTail, "tail.wav");
		sounds.play(Type.addTail);

		verify(sound).play();
	}

	@Test
	public void itDisposesSounds() throws Exception {
		when(soundReader.readSound("tail.wav")).thenReturn(sound);
		SoundManager sounds = new SoundManager(soundReader);

		sounds.put(Type.addTail, "tail.wav");
		sounds.dispose();

		verify(sound).dispose();
	}
}

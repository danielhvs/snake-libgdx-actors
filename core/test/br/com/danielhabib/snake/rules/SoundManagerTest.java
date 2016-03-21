package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.audio.Sound;

import br.com.danielhabib.snake.listeners.SnakeEvent.Type;
import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.sound.SoundReader;

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

	@Test
	public void itCanDisableSound() throws Exception {
		when(soundReader.readSound("tail.wav")).thenReturn(sound);
		SoundManager sounds = new SoundManager(soundReader);

		sounds.put(Type.addTail, "tail.wav");
		sounds.disable();
		sounds.play(Type.addTail);

		verify(sound, never()).play();
	}

	@Test
	public void itCanEnableSound() throws Exception {
		when(soundReader.readSound("tail.wav")).thenReturn(sound);
		SoundManager sounds = new SoundManager(soundReader);

		sounds.put(Type.addTail, "tail.wav");
		sounds.disable();
		sounds.enable();
		sounds.play(Type.addTail);

		verify(sound).play();
	}

}

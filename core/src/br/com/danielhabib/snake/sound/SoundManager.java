package br.com.danielhabib.snake.sound;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.audio.Sound;

import br.com.danielhabib.snake.listeners.SnakeEvent;
import br.com.danielhabib.snake.listeners.SnakeEvent.Type;

public class SoundManager {
	private SoundReader soundReader;
	private Map<Type, Sound> sounds = new HashMap<Type, Sound>();
	private boolean enabled;
	public static final SoundManager NoSoundManager = new NOPSoundManager(null);

	public SoundManager(SoundReader soundReader) {
		this.soundReader = soundReader;
		this.enabled = true;
	}

	public void put(Type type, String fileName) {
		sounds.put(type, soundReader.readSound(fileName));
	}

	public void play(Type type) {
		if (enabled) {
			sounds.get(type).play();
		}
	}

	// FIXME: It never disposes yet...
	public void dispose() {
		Collection<Sound> allSounds = sounds.values();
		for (Sound sound : allSounds) {
			sound.stop();
			sound.dispose();
			sounds.remove(sound);
		}
	}

	private static class NOPSoundManager extends SoundManager {

		public NOPSoundManager(SoundReader soundReader) {
			super(null);
		}

		@Override
		public void dispose() {
		}

	}

	public void disable() {
		this.enabled = false;
	}

	public void enable() {
		this.enabled = true;
	}

	public boolean isEnabled() {
		return enabled;
	}

}

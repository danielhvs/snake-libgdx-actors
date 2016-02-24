package br.com.danielhabib.snake.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.audio.Sound;

import br.com.danielhabib.snake.rules.SnakeEvent.Type;

public class SoundManager {
	private SoundReader soundReader;
	private Map<Type, Sound> sounds = new HashMap<Type, Sound>();

	public SoundManager(SoundReader soundReader) {
		this.soundReader = soundReader;
	}

	public void put(Type type, String fileName) {
		sounds.put(type, soundReader.readSound(fileName));
	}

	public void play(Type type) {
		sounds.get(type).play();
	}

	public void dispose() {
		Collection<Sound> allSounds = sounds.values();
		for (Sound sound : allSounds) {
			sound.stop();
			sound.dispose();
			sounds.remove(sound);
		}
	}

}

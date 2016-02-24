package br.com.danielhabib.snake.rules;

import br.com.danielhabib.snake.rules.SnakeEvent.Type;

public class SoundManagerFactory {

	public SoundManager newSoundManager(SoundReader soundReader) {
		SoundManager sounds = new SoundManager(soundReader);
		sounds.put(Type.died, "dead.mp3");
		sounds.put(Type.addTail, "apple.wav");
		sounds.put(Type.revert, "revert.wav");
		sounds.put(Type.removeTail, "poison.mp3");
		sounds.put(Type.speed, "speed.wav");
		return sounds;
	}

}

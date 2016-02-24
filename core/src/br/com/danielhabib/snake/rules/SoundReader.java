package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundReader implements ISoundReader {

	@Override
	public Sound readSound(String fileName) {
		return Gdx.audio.newSound(Gdx.files.internal(fileName));
	}

}

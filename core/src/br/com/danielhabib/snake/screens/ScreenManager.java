package br.com.danielhabib.snake.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.sound.SoundManagerFactory;
import br.com.danielhabib.snake.sound.SoundReader;

public class ScreenManager {

	// Singleton: unique instance
	private static ScreenManager instance;

	// Reference to game
	private Game game;

	private SoundManager soundManager;

	// Singleton!?: private constructor
	private ScreenManager() {
		super();
	}

	// Singleton: retrieve instance
	public static ScreenManager getInstance() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}

	// Initialization with the game class
	public void initialize(Game game) {
		this.game = game;
		this.soundManager = new SoundManagerFactory().newSoundManager(new SoundReader());
	}

	public void showScreen(ScreenEnum screenEnum, Object... params) {

		Screen previousScreen = game.getScreen();

		AbstractScreen newScreen = screenEnum.getScreen(soundManager, params);
		newScreen.buildStage();
		game.setScreen(newScreen);

		if (previousScreen != null) {
			previousScreen.dispose();
		}
	}

}

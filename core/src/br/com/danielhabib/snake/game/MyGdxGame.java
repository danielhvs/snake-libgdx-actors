package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		setScreen(new Splash());
	}

}

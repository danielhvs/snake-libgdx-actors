package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Game;

import br.com.danielhabib.snake.game.Splash;

public class SnakeDeathRule implements IRule {
	private Game game;

	public SnakeDeathRule(Game game) {
		this.game = game;
	}

	@Override
	public Snake update(Snake snake) {
		game.setScreen(new Splash(game));
		return null;
	}

}

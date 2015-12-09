package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Game;

import br.com.danielhabib.snake.game.Splash;

public class SnakeDeathRule extends IRule {
	private Game game;

	public SnakeDeathRule(Game game) {
		super();
		this.game = game;
	}

	// FIXME: Fire some event...
	@Override
	protected void fireEvent(float delta) {
		game.setScreen(new Splash(game));
	}


}

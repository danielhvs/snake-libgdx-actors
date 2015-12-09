package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.game.Splash;

public class SnakeDeathRule extends IRule {
	private Game game;

	public SnakeDeathRule(Game game) {
		super();
		this.game = game;
	}

	// FIXME: Fire some event...
	@Override
	public void fireEvent(Actor source) {
		game.setScreen(new Splash(game));
	}


}

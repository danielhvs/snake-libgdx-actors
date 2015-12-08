package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Game;

import br.com.danielhabib.snake.game.Splash;

public class SnakeDeathRule extends IRule {
	private Game game;

	public SnakeDeathRule(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void fireEvent() {
		game.setScreen(new Splash(game));
	}

	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		return false;
	}

}

package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.game.EntityBuilder;
import br.com.danielhabib.snake.game.EventFirerEntity;
import br.com.danielhabib.snake.game.SpeedFruit;
import br.com.danielhabib.snake.game.TextureManager;

public class SpeedBuilder implements EntityBuilder {
	private TextureManager manager;

	public SpeedBuilder(TextureManager manager) {
		this.manager = manager;
	}

	@Override
	public EventFirerEntity build(Vector2 pos) {
		return new SpeedFruit(manager.getTexture("identityRule"), pos);
	}
}

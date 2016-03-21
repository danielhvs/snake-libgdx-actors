package br.com.danielhabib.snake.entity.builders;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.SpeedFruit;
import br.com.danielhabib.snake.entity.TextureManager;

public class SpeedBuilder implements EntityBuilder {
	private TextureManager manager;

	public SpeedBuilder(TextureManager manager) {
		this.manager = manager;
	}

	@Override
	public EventFirerEntity build(Vector2 pos) {
		return new SpeedFruit(manager.getTexture("speed"), pos);
	}
}

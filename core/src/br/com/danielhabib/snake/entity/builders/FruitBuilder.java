package br.com.danielhabib.snake.entity.builders;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.Fruit;
import br.com.danielhabib.snake.entity.TextureManager;

public class FruitBuilder implements EntityBuilder {
	private TextureManager manager;

	public FruitBuilder(TextureManager manager) {
		this.manager = manager;
	}

	@Override
	public EventFirerEntity build(Vector2 pos) {
		return new Fruit(manager.getTexture("fruit"), pos);
	}

}

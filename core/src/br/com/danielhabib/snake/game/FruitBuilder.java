package br.com.danielhabib.snake.game;

import com.badlogic.gdx.math.Vector2;

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

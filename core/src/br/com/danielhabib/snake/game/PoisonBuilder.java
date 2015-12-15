package br.com.danielhabib.snake.game;

import com.badlogic.gdx.math.Vector2;

public class PoisonBuilder implements EntityBuilder {
	private TextureManager manager;

	public PoisonBuilder(TextureManager manager) {
		this.manager = manager;
	}
	@Override
	public EventFirerEntity build(Vector2 pos) {
		return new PoisonedFruit(manager.getTexture("poison"), pos);
	}

}

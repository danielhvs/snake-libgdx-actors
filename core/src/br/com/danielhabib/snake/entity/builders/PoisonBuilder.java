package br.com.danielhabib.snake.entity.builders;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.PoisonedFruit;
import br.com.danielhabib.snake.entity.TextureManager;

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

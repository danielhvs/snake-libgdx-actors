package br.com.danielhabib.snake.entity.builders;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.TextureManager;
import br.com.danielhabib.snake.entity.Wall;

public class WallBuilder implements EntityBuilder {
	private TextureManager manager;

	public WallBuilder(TextureManager manager) {
		this.manager = manager;
	}

	@Override
	public EventFirerEntity build(Vector2 pos) {
		return new Wall(manager.getTexture("identityRule"), pos);
	}

}

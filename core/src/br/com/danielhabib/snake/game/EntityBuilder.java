package br.com.danielhabib.snake.game;

import com.badlogic.gdx.math.Vector2;

public interface EntityBuilder {

	EventFirerEntity build(Vector2 pos);
}

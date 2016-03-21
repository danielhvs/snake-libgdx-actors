package br.com.danielhabib.snake.entity.builders;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.entity.EventFirerEntity;

public interface EntityBuilder {

	EventFirerEntity build(Vector2 pos);
}

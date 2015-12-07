package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface SnakeDrawable {
	void update();

	void render(Batch batch);

	void dispose();
}

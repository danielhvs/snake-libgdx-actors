package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface SnakeDrawable {
	void update();

	void render(SpriteBatch batch);

	void dispose();
}

package br.com.danielhabib.snake.game;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.rules.SnakeDrawable;

public class DrawableManager {
	private final Array<SnakeDrawable> drawables = new Array<SnakeDrawable>();

	public void addDrawable(SnakeDrawable entity) {
		drawables.add(entity);
	}

	public void update() {
		for (SnakeDrawable drawable : drawables) {
			drawable.update();
		}
	}

	public void render(SpriteBatch batch) {
		for (SnakeDrawable drawable : drawables) {
			drawable.render(batch);
		}
	}

	public void addDrawables(List<SnakeDrawable> items) {
		for (SnakeDrawable drawable : items) {
			addDrawable(drawable);
		}
	}
}

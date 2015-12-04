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

	public void addDrawables(List<? extends SnakeDrawable> items) {
		for (SnakeDrawable drawable : items) {
			addDrawable(drawable);
		}
	}

	public void dispose() {
		for (SnakeDrawable drawable : drawables) {
			drawable.dispose();
		}
	}

	public void remove(SnakeDrawable drawable) {
		drawables.removeValue(drawable, true);
	}

}

package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public class BoingMovingRules extends AMovingRules {
	private int lastX;
	private int lastY;
	private int firstX;
	private int firstY;

	public BoingMovingRules(int firstX, int firstY, int lastX, int lastY) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	// FIXME: Generalize to use a map (list of walls).
	@Override
	public Snake update(Snake snake) {
		Vector2 nextPosition = snake.getNextPosition();
		return isOutOfBounds(nextPosition) ? snake.revert() : snake.move();
	}

	private boolean isOutOfBounds(Vector2 nextPosition) {
		return nextPosition.x > lastX || nextPosition.x < firstX || nextPosition.y > lastY || nextPosition.y < firstY;
	}

}

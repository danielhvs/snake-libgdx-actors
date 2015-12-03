package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public class BoingMovingRules extends AMovingRules {
	private AMovingRules movingRules;
	private int lastX;
	private int lastY;
	private int firstX;
	private int firstY;

	public BoingMovingRules(AMovingRules movingRules, int firstX, int firstY, int lastX, int lastY) {
		this.movingRules = movingRules;
		this.firstX = firstX;
		this.firstY = firstY;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	// FIXME: Broke!?
	@Override
	public Snake update(Snake snake) {
		Snake nextSnake = movingRules.update(snake);
		Vector2 nextPosition = nextSnake.getPosition();
		return isOutOfBounds(nextPosition) ? snake.revert() : nextSnake;
	}

	@Override
	public Snake turnLeft(Snake snake) {
		return movingRules.turnLeft(snake);
	}

	@Override
	public Snake turnRight(Snake snake) {
		return movingRules.turnRight(snake);
	}

	private boolean isOutOfBounds(Vector2 nextPosition) {
		return nextPosition.x > lastX || nextPosition.x < firstX || nextPosition.y > lastY || nextPosition.y < firstY;
	}

}

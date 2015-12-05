package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public class MirrorMapMovingRules extends AMovingRules {

	private AMovingRules movingRules;
	private float lastX;
	private float lastY;

	public MirrorMapMovingRules(AMovingRules movingRules, float lastX, float lastY) {
		this.movingRules = movingRules;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	// FIXME: Requires texture
	@Override
	public Snake update(Snake snake) {

		Snake nextSnake = movingRules.update(snake);
		Vector2 nextPosition = nextSnake.getPosition();
		if (nextPosition.x > lastX) {
			return snake.move(new Vector2(0, snake.getPosition().y));
		} else if (nextPosition.x < 0) {
			return snake.move(new Vector2(lastX, snake.getPosition().y));
		} else if (nextPosition.y > lastY) {
			return snake.move(new Vector2(snake.getPosition().x, 0));
		} else if (nextPosition.y < 0) {
			return snake.move(new Vector2(snake.getPosition().x, lastY));
		}
		return nextSnake;
	}

	@Override
	public Snake turnLeft(Snake snake) {
		return movingRules.turnLeft(snake);
	}

	@Override
	public Snake turnRight(Snake snake) {
		return movingRules.turnRight(snake);
	}

}

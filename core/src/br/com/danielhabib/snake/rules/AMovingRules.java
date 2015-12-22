package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AMovingRules extends Actor {

	private static final float DEGREES_PER_MOVEMENT = 3.5f;
	protected Snake snake;

	public AMovingRules(Snake snake) {
		this.snake = snake;
	}

	public void turnLeft(Snake snake, float delta) {
		snake.turn(DEGREES_PER_MOVEMENT);
	}

	public void turnRight(Snake snake, float delta) {
		snake.turn(-DEGREES_PER_MOVEMENT);
	}

}

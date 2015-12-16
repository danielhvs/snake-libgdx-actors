package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AMovingRules extends Actor {

	protected Snake snake;
	private float speed = 250f;
	protected boolean checking = false;

	public AMovingRules(Snake snake) {
		this.snake = snake;
	}

	// FIXME: Transaction!?
	public Snake turnLeft(Snake snake, float delta) {
		if (!checking) {
			return snake.turn(snake.getDirection().rotate(delta * speed));
		} else {
			return snake;
		}
	}

	public Snake turnRight(Snake snake, float delta) {
		if (!checking) {
			return snake.turn(snake.getDirection().rotate(-delta * speed));
		} else {
			return snake;
		}
	}

}

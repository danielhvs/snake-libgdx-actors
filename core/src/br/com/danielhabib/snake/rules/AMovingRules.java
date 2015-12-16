package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AMovingRules extends Actor {

	protected Snake snake;
	private float speed = 250f;

	public AMovingRules(Snake snake) {
		this.snake = snake;
	}

	public Snake turnLeft(Snake snake, float delta) {
		snake.turn(snake.getDirection().rotate(delta * speed));
		return snake.move(delta);
	}

	public Snake turnRight(Snake snake, float delta) {
		snake.turn(snake.getDirection().rotate(-delta * speed));
		return snake.move(delta);
	}

}

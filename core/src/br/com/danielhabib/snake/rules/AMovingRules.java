package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AMovingRules extends Actor {

	protected Snake snake;

	public AMovingRules(Snake snake) {
		this.snake = snake;
	}

	public void turnLeft(Snake snake, float delta) {
		snake.turn(2.5f);
	}

	public void turnRight(Snake snake, float delta) {
		snake.turn(-2.5f);
	}

}

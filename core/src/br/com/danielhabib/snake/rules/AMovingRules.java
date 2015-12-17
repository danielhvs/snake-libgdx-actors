package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AMovingRules extends Actor {

	protected Snake snake;

	public AMovingRules(Snake snake) {
		this.snake = snake;
	}

	public void turnLeft(Snake snake, float delta) {
		snake.turn(snake.getDirection().rotate(snake.getSpeed() / 2f));
	}

	public void turnRight(Snake snake, float delta) {
		snake.turn(snake.getDirection().rotate(-snake.getSpeed() / 2f));
	}

}

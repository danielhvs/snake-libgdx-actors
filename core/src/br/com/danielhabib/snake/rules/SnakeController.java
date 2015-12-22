package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SnakeController extends Actor {

	private AMovingRules movingRules;
	private Snake snake;

	public SnakeController(AMovingRules movingRules, Snake snake) {
		this.movingRules = movingRules;
		this.snake = snake;
	}

	public void left(Snake snake, float delta) {
		if (snake.hasActions() || snake.isDead()) {
			return;
		}
		movingRules.turnLeft(snake, delta);
	}

	public void right(Snake snake, float delta) {
		if (snake.hasActions() || snake.isDead()) {
			return;
		}
		movingRules.turnRight(snake, delta);
	}

	@Override
	public void act(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			left(snake, delta);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			right(snake, delta);
		}
	}

}

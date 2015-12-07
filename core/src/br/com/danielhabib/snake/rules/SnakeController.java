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

	public Snake up(Snake snake) {
		if (Direction.RIGHT.equals(snake.getDirection())) {
			return movingRules.turnLeft(snake);
		} else if (Direction.LEFT.equals(snake.getDirection())) {
			return movingRules.turnRight(snake);
		} else {
			return snake;
		}
	}

	public Snake down(Snake snake) {
		if (Direction.RIGHT.equals(snake.getDirection())) {
			return movingRules.turnRight(snake);
		} else if (Direction.LEFT.equals(snake.getDirection())) {
			return movingRules.turnLeft(snake);
		} else {
			return snake;
		}
	}

	public Snake left(Snake snake) {
		if (Direction.UP.equals(snake.getDirection())) {
			return movingRules.turnLeft(snake);
		} else if (Direction.DOWN.equals(snake.getDirection())) {
			return movingRules.turnRight(snake);
		} else {
			return snake;
		}
	}

	public Snake right(Snake snake) {
		if (Direction.UP.equals(snake.getDirection())) {
			return movingRules.turnRight(snake);
		} else if (Direction.DOWN.equals(snake.getDirection())) {
			return movingRules.turnLeft(snake);
		} else {
			return snake;
		}
	}

	@Override
	public void act(float delta) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			left(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			right(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			up(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			down(snake);
		}
	}

}

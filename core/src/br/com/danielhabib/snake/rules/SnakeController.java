package br.com.danielhabib.snake.rules;

public class SnakeController {

	private AMovingRules movingRules;

	public SnakeController(AMovingRules movingRules) {
		this.movingRules = movingRules;
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

}

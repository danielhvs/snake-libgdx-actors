package br.com.danielhabib.snake.rules;

public class SnakeController {

	private AMovingRules movingRules;

	public SnakeController(AMovingRules movingRules) {
		this.movingRules = movingRules;
	}

	// FIXME: Change to not use Direction.RIGHT, LEFT etc. anymore.
	public Snake up(Snake snake) {
		if (Direction.RIGHT.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnLeft(snake);
		} else if (Direction.LEFT.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnRight(snake);
		} else {
			return snake;
		}
	}

	public Snake down(Snake snake) {
		if (Direction.RIGHT.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnRight(snake);
		} else if (Direction.LEFT.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnLeft(snake);
		} else {
			return snake;
		}
	}

	public Snake left(Snake snake) {
		if (Direction.UP.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnLeft(snake);
		} else if (Direction.DOWN.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnRight(snake);
		} else {
			return snake;
		}
	}

	public Snake right(Snake snake) {
		if (Direction.UP.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnRight(snake);
		} else if (Direction.DOWN.epsilonEquals(snake.getDirection(), 0.01f)) {
			return movingRules.turnLeft(snake);
		} else {
			return snake;
		}
	}

}

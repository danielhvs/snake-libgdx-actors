package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;

public abstract class AMovingRules implements IRule {

	@Override
	public abstract Snake update(Snake snake);

	public Snake turnLeft(Snake snake) {
		Map<Direction, Direction> leftTurningOffsetMap = new HashMap<Direction, Direction>();
		leftTurningOffsetMap.put(Direction.UP, Direction.LEFT);
		leftTurningOffsetMap.put(Direction.LEFT, Direction.DOWN);
		leftTurningOffsetMap.put(Direction.DOWN, Direction.RIGHT);
		leftTurningOffsetMap.put(Direction.RIGHT, Direction.UP);

		return snake.turn(leftTurningOffsetMap.get(snake.getDirection()));
	}

	public Snake turnRight(Snake snake) {
		Map<Direction, Direction> rightTurningOffsetMap = new HashMap<Direction, Direction>();
		rightTurningOffsetMap.put(Direction.UP, Direction.RIGHT);
		rightTurningOffsetMap.put(Direction.RIGHT, Direction.DOWN);
		rightTurningOffsetMap.put(Direction.DOWN, Direction.LEFT);
		rightTurningOffsetMap.put(Direction.LEFT, Direction.UP);

		return snake.turn(rightTurningOffsetMap.get(snake.getDirection()));
	}

}

package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public abstract class AMovingRules implements IRule {

	@Override
	public abstract Snake update(Snake snake);

	public Snake turnLeft(Snake snake) {
		Map<Vector2, Vector2> leftTurningOffsetMap = new HashMap<Vector2, Vector2>();
		leftTurningOffsetMap.put(Direction.UP, Direction.LEFT);
		leftTurningOffsetMap.put(Direction.LEFT, Direction.DOWN);
		leftTurningOffsetMap.put(Direction.DOWN, Direction.RIGHT);
		leftTurningOffsetMap.put(Direction.RIGHT, Direction.UP);

		return snake.turn(leftTurningOffsetMap.get(snake.getDirection()));
	}

	public Snake turnRight(Snake snake) {
		Map<Vector2, Vector2> rightTurningOffsetMap = new HashMap<Vector2, Vector2>();
		rightTurningOffsetMap.put(Direction.UP, Direction.RIGHT);
		rightTurningOffsetMap.put(Direction.RIGHT, Direction.DOWN);
		rightTurningOffsetMap.put(Direction.DOWN, Direction.LEFT);
		rightTurningOffsetMap.put(Direction.LEFT, Direction.UP);

		return snake.turn(rightTurningOffsetMap.get(snake.getDirection()));
	}

}

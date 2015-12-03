package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.math.Vector2;

public class RestrictedMovingRules extends AMovingRules {

	// FIXME: Broke
	@Override
	public Snake update(Snake snake) {
		Stack<Vector2> nextPositions = snake.getNextPositions();
		Vector2 headPosition = nextPositions.pop();
		for (Vector2 piecePosition : nextPositions) {
			if (headPosition.epsilonEquals(piecePosition, 0.1f)) {
				return snake;
			}
		}
		return snake.move();
	}

}

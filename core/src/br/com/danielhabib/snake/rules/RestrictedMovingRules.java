package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.math.Rectangle;

public class RestrictedMovingRules extends AMovingRules {

	// FIXME: Broke
	@Override
	public Snake update(Snake snake) {
		Stack<Rectangle> nextPositions = snake.getNextPositions();
		Rectangle headPosition = nextPositions.pop();
		for (Rectangle piecePosition : nextPositions) {
			if (headPosition.contains(piecePosition) || headPosition.overlaps(piecePosition)) {
				return snake;
			}
		}
		return snake.move();
	}

}

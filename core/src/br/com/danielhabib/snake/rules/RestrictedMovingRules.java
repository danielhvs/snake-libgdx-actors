package br.com.danielhabib.snake.rules;

import java.util.Stack;

public class RestrictedMovingRules extends AMovingRules {

	// FIXME: Broke
	@Override
	public Snake update(Snake snake) {
		Snake futureSnake = snake.move();
		Stack<Piece> pieces = futureSnake.getPieces();
		Piece head = futureSnake.getHead();
		pieces.remove(0);
		while (!pieces.isEmpty()) {
			if (pieces.pop().getPosition().epsilonEquals(head.getPosition(), 0.01f)) {
				return snake;
			}
		}
		return futureSnake;
	}

}

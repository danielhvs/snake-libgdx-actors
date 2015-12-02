package br.com.danielhabib.snake.rules;

import java.util.Stack;

public class RestrictedMovingRules extends AMovingRules {

	@Override
	public Snake update(Snake snake) {
		Snake futureSnake = snake.move();
		Stack<Piece> pieces = futureSnake.getPieces();
		Piece head = futureSnake.getHead();
		pieces.remove(head);
		while (!pieces.isEmpty()) {
			if (pieces.pop().getPoint().equals(head.getPoint())) {
				return snake;
			}
		}
		return futureSnake;
	}

}

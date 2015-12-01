package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.math.Vector2;


public class BaseTest {

	protected Snake newSnake(int x, int y) {
		return newSnake(x, y, Direction.RIGHT);
	}

	protected Snake newSnake(Vector2 point, Direction direction) {
		return newSnake(point.x, point.y, direction);
	}

	protected Snake newSnake(float x, float y, Direction direction) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Piece(new Vector2(x, y), direction.getDirection()));
		return new Snake(pieces);
	}

	protected Piece newPiece(Vector2 vector, Direction direction) {
		return new Piece(vector, direction.getDirection());
	}
}

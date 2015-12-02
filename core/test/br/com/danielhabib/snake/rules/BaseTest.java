package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

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
		pieces.push(new Piece(new Vector2(x, y), direction));
		return new Snake(pieces);
	}

	protected Piece newPiece(Vector2 vector, Direction direction) {
		return new Piece(vector, direction);
	}

	protected void assertPoints(Vector2 expected, Vector2 actual) {
		assertEquals("X differs", expected.x, actual.x, 0.01f);
		assertEquals("Y differs", expected.y, actual.y, 0.01f);
	}

	protected void assertSnake(Snake expected, Snake actual) {
		Stack<Piece> expectedPieces = expected.getPieces();
		Stack<Piece> actualPieces = actual.getPieces();
		assertEquals("Pieces size", expectedPieces.size(), actualPieces.size());
		for (int i = 0; i < expectedPieces.size(); i++) {
			assertPiece(expectedPieces.get(i), actualPieces.get(i));
		}
	}

	protected void assertPiece(Piece expectedPiece, Piece actualPieces) {
		assertPoints(expectedPiece.getPoint(), actualPieces.getPoint());
		assertEquals(expectedPiece.getDirection(), actualPieces.getDirection());
	}
}

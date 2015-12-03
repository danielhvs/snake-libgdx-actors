package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BaseTest {

	@Mock
	Texture texture;

	@Mock
	SpriteBatch batch;

	@Before
	public void superSetup() {
		MockitoAnnotations.initMocks(this);
	}

	protected Snake newSnake(int x, int y) {
		return newSnake(x, y, Direction.RIGHT);
	}

	protected Snake newSnake(Vector2 point, Direction direction) {
		return newSnake(point.x, point.y, direction);
	}

	protected Snake newSnake(float x, float y, Direction direction) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Piece(new Vector2(x, y), direction, texture));
		return new Snake(pieces);
	}

	protected Snake newSnake(Stack<Piece> pieces) {
		return new Snake(pieces);
	}

	protected Piece newPiece(Vector2 vector, Direction direction) {
		return new Piece(vector, direction, texture);
	}

	protected Piece newPiece(int x, int y) {
		return newPiece(new Vector2(x, y), Direction.RIGHT);
	}

	protected void assertPoints(Vector2 expected, Vector2 actual) {
		assertEquals("X differs", expected.x, actual.x, 0.01f);
		assertEquals("Y differs", expected.y, actual.y, 0.01f);
	}

	protected void assertSnake(Snake expected, Snake actual) {
		Stack<Piece> expectedPieces = expected.copyPieces();
		Stack<Piece> actualPieces = actual.copyPieces();
		assertEquals("Pieces size", expectedPieces.size(), actualPieces.size());
		for (int i = 0; i < expectedPieces.size(); i++) {
			assertPiece(expectedPieces.get(i), actualPieces.get(i));
		}
	}

	protected void assertPiece(Piece expectedPiece, Piece actualPieces) {
		assertPoints(expectedPiece.getPosition(), actualPieces.getPosition());
		assertEquals(expectedPiece.getNormDirection(), actualPieces.getNormDirection());
	}
}
package br.com.danielhabib.snake.rules;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PieceTest extends BaseTest {

	@Test
	public void itRepresentsAPoint() throws Exception {
		// It's only a entity, now...
		Piece piece = newPiece(new Vector2(0, 0));

		assertPoints(new Vector2(0, 0), piece.getPosition());
	}

}

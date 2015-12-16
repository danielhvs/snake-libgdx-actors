package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PieceTest extends BaseTest {

	@Test
	public void itRepresentsAThetaAndARadius() throws Exception {
		Piece piece = newPiece(1f, 0f);

		assertEquals(0f, piece.getRotation(), 0.01f);
		assertPoints(new Vector2(1, 0), piece.getPosition());
	}

	@Test
	public void move_IncrementsRadius() throws Exception {
		Piece piece = newPiece(1f, 90f);

		Piece moved = piece.move(1f);

		assertPoints(new Vector2(0, 2), moved.getPosition());
	}

	@Test
	public void itCanChangeTheDirection() throws Exception {
		Piece piece = newPiece(1f, 90f);

		piece.setRotation(180f);
		piece.move(1f);

		assertPoints(new Vector2(-2, 0), piece.getPosition());
	}

}

package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PieceTest extends BaseTest {

	@Test
	public void itRepresentsAPointAndADirection() throws Exception {
		Piece piece = newPiece(new Vector2(0, 0), Direction.RIGHT);

		assertEquals(Direction.RIGHT, piece.getDirection());
		assertPoints(new Vector2(0, 0), piece.getPoint());
	}

	@Test
	public void move_MovesTowardsDirection() throws Exception {
		Piece piece = newPiece(new Vector2(0, 0), Direction.RIGHT);
		Piece moved = piece.move();

		assertPoints(new Vector2(1, 0), moved.getPoint());
	}

	@Test
	public void itCanMoveToSpecificPoint() throws Exception {
		Piece piece = newPiece(new Vector2(0, 0), Direction.RIGHT);

		Piece moved = piece.move(new Vector2(10, 0));

		assertPoints(new Vector2(10, 0), moved.getPoint());
	}

	@Test
	public void itCanChangeTheDirection() throws Exception {
		Piece piece = newPiece(new Vector2(0, 0), Direction.RIGHT);

		Piece turned = piece.turn(Direction.UP);

		assertEquals(Direction.UP, turned.getDirection());
	}

}

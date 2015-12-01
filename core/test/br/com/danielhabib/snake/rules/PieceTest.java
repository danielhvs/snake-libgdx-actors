package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PieceTest {

	@Test
	public void itRepresentsAPointAndADirection() throws Exception {
		Piece piece = new Piece(new Vector2(0, 0), right());

		assertEquals(right(), piece.getDirection());
		assertEquals(new Vector2(0, 0), piece.getPoint());
	}

	@Test
	public void move_MovesTowardsDirection() throws Exception {
		Piece piece = new Piece(new Vector2(0, 0), right());
		Piece moved = piece.move();

		assertEquals(new Vector2(1, 0), moved.getPoint());
	}

	@Test
	public void itCanMoveToSpecificPoint() throws Exception {
		Piece piece = new Piece(new Vector2(0, 0), right());

		Piece moved = piece.move(new Vector2(10, 0));

		assertEquals(new Vector2(10, 0), moved.getPoint());
	}

	@Test
	public void itCanChangeTheDirection() throws Exception {
		Piece piece = new Piece(new Vector2(0, 0), right());

		Piece turned = piece.turn(up());

		assertEquals(up(), turned.getDirection());
	}

	private Vector2 up() {
		return Direction.UP.getDirection();
	}

	private Vector2 right() {
		return Direction.RIGHT.getDirection();
	}

}

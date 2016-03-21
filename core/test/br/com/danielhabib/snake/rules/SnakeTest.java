package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.entity.Piece;

public class SnakeTest extends BaseTest {

	@Ignore("See later when the application is stable")
	public void equals_InitialCondition_HasOnlyHead() throws Exception {
		Snake snake = newSnake(1, 1);

		assertEquals(newSnake(1, 1), snake);
	}

	@Ignore("See later when the application is stable")
	public void move_OnlyHead_MovesOnePosition() throws Exception {
		Snake snake = newSnake(1, 1).move(new Vector2(2, 1));

		assertSnake(newSnake(2, 1), snake);
	}

	@Ignore("See later when the application is stable")
	public void move_ManyPieces_MovesAllPieces() throws Exception {
		Snake snake = newSnake(3, 1).addTail().addTail().addTail();

		Snake actual = snake.move(new Vector2(4, 1));

		Snake expected = newSnake(4, 1).addTail().addTail().addTail();
		assertSnake(expected, actual);
	}

	@Ignore("See later when the application is stable")
	public void getPosition_ManyPieces_ReturnsAllPositions() throws Exception {
		Snake snake = newSnake(3, 1).addTail().addTail().addTail();

		Array<Piece> pieces = snake.getPieces();

		assertPiece(newPiece(new Vector2(3, 1)), pieces.get(0));
		assertPiece(newPiece(new Vector2(2, 1)), pieces.get(1));
		assertPiece(newPiece(new Vector2(1, 1)), pieces.get(2));
		assertPiece(newPiece(new Vector2(0, 1)), pieces.get(3));
	}

	@Ignore("See later when the application is stable")
	public void addTail_NoParameter_AddsAfterLastTail() throws Exception {
		Snake snake = newSnake(3, 1).addTail().addTail().addTail();
		Snake expected = newSnake(3, 1).addTail().addTail().addTail();

		assertSnake(expected, snake);
	}

	@Ignore("See later when the application is stable")
	public void addTail_2PiecedSnake_MaintainsDirection() throws Exception {
		Snake snake = newSnake(0, 0, new Vector2(1, 0)).addTail();

		assertEquals(new Vector2(1, 0), snake.getVelocity());
	}

	@Ignore("See later the size rules about the snakes and textures...")
	public void removeTail_OneHeadedSnake_ReturnsSnakeEnd() throws Exception {
		Snake snake = newSnake(0, 0).removeTail();

		assertEquals(new Array<Piece>(), snake.getPieces());
	}

	@Ignore("See later the size rules about the snakes and textures...")
	public void removeTail_TwoHeadedSnake_ReturnsOneHeaded() throws Exception {
		Snake snake = newSnake(0, 0).addTail().removeTail();

		assertEquals(newSnake(0, 0), snake);
	}

	@Ignore("See later when the application is stable")
	public void revert_RevertsSnakePosition() throws Exception {
		Snake snake = newSnake(0, 0, new Vector2(1, 0)).revert();

		assertEquals(new Vector2(-1, 0), snake.getVelocity());
	}

	@Ignore("See later when the application is stable")
	public void revert_2PiecedSnake_RevertsSnakePosition() throws Exception {
		Snake expected = newSnake(0, 0).addTail();

		Snake original = newSnake(1, 0).addTail();
		Snake snake = original.revert();

		assertSnake(expected, snake);
	}

	@Test
	public void revert_ManyPiecedSnake_RevertsSnakeAndTailPosition() throws Exception {
		Array<Piece> pieces = new Array<Piece>();
		pieces.add(newPiece(new Vector2(2, 0)));
		pieces.add(newPiece(new Vector2(1, 0)));
		pieces.add(newPiece(new Vector2(0, 1)));
		pieces.add(newPiece(new Vector2(-1, 1)));

		Array<Piece> expectedPieces = new Array<Piece>();
		expectedPieces.add(newPiece(new Vector2(-1, 1)));
		expectedPieces.add(newPiece(new Vector2(0, 1)));
		expectedPieces.add(newPiece(new Vector2(1, 0)));
		expectedPieces.add(newPiece(new Vector2(2, 0)));

		Snake snake = new Snake(pieces, texture, new Vector2(1, 0)).revert();

		assertSnake(new Snake(expectedPieces, texture, new Vector2(1, 0)), snake);
	}

	@Ignore("See later when the application is stable")
	public void turn_ChangesHeadDirection() throws Exception {
		Snake snake = newSnake(0, 0);

		Snake turned = snake.turn(1f);

		assertEquals(32f, turned.getVelocity().angle());
	}

}

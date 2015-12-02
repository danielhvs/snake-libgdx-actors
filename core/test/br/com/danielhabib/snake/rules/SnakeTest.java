package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class SnakeTest extends BaseTest {

	@Test
	public void equals_InitialCondition_HasOnlyHead() throws Exception {
		Snake snake = newSnake(1, 1);

		assertEquals(newSnake(1, 1), snake);
	}

	@Test
	public void move_OnlyHead_MovesOnePosition() throws Exception {
		Snake snake = newSnake(1, 1).move(new Vector2(2, 1));

		assertSnake(newSnake(2, 1), snake);
	}

	@Test
	public void move_ManyPieces_MovesAllPieces() throws Exception {
		Snake snake = newSnake(3, 1).addTail(2, 1).addTail(1, 1).addTail(0, 1);

		Snake actual = snake.move(new Vector2(4, 1));

		Snake expected = newSnake(4, 1).addTail(3, 1).addTail(2, 1).addTail(1, 1);
		assertSnake(expected, actual);
	}

	@Test
	public void getPosition_ManyPieces_ReturnsAllPositions() throws Exception {
		Snake snake = newSnake(3, 1).addTail(2, 1).addTail(1, 1).addTail(0, 1);

		List<Piece> pieces = snake.getPieces();

		assertPiece(newPiece(new Vector2(3, 1), Direction.RIGHT), pieces.get(0));
		assertPiece(newPiece(new Vector2(2, 1), Direction.RIGHT), pieces.get(1));
		assertPiece(newPiece(new Vector2(1, 1), Direction.RIGHT), pieces.get(2));
		assertPiece(newPiece(new Vector2(0, 1), Direction.RIGHT), pieces.get(3));
	}

	@Test
	public void addTail_NoParameter_AddsAfterLastTail() throws Exception {
		Snake snake = newSnake(3, 1).addTail(2, 1).addTail(1, 1).addTail();
		Snake expected = newSnake(3, 1).addTail(2, 1).addTail(1, 1).addTail(0, 1);

		assertSnake(expected, snake);
	}

	@Test
	public void addTail_2PiecedSnake_MaintainsDirection() throws Exception {
		Snake snake = newSnake(0, 0, Direction.LEFT).addTail();

		assertEquals(Direction.LEFT, snake.getTail().getDirection());
	}

	@Test
	public void removeTail_OneHeadedSnake_ReturnsSnakeEnd() throws Exception {
		Snake snake = newSnake(0, 0).removeTail();

		assertEquals(new Stack<Piece>(), snake.getPieces());
	}

	@Test
	public void removeTail_TwoHeadedSnake_ReturnsOneHeaded() throws Exception {
		Snake snake = newSnake(0, 0).addTail(1, 0).removeTail();

		assertEquals(newSnake(0, 0), snake);
	}

	@Test
	public void revert_RevertsSnakePosition() throws Exception {
		Snake snake = newSnake(0, 0).revert();

		assertEquals(Direction.LEFT, snake.getTail().getDirection());
	}

	@Test
	public void revert_2PiecedSnake_RevertsSnakePosition() throws Exception {
		Snake expected = newSnake(0, 0, Direction.LEFT).addTail();

		Snake original = newSnake(1, 0).addTail();
		Snake snake = original.revert();

		assertSnake(expected, snake);
	}

	@Test
	public void revert_ManyPiecedSnake_RevertsSnakeAndTailPosition() throws Exception {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(newPiece(new Vector2(2, 0), Direction.RIGHT));
		pieces.push(newPiece(new Vector2(1, 0), Direction.RIGHT));
		pieces.push(newPiece(new Vector2(0, 1), Direction.UP));
		pieces.push(newPiece(new Vector2(-1, 1), Direction.RIGHT));

		Stack<Piece> expectedPieces = new Stack<Piece>();
		expectedPieces.push(newPiece(new Vector2(-1, 1), Direction.LEFT));
		expectedPieces.push(newPiece(new Vector2(0, 1), Direction.DOWN));
		expectedPieces.push(newPiece(new Vector2(1, 0), Direction.LEFT));
		expectedPieces.push(newPiece(new Vector2(2, 0), Direction.LEFT));

		Snake snake = new Snake(pieces).revert();

		assertSnake(new Snake(expectedPieces), snake);
	}

	@Test
	public void turn_ChangesHeadDirection() throws Exception {
		Snake snake = newSnake(0, 0, Direction.RIGHT);

		Snake turned = snake.turn(Direction.UP);

		assertEquals(Direction.UP, turned.getDirection());
	}

}

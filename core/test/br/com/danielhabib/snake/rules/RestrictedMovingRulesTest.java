package br.com.danielhabib.snake.rules;

import java.util.Stack;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class RestrictedMovingRulesTest extends BaseTest {

	@Test
	public void move_ThereIsTailInTheWay_DoesntMove() throws Exception {
		AMovingRules rules = new RestrictedMovingRules();

		Snake snake = rules.update(snakeSize5());

		assertPoints(new Vector2(5, 0), snake.getPosition());
	}

	private Snake snakeSize5() {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(newPiece(new Vector2(5, 0), Direction.DOWN));
		pieces.push(newPiece(new Vector2(4, 0), Direction.RIGHT));
		pieces.push(newPiece(new Vector2(4, 1), Direction.UP));
		pieces.push(newPiece(new Vector2(5, 1), Direction.LEFT));
		pieces.push(newPiece(new Vector2(5, 1), Direction.LEFT));
		return new Snake(pieces);
	}
}

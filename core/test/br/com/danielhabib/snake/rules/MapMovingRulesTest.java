package br.com.danielhabib.snake.rules;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.math.Vector2;

public class MapMovingRulesTest extends BaseTest {
	private static final Vector2 ORIGIN = new Vector2(0, 0);

	@Mock
	IRule movingRuleWhenCollided;

	@Mock
	IRule movingRuleWhenCollidedWithItself;

	@Mock
	IRule movingRuleWhenFree;

	@Test
	public void move_ThereIsAWall_ApplyMovingRule() throws Exception {
		List<Entity> map = wallNextToSnake();
		AMovingRules rules = new MapMovingRules(new MovingRules(), movingRuleWhenCollidedWithItself,
				map);

		Snake snake = newSnake(ORIGIN, Direction.RIGHT);
		rules.update(snake);

		verify(movingRuleWhenCollided).update(snake);
	}

	@Test
	public void move_ThereIsNotAWall_Moves() throws Exception {
		List<Entity> map = wallAwayFromSnake();
		AMovingRules rules = new MapMovingRules(new MovingRules(), movingRuleWhenCollidedWithItself,
				map);

		Snake snake = rules.update(newSnake(ORIGIN, Direction.RIGHT));

		assertPoints(new Vector2(1, 0), snake.getPosition());
	}

	@Test
	public void move_ThereIsTailInTheWay_DoesntMove() throws Exception {
		AMovingRules rules = new MapMovingRules(movingRuleWhenFree, movingRuleWhenCollidedWithItself,
				Collections.<Entity> emptyList());

		Snake snake = rules.update(snakeSize5());

		assertPoints(new Vector2(5, 0), snake.getPosition());
	}

	private Snake snakeSize5() {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(newPiece(new Vector2(5, 0), Direction.DOWN));
		pieces.push(newPiece(new Vector2(4, 0), Direction.RIGHT));
		pieces.push(newPiece(new Vector2(4, 1), Direction.UP));
		pieces.push(newPiece(new Vector2(5, 1), Direction.LEFT));
		pieces.push(newPiece(new Vector2(6, 1), Direction.LEFT));
		return new Snake(pieces, texture);
	}

	private List<Entity> wallAwayFromSnake() {
		return Arrays.asList(new Entity(texture, new Vector2(10, 10)));
	}

	private List<Entity> wallNextToSnake() {
		return Arrays.asList(new Entity(texture, new Vector2(1, 0)));
	}
}

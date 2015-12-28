package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mock;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MapMovingRulesTest extends BaseTest {
	private static final Vector2 ORIGIN = new Vector2(0, 0);

	@Mock
	IRule movingRuleWhenCollided;

	@Mock
	IRule movingRuleWhenCollidedWithItself;

	@Mock
	IRule movingRuleWhenFree;

	private StaticEntity wall;

	// @Test
	// public void move_ThereIsAWall_ApplyMovingRule() throws Exception {
	// Map<Entity, IRule> map = wallNextToSnake();
	// Snake newSnake = newSnake(ORIGIN, Direction.RIGHT);
	// AMovingRules rules = new MapMovingRules(new MovingRules(newSnake),
	// movingRuleWhenCollidedWithItself, map, newSnake);
	//
	// rules.act(1f);
	//
	// verify(movingRuleWhenCollided).fireEvent(wall);
	// }
	//
	// @Test
	// public void move_ThereIsNotAWall_Moves() throws Exception {
	// Map<Entity, IRule> map = wallAwayFromSnake();
	// AMovingRules rules = new MapMovingRules(new MovingRules(),
	// movingRuleWhenCollidedWithItself,
	// map);
	//
	// Snake snake = rules.fireEvent();
	//
	// assertPoints(new Vector2(1, 0), snake.getPosition());
	// }
	//
	// @Test
	// public void move_ThereIsTailInTheWay_DoesntMove() throws Exception {
	// AMovingRules rules = new MapMovingRules(movingRuleWhenFree,
	// movingRuleWhenCollidedWithItself,
	// Collections.<Entity, IRule> emptyMap());
	//
	// Snake snake = rules.fireEvent();
	//
	// assertPoints(new Vector2(5, 0), snake.getPosition());
	// }

	// FIXME: Fix those "mirrorMap" tests.
	// @Test
	// public void move_RightLastXPosition_GoesToFirstXPosition() throws
	// Exception {
	// AMovingRules rules = new MapMovingRules(new MovingRules(), 1, 1);
	//
	// Snake snake = rules.update(newSnake(1, 0, Direction.RIGHT));
	//
	// assertPoints(new Vector2(0, 0), snake.getPosition());
	// }
	//
	// @Test
	// public void move_DownLastYPosition_GoesToFirstYPosition() throws
	// Exception {
	// AMovingRules rules = new MapMovingRules(new MovingRules(), 1, 1);
	//
	// Snake snake = rules.update(newSnake(0, 1, Direction.DOWN));
	//
	// assertPoints(new Vector2(0, 0), snake.getPosition());
	// }
	//
	// @Test
	// public void move_UpLastYPosition_GoesToFirstYPosition() throws Exception
	// {
	// AMovingRules rules = new MapMovingRules(new MovingRules(), 1, 1);
	//
	// Snake snake = rules.update(newSnake(0, 0, Direction.UP));
	//
	// assertPoints(new Vector2(0, 1), snake.getPosition());
	// }
	//
	// @Test
	// public void move_LeftLastXPosition_GoesToFirstXPosition() throws
	// Exception {
	// AMovingRules rules = new MirrorMapMovingRules(new MovingRules(), 1, 1);
	//
	// Snake snake = rules.update(newSnake(0, 0, Direction.LEFT));
	//
	// assertPoints(new Vector2(1, 0), snake.getPosition());
	// }

	private Snake snakeSize5() {
		Array<Piece> pieces = new Array<Piece>();
		pieces.add(newPiece(new Vector2(5, 0)));
		pieces.add(newPiece(new Vector2(4, 0)));
		pieces.add(newPiece(new Vector2(4, 1)));
		pieces.add(newPiece(new Vector2(5, 1)));
		pieces.add(newPiece(new Vector2(6, 1)));
		return new Snake(pieces, texture, new Vector2(32, 0));
	}

	private Map<Entity, IRule> wallAwayFromSnake() {
		HashMap<Entity, IRule> map = new HashMap<Entity, IRule>();
		map.put(new StaticEntity(texture, new Vector2(10, 10)), movingRuleWhenCollided);
		return map;
	}

	private Map<Entity, IRule> wallNextToSnake() {
		HashMap<Entity, IRule> map = new HashMap<Entity, IRule>();
		wall = new StaticEntity(texture, new Vector2(1, 0));
		map.put(wall, movingRuleWhenCollided);
		return map;
	}
}

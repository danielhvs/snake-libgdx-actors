package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class MapMovingRulesTest extends BaseTest {
	private static final Vector2 ORIGIN = new Vector2(0, 0);

	@Test
	public void move_ThereIsAWall_DoesntMove() throws Exception {
		List<Vector2> map = wallNextToSnake();
		AMovingRules rules = new MapMovingRules(new MovingRules(), map);

		Snake snake = rules.update(newSnake(ORIGIN, Direction.RIGHT));

		assertEquals(ORIGIN, snake.getPosition());
	}

	@Test
	public void move_ThereIsNotAWall_Moves() throws Exception {
		List<Vector2> map = wallAwayFromSnake();
		AMovingRules rules = new MapMovingRules(new MovingRules(), map);

		Snake snake = rules.update(newSnake(ORIGIN, Direction.RIGHT));

		assertEquals(new Vector2(1, 0), snake.getPosition());
	}

	private List<Vector2> wallAwayFromSnake() {
		return Arrays.asList(new Vector2(10, 10));
	}

	private List<Vector2> wallNextToSnake() {
		return Arrays.asList(new Vector2(1, 0));
	}
}

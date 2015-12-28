package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class SnakeControllerTest extends BaseTest {
	@Test
	public void right() throws Exception {
		Snake snake = whenSnakeIs(Direction.UP.getDirection());
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);

		controller.right(snake, 0f);

		assertEquals(Direction.UP.getDirection().angle() - AMovingRules.DEGREES_PER_MOVEMENT, snake.getVelocity().angle(), .1f);
	}

	@Test
	public void left() throws Exception {
		Snake snake = whenSnakeIs(Direction.UP.getDirection());
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);

		controller.left(snake, 0f);

		assertEquals(Direction.UP.getDirection().angle() + AMovingRules.DEGREES_PER_MOVEMENT, snake.getVelocity().angle(), .1f);
	}

	private Snake whenSnakeIs(Vector2 velocity) {
		return newSnake(0, 0, velocity.cpy());
	}

	private enum Direction {
		UP(new Vector2(0, 1)),
		DOWN(new Vector2(0, -1)),
		LEFT(new Vector2(-1, 0)),
		RIGHT(new Vector2(1, 0));

		private Vector2 direction;

		private Direction(Vector2 direction) {
			this.direction = direction;
		}

		public Vector2 getDirection() {
			return direction;
		}

	}

}

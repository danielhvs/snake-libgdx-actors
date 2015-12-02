package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SnakeControllerTest extends BaseTest {
	private SnakeController controller;

	@Before
	public void setup() {
		controller = new SnakeController(new MovingRules());
	}

	@Test
	public void up() throws Exception {
		assertEquals(Direction.UP, directionOf(controller.up(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.DOWN, directionOf(controller.up(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.UP, directionOf(controller.up(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.UP, directionOf(controller.up(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void down() throws Exception {
		assertEquals(Direction.UP, directionOf(controller.down(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.DOWN, directionOf(controller.down(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.DOWN, directionOf(controller.down(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.DOWN, directionOf(controller.down(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void right() throws Exception {
		assertEquals(Direction.RIGHT, directionOf(controller.right(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.RIGHT, directionOf(controller.right(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.LEFT, directionOf(controller.right(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.RIGHT, directionOf(controller.right(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void left() throws Exception {
		assertEquals(Direction.LEFT, directionOf(controller.left(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.LEFT, directionOf(controller.left(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.LEFT, directionOf(controller.left(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.RIGHT, directionOf(controller.left(whenSnakeIs(Direction.RIGHT))));
	}

	private Direction directionOf(Snake snake) {
		return snake.getDirection();
	}

	private Snake whenSnakeIs(Direction direction) {
		return newSnake(0, 0, direction);
	}

}

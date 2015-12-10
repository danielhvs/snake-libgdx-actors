package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnakeControllerTest extends BaseTest {
	private SnakeController controller;

	@Test
	public void up() throws Exception {
		assertEquals(Direction.UP, directionOf(controllerUp(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.DOWN, directionOf(controllerUp(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.UP, directionOf(controllerUp(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.UP, directionOf(controllerUp(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void down() throws Exception {
		assertEquals(Direction.UP, directionOf(controllerDown(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.DOWN, directionOf(controllerDown(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.DOWN, directionOf(controllerDown(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.DOWN, directionOf(controllerDown(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void right() throws Exception {
		assertEquals(Direction.RIGHT, directionOf(controllerRight(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.RIGHT, directionOf(controllerRight(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.LEFT, directionOf(controllerRight(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.RIGHT, directionOf(controllerRight(whenSnakeIs(Direction.RIGHT))));
	}

	@Test
	public void left() throws Exception {
		assertEquals(Direction.LEFT, directionOf(controllerLeft(whenSnakeIs(Direction.UP))));
		assertEquals(Direction.LEFT, directionOf(controllerLeft(whenSnakeIs(Direction.DOWN))));
		assertEquals(Direction.LEFT, directionOf(controllerLeft(whenSnakeIs(Direction.LEFT))));
		assertEquals(Direction.RIGHT, directionOf(controllerLeft(whenSnakeIs(Direction.RIGHT))));
	}

	private Direction directionOf(Snake snake) {
		return snake.getDirection();
	}

	private Snake whenSnakeIs(Direction direction) {
		return newSnake(0, 0, direction);
	}

	private Snake controllerUp(Snake snake) {
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);
		return controller.up(snake);
	}

	private Snake controllerDown(Snake snake) {
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);
		return controller.down(snake);
	}

	private Snake controllerRight(Snake snake) {
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);
		return controller.right(snake);
	}

	private Snake controllerLeft(Snake snake) {
		SnakeController controller = new SnakeController(new MovingRules(snake), snake);
		return controller.left(snake);
	}

}

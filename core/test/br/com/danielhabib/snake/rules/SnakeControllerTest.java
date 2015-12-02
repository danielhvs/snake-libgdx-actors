package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class SnakeControllerTest extends BaseTest {
	@TestWith({ "LEFT", "RIGHT" })
	public void up_SnakeCanGoUp_TurnsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.up(snake);

		assertEquals(Direction.UP, result.getDirection());
	}

	@TestWith({ "UP", "DOWN" })
	public void up_SnakeCannotGoUp_MaintainsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.up(snake);

		assertEquals(direction, result.getDirection());
	}

	@TestWith({ "LEFT", "RIGHT" })
	public void down_SnakeCanGoDown_TurnsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.down(snake);

		assertEquals(Direction.DOWN, result.getDirection());
	}

	@TestWith({ "UP", "DOWN" })
	public void down_SnakeCannotGoDown_MaintainsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.down(snake);

		assertEquals(direction, result.getDirection());
	}

	@TestWith({ "LEFT", "RIGHT" })
	public void left_SnakeCannotGoLeft_MaintainsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.left(snake);

		assertEquals(direction, result.getDirection());
	}

	@TestWith({ "UP", "DOWN" })
	public void left_SnakeCanGoLeft_TurnsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.left(snake);

		assertEquals(Direction.LEFT, result.getDirection());
	}

	@TestWith({ "UP", "DOWN" })
	public void right_SnakeCanGoRight_TurnsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.right(snake);

		assertEquals(Direction.RIGHT, result.getDirection());
	}

	@TestWith({ "LEFT", "RIGHT" })
	public void right_SnakeCannotGoRight_MaintainsDirection(Direction direction) throws Exception {
		SnakeController controller = new SnakeController(new MovingRules());

		Snake snake = newSnake(0, 0, direction);
		Snake result = controller.right(snake);

		assertEquals(direction, result.getDirection());
	}
}

package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class RestrictedMovingRulesTest extends BaseTest {

	@Test
	public void move_ThereIsTailInTheWay_DoesntMove() throws Exception {
		AMovingRules rules = new RestrictedMovingRules();

		Snake snake = snakeSize5();

		for (int i = 0; i < 3; i++) {
			snake = rules.turnLeft(snake);
			snake = rules.update(snake);
		}

		assertEquals(new Vector2(4, -1), snake.getPosition());
	}

	private Snake snakeSize5() {
		return newSnake(5, 0).addTail(4, 0).addTail(3, 0).addTail(2, 0).addTail(1, 0);
	}
}

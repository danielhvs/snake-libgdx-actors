package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PoisonedFruitRuleTest extends BaseTest {
	@Test
	public void update_ThereIsAPoisonedFruit_RemovesSnakeTail() throws Exception {
		IRule rules = null;// newFruitRule(new Vector2(0, 0), new
							// PoisonedFruitRule());

		Snake snake = rules.fireEvent();

		assertEquals(newSnake(0, 0), snake);

	}
}

package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PoisonedFruitRuleTest extends BaseTest {
	@Test
	public void update_ThereIsAPoisonedFruit_RemovesSnakeTail() throws Exception {
		IRule rules = newFruitRule(new Vector2(0, 0), new PoisonedFruitRule());

		Snake snake = rules.update(newSnake(0, 0).addTail());

		assertEquals(newSnake(0, 0), snake);

	}
}

package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class FruitRuleTest extends BaseTest {

	@Test
	public void update_ThereIsNoFruit_ReturnsSameSnake() throws Exception {
		FruitRule rule = new FruitRule(new Vector2(1, 0));

		Snake newSnake = newSnake(0, 0);
		Snake updatedSnake = rule.update(newSnake);

		assertSame(newSnake, updatedSnake);
	}

	@Test
	public void update_ThereIsAFruit_ConsumesIt() throws Exception {
		FruitRule rule = new FruitRule(new Vector2(0, 0));

		Snake snakeAte = rule.update(newSnake(0, 0));
		Snake snakeDidntAte = rule.update(newSnake(0, 0));

		assertEquals(2, snakeAte.getPieces().size());
		assertEquals(1, snakeDidntAte.getPieces().size());
	}
}

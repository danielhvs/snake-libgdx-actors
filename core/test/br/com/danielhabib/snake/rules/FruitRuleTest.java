package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class FruitRuleTest extends BaseTest {

	@Test
	public void update_ThereIsNoFruit_ReturnsSameSnake() throws Exception {
		AFruitRule rule = newFruitRule(new Vector2(1, 0), new FruitRule());

		Snake newSnake = newSnake(0, 0);
		Snake updatedSnake = null;// ; = rule.update(newSnake);

		assertSame(newSnake, updatedSnake);
	}

	@Test
	public void update_ThereIsAFruit_ConsumesIt() throws Exception {
		AFruitRule rule = newFruitRule(new Vector2(0, 0), new FruitRule());

		Snake snakeAte = null;// rule.update(newSnake(0, 0));
		Snake snakeDidntAte = null;// rule.update(newSnake(0, 0));

		assertEquals(2, snakeAte.copyPieces().size());
		assertEquals(1, snakeDidntAte.copyPieces().size());
	}
}

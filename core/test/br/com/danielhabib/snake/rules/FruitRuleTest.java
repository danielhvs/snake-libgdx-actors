package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public class FruitRuleTest extends IRuleTest {

	@Override
	void assertEvent(SnakeEvent capture) throws Exception {
		assertEquals(SnakeEvent.Type.addTail, capture.getType());
		assertEquals(actor, capture.getSource());
	}

	@Override
	IRule newInstanceOfIRule() {
		return new FruitRule(stage);
	}

	// FIXME: Migrate to AFruitRuleTest
	// @Test
	// public void update_ThereIsNoFruit_ReturnsSameSnake() throws Exception {
	// AFruitRule rule = newFruitRule(new Vector2(1, 0), new FruitRule());
	//
	// Snake newSnake = newSnake(0, 0);
	// Snake updatedSnake = null;// ; = rule.update(newSnake);
	//
	// assertSame(newSnake, updatedSnake);
	// }
	//
	// @Test
	// public void update_ThereIsAFruit_ConsumesIt() throws Exception {
	// AFruitRule rule = newFruitRule(new Vector2(0, 0), new FruitRule());
	//
	// Snake snakeAte = null;// rule.update(newSnake(0, 0));
	// Snake snakeDidntAte = null;// rule.update(newSnake(0, 0));
	//
	// assertEquals(2, snakeAte.copyPieces().size());
	// assertEquals(1, snakeDidntAte.copyPieces().size());
	// }
}

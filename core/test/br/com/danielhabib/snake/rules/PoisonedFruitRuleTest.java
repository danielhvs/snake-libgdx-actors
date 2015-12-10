package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

public class PoisonedFruitRuleTest extends IRuleTest {

	@Override
	IRule newInstanceOfIRule() {
		return new PoisonedFruitRule(stage);
	}

	@Override
	void assertEvent(SnakeEvent capture) throws Exception {
		assertEquals(SnakeEvent.Type.removeTail, capture.getType());
		assertEquals(actor, capture.getSource());
	}
}

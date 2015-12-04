package br.com.danielhabib.snake.rules;

public class PoisonedFruitRule implements IRule {

	@Override
	public Snake update(Snake snake) {
		return snake.removeTail();
	}

}

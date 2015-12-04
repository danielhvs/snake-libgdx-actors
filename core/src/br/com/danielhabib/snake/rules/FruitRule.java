package br.com.danielhabib.snake.rules;

public class FruitRule implements IRule {

	@Override
	public Snake update(Snake snake) {
		return snake.addTail();
	}

}

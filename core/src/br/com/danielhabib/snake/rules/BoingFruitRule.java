package br.com.danielhabib.snake.rules;

public class BoingFruitRule implements IRule {

	@Override
	public Snake update(Snake snake) {
		return snake.revert();
	}

}

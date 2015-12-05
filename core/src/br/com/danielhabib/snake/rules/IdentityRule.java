package br.com.danielhabib.snake.rules;

public class IdentityRule implements IRule {

	@Override
	public Snake update(Snake snake) {
		return snake;
	}

}

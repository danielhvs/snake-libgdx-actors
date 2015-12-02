package br.com.danielhabib.snake.rules;

public class MovingRules extends AMovingRules {

	@Override
	public Snake update(Snake snake) {
		return snake.move();
	}

}

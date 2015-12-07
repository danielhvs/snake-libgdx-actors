package br.com.danielhabib.snake.rules;

public class MovingRules extends AMovingRules {

	public MovingRules(Snake snake) {
		super(snake);
	}

	@Override
	public void act(float delta) {
		update(snake);
	}

	public Snake update(Snake snake) {
		return snake.move();
	}

}

package br.com.danielhabib.snake.rules;

import java.util.List;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class RandomMovingRules extends MapMovingRules {

	public RandomMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf,
			List<EventFirerEntity> wallsList, Snake snake, int lastX, int lastY) {
		super(ruleWhenFree, ruleWhenCollidedWithItSelf, wallsList, snake, lastX, lastY);
	}

	@Override
	public Snake turnLeft(Snake snake) {
		return turnRight(snake);
	}

	@Override
	public Snake turnRight(Snake snake) {
		double random = Math.random();
		if (random < 0.3) {
			return super.turnLeft(snake);
		} else if (random > 0.8) {
			return super.turnRight(snake);
		} else {
			return snake;
		}
	}

}

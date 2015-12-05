package br.com.danielhabib.snake.rules;

public class RandomMovingRules extends MapMovingRules {

	public RandomMovingRules(IRule ruleWhenFree, IRule ruleWhenCollidedWithItSelf) {
		super(ruleWhenFree, ruleWhenCollidedWithItSelf);
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

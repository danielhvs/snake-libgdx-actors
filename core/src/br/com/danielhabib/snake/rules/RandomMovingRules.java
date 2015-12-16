package br.com.danielhabib.snake.rules;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class RandomMovingRules extends MapMovingRules {


	public RandomMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, List<Actor> worldMap,
			List<EventFirerEntity> wallsList, Snake snake, int lastX, int lastY) {
		super(ruleWhenFree, ruleWhenCollidedWithItSelf, worldMap, wallsList, snake, lastX, lastY);
	}

	@Override
	public Snake turnLeft(Snake snake, float delta) {
		return turnRight(snake, delta);
	}

	@Override
	public Snake turnRight(Snake snake, float delta) {
		double random = Math.random();
		if (random < 0.3) {
			return super.turnLeft(snake, delta);
		} else if (random > 0.8) {
			return super.turnRight(snake, delta);
		} else {
			return snake;
		}
	}

}

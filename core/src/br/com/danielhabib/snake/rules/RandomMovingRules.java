package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.entity.EventFirerEntity;

public class RandomMovingRules extends MapMovingRules {

	public RandomMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, Array<Actor> worldMap,
			Array<EventFirerEntity> wallsList, Snake snake, int lastX, int lastY) {
		super(ruleWhenFree, ruleWhenCollidedWithItSelf, worldMap, wallsList, snake, lastX, lastY);
	}

	@Override
	public void turnLeft(Snake snake, float delta) {
		turnRight(snake, delta);
	}

	@Override
	public void turnRight(Snake snake, float delta) {
		double random = Math.random();
		if (random < 0.3) {
			super.turnLeft(snake, delta);
		} else if (random > 0.8) {
			super.turnRight(snake, delta);
		}
	}

}

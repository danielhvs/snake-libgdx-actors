package br.com.danielhabib.snake.rules;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class MapMovingRules extends AMovingRules {

	private AMovingRules movingRules;
	private List<Vector2> map;

	public MapMovingRules(AMovingRules movingRules, List<Vector2> map) {
		this.movingRules = movingRules;
		this.map = map;
	}

	@Override
	public Snake update(Snake snake) {
		Snake nextPositionSnake = movingRules.update(snake);
		return map.contains(nextPositionSnake.getPosition()) ? snake : nextPositionSnake;
	}

}

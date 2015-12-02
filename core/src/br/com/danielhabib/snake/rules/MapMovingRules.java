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

	// FIXME: Texture is needed...
	@Override
	public Snake update(Snake snake) {
		Snake nextPositionSnake = movingRules.update(snake);
		for (Vector2 tile : map) {
			if (tile.epsilonEquals(nextPositionSnake.getPosition(), 0.01f)) {
				return snake;
			}
		}
		return nextPositionSnake;
	}

}

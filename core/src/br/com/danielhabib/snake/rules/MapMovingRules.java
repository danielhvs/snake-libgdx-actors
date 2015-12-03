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
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (Vector2 tile : map) {
			if (tile.epsilonEquals(nextPositionSnake, 0.01f)) {
				return snake;
			}
		}
		return snake.move();
	}

}

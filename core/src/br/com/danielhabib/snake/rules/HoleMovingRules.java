package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class HoleMovingRules extends AMovingRules {

	private Array<WormHole> holes;

	public HoleMovingRules(Array<WormHole> hole, Snake snake) {
		super(snake);
		this.holes = hole;
	}

	public HoleMovingRules(WormHole hole, Snake snake) {
		super(snake);
		this.holes = Array.with(hole);
	}

	@Override
	public void act(float delta) {
		Vector2 nextPositionSnake = snake.getNextPosition(delta);
		Rectangle snakeBounds = snake.getBounds().setPosition(nextPositionSnake.x, nextPositionSnake.y);
		boolean colided = false;
		for (WormHole hole : holes) {
			Vector2 initiaLPosition = hole.getInitialPoint().getPosition();
			Rectangle holeBounds = new Rectangle(initiaLPosition.x, initiaLPosition.y, 32, 32);
			if (snakeBounds.overlaps(holeBounds)) {
				snake.move(hole.getFinalPoint().getPosition());
				colided = true;
				break;
			}
		}
		if (!colided) {
			snake.move(delta);
		}
	}

}

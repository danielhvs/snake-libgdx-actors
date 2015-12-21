package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HoleMovingRules extends AMovingRules {

	private WormHole hole;

	public HoleMovingRules(WormHole hole, Snake snake) {
		super(snake);
		this.hole = hole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((hole == null) ? 0 : hole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HoleMovingRules other = (HoleMovingRules) obj;
		if (hole == null) {
			if (other.hole != null) {
				return false;
			}
		} else if (!hole.equals(other.hole)) {
			return false;
		}
		return true;
	}

	@Override
	public void act(float delta) {
		Vector2 nextPositionSnake = snake.getNextPosition(delta);
		Rectangle snakeBounds = snake.getBounds().setPosition(nextPositionSnake.x, nextPositionSnake.y);
		Rectangle holeBounds = new Rectangle(hole.getInitialPoint().x, hole.getInitialPoint().y, 32, 32);
		if (snakeBounds.overlaps(holeBounds)) {
			snake.move(hole.getFinalPoint());
		} else {
			snake.move(delta);
		}
	}

	public WormHole getHole() {
		return hole;
	}

}

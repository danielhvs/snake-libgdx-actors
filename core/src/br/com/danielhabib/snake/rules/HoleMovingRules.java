package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.g2d.Batch;

public class HoleMovingRules extends AMovingRules {

	private WormHole hole;

	public HoleMovingRules(WormHole hole, Snake snake) {
		super(snake);
		this.hole = hole;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		hole.act(0);
		hole.draw(batch, parentAlpha);
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
		hole.act(delta);
		if (snake.getPosition().epsilonEquals(hole.getInitialPoint(), 0.01f)) {
			snake.move(hole.getFinalPoint());
		} else {
			snake.move();
		}
	}

	public WormHole getHole() {
		return hole;
	}

}

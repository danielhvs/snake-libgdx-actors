package br.com.danielhabib.snake.rules;

public class HoleMovingRules extends AMovingRules {

	private WormHole hole;

	public HoleMovingRules(WormHole hole) {
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
	public Snake update(Snake snake) {
		return snake.getPosition().epsilonEquals(hole.getInitialPoint(), 0.01f) ? snake.move(hole.getFinalPoint())
				: snake.move();
	}

	public WormHole getHole() {
		return hole;
	}

}

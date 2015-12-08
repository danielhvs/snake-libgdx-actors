package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WormHole extends Actor {

	private Entity initialPoint;
	private Entity finalPoint;

	public WormHole(Entity initialPoint, Entity finalPoint) {
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
	}

	public Vector2 getInitialPoint() {
		return initialPoint.getPosition();
	}

	public Vector2 getFinalPoint() {
		return finalPoint.getPosition();
	}

	@Override
	public String toString() {
		return initialPoint + " --> " + finalPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finalPoint == null) ? 0 : finalPoint.hashCode());
		result = prime * result + ((initialPoint == null) ? 0 : initialPoint.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WormHole other = (WormHole) obj;
		if (finalPoint == null) {
			if (other.finalPoint != null) {
				return false;
			}
		} else if (!finalPoint.equals(other.finalPoint)) {
			return false;
		}
		if (initialPoint == null) {
			if (other.initialPoint != null) {
				return false;
			}
		} else if (!initialPoint.equals(other.initialPoint)) {
			return false;
		}
		return true;
	}

	@Override
	public void act(float delta) {
		initialPoint.act(delta);
		finalPoint.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		initialPoint.draw(batch, parentAlpha);
		finalPoint.draw(batch, parentAlpha);
	}

}

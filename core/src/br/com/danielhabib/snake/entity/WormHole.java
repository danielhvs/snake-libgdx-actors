package br.com.danielhabib.snake.entity;

public class WormHole {

	private Entity initialPoint;
	private Entity finalPoint;

	public WormHole(Entity initialPoint, Entity finalPoint) {
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
	}

	public Entity getInitialPoint() {
		return initialPoint;
	}

	public Entity getFinalPoint() {
		return finalPoint;
	}

}

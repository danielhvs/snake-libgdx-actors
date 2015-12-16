package br.com.danielhabib.snake.rules;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Piece extends Entity {
	private float radius;

	public Piece(Vector2 pos, Texture texture) {
		super(texture, pos);
	}

	public Piece(float radius, float degrees, Texture texture) {
		super(texture, new Vector2((float) (radius * Math.cos(Math.toRadians(degrees))), (float) (radius * Math.sin(Math.toRadians(degrees)))));
		setRotation(degrees);
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Piece [" + getPosition();
	}

	private float degrees = 0f;

	@Override
	public void updateAct() {
		degrees += 10f;
		setRotation(degrees);
	}

	public Piece move(float radiusOffset) {
		this.radius += radiusOffset;
		setX((float) (radius * Math.cos(Math.toRadians(getRotation()))));
		setY((float) (radius * Math.sin(Math.toRadians(getRotation()))));
		return this;
	}

}
package br.com.danielhabib.snake.rules;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Piece extends Entity {
	private float radius;

	public Piece(Vector2 pos, Texture texture) {
		super(texture, pos);
		setRotation(Math.atan2(pos.y, pos.x));
		this.radius = (float) Math.sqrt(Math.pow(pos.x, 2) + Math.pow(pos.y, 2));
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

	@Override
	public void updateAct() {
	}

	public Piece move(float radiusOffset) {
		this.radius += radiusOffset;
		setX((float) (radius * Math.cos(Math.toRadians(getRotation()))));
		setY((float) (radius * Math.sin(Math.toRadians(getRotation()))));
		return this;
	}

}
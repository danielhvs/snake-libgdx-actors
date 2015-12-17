package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Piece extends Entity {
	public Piece(Vector2 pos, Texture texture) {
		super(texture, pos);
	}

	@Override
	public String toString() {
		return "Piece [" + getPosition();
	}

	private float degrees = 0f;

	@Override
	public void updateAct() {
		degrees += 10f;
		// setRotation(degrees);
	}

}
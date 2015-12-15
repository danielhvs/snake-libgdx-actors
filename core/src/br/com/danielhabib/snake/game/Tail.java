package br.com.danielhabib.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Piece;

public class Tail extends Piece {

	public Tail(Vector2 pos, Direction direction, Texture texture) {
		super(pos, direction, texture);
	}

	private double x = 0.0f;
	@Override
	public void updateAct() {
		x += Math.PI / 40;
		float initialRotation = direction.getRotation();
		float degreesOffset = (float) (15 * Math.sin(x));
		setRotation(initialRotation + degreesOffset);
	}


}

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Piece;

public class Head extends Piece {

	public Head(Vector2 pos, Direction direction, Texture texture) {
		super(pos, direction, texture);
	}

	@Override
	public void updateAct() {
		setRotation(direction.getRotation());
	}

}

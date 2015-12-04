package br.com.danielhabib.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.Piece;

public class Tail extends Piece {


	public Tail(Vector2 pos, Direction direction, Texture texture) {
		super(pos, direction, texture);
	}

	private double x = 0.0f;
	@Override
	public void update() {
		sprite.setPosition(pos.x * Entity.SIZE, pos.y * Entity.SIZE);
		sprite.setRotation(direction.getRotation());
		x += Math.PI / 40;
		sprite.rotate((float) (15 * Math.sin(x)));
	}


}

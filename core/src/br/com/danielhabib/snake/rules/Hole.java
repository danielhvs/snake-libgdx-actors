package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Hole extends Entity {

	public Hole(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	public void update() {
		super.update();
		sprite.rotate(100);
	}

}

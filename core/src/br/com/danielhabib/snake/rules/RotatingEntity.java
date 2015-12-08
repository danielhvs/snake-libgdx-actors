package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class RotatingEntity extends Entity {

	private float degreesPerUpdate;

	public RotatingEntity(Texture texture, Vector2 pos, float degreesPerUpdate) {
		super(texture, pos);
		this.degreesPerUpdate = degreesPerUpdate;
	}

	@Override
	public void update() {
		sprite.rotate(degreesPerUpdate);
	}

}

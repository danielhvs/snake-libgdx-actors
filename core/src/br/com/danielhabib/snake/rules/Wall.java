package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Entity {

	public Wall(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	public void update() {
		double random = Math.random();
		sprite.rotate(random < 0.5 ? 2.0f : -2.0f);
		sprite.setPosition(pos.x * SIZE, pos.y * SIZE);
	}

}

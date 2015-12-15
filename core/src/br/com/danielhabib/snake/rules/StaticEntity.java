package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class StaticEntity extends Entity {

	public StaticEntity(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	public void updateAct() {
	}

}

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.rules.SnakeEvent;

public class SpeedFruit extends EventFirerEntity {
	public SpeedFruit(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	protected void updateAct() {
	}

	@Override
	protected Event getEvent() {
		return new SnakeEvent(this, SnakeEvent.Type.speed);
	}

}

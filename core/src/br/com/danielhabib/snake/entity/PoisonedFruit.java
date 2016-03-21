package br.com.danielhabib.snake.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public class PoisonedFruit extends EventFirerEntity {

	public PoisonedFruit(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	protected void updateAct() {
	}

	@Override
	protected Event getEvent() {
		return new SnakeEvent(this, SnakeEvent.Type.removeTail);
	}

}

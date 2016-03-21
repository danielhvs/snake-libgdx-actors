package br.com.danielhabib.snake.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.listeners.SnakeEvent;
import br.com.danielhabib.snake.listeners.SnakeEvent.Type;

public class BoingWall extends EventFirerEntity {

	public BoingWall(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	protected void updateAct() {
	}

	@Override
	protected Event getEvent() {
		return new SnakeEvent(this, SnakeEvent.Type.revert);
	}



}

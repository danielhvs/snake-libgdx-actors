package br.com.danielhabib.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.NOPEventFirerEntity;

public abstract class EventFirerEntity extends Entity {

	public static final EventFirerEntity NOP = new NOPEventFirerEntity();
	
	public EventFirerEntity() {
		// NOP.
	}

	public EventFirerEntity(Texture texture, Vector2 pos) {
		super(texture, pos);
	}

	@Override
	protected abstract void updateAct();

	protected abstract Event getEvent();

	public void fireEvent() {
		for (Actor actor : getStage().getActors()) {
			actor.fire(getEvent());
		}
	}


}

package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class NOPEventFirerEntity extends EventFirerEntity {

	@Override
	protected void updateAct() {
	}

	@Override
	protected Event getEvent() {
		return null;
	}

}

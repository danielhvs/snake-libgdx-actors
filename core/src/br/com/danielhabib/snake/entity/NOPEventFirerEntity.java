package br.com.danielhabib.snake.entity;

import com.badlogic.gdx.scenes.scene2d.Event;

public class NOPEventFirerEntity extends EventFirerEntity {

	@Override
	protected void updateAct() {
	}

	@Override
	protected Event getEvent() {
		return null;
	}

}

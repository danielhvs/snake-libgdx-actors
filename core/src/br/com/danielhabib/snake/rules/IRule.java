package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class IRule {

	protected Stage stage;

	public IRule() {
	}

	public IRule(Stage stage) {
		this.stage = stage;
	}

	public abstract void fireEvent(Actor source);
}

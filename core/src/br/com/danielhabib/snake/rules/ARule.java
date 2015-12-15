package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class ARule implements IRule {

	protected Stage stage;

	public ARule() {
	}

	public ARule(Stage stage) {
		this.stage = stage;
	}

	@Override
	public abstract void fireEvent(Actor source);

}

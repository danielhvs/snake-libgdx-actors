package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class IRule extends Action {

	protected Stage stage;

	public IRule() {
	}

	public IRule(Stage stage) {
		this.stage = stage;
	}

	@Override
	public boolean act(float delta) {
		fireEvent(delta);
		return true;
	}

	protected abstract void fireEvent(float delta);
}

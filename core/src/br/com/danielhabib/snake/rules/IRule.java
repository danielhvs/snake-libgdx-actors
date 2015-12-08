package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class IRule extends Action {

	public IRule() {
	}

	public IRule(Actor actor) {
		setTarget(actor);
	}

	@Override
	public boolean act(float delta) {
		return fireEvent(delta);
	}

	protected abstract boolean fireEvent(float delta);
}

package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface IRule {
	public abstract void fireEvent(Actor source);
}

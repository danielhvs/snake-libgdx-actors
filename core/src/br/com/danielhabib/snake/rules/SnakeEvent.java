package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Event;

public class SnakeEvent extends Event {
	private Type type;

	public SnakeEvent(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	static public enum Type {
		revert, addTail, removeTail,
	}
}

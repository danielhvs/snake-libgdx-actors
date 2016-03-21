package br.com.danielhabib.snake.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

public class SnakeEvent extends Event {
	private Type type;
	private Actor source;

	public SnakeEvent(Actor source, Type type) {
		this.source = source;
		this.type = type;
	}

	// May be null!
	public Actor getSource() {
		return source;
	}

	public Type getType() {
		return type;
	}

	static public enum Type {
		revert, addTail, removeTail, colided, speed, died
	}
}

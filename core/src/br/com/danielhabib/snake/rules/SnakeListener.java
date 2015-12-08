package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class SnakeListener implements EventListener {

	@Override
	public boolean handle(Event event) {
		if (!(event instanceof SnakeEvent)) {
			return false;
		}
		SnakeEvent snakeEvent = (SnakeEvent) event;

		switch (snakeEvent.getType()) {
		case revert:
			return revert(event);
		case addTail:
			return addTail(event);
		case removeTail:
			return removeTail(event);
		}

		return false;
	}

	public boolean revert(Event event) {
		return false;
	}

	public boolean addTail(Event event) {
		return false;
	}

	public boolean removeTail(Event event) {
		return false;
	}

}

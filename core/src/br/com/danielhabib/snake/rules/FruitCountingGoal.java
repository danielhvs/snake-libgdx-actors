package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

import br.com.danielhabib.snake.listeners.SnakeEvent;
import br.com.danielhabib.snake.listeners.SnakeListener;

public class FruitCountingGoal extends Actor {

	private int limit;
	private int count;
	private Actor[] actors;

	public FruitCountingGoal() {
	}

	public FruitCountingGoal(int limit, Actor... actors) {
		this.limit = limit;
		this.actors = actors;
	}

	public void addListeners() {
		addListener(new SnakeListener() {
			@Override
			public boolean addTail(Actor source, Event event) {
				if (++count >= limit) {
					for (Actor actor : actors) {
						actor.fire(new SnakeEvent(source, SnakeEvent.Type.win));
					}
				}
				return super.addTail(source, event);
			}
		});
	}

}

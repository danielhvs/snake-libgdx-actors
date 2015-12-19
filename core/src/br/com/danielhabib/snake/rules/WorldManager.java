package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.game.EventFirerEntity;

public interface WorldManager {
	public Array<Actor> getMap();

	public void put(EventFirerEntity entity);
}

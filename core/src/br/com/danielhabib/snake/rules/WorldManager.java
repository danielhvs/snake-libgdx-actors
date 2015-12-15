package br.com.danielhabib.snake.rules;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.game.EventFirerEntity;

public interface WorldManager {
	public List<Actor> getMap();

	public void put(EventFirerEntity entity);
}

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class MapGenerator extends Actor {
	private Array<Actor> actors;
	private Array<Actor> worldMap;
	private Array<Actor> pieces;

	public MapGenerator(Array<Actor> actors, Array<Actor> worldMap, Array<Actor> pieces) {
		this.actors = actors;
		this.worldMap = worldMap;
		this.pieces = pieces;
	}

	@Override
	public void act(float delta) {
		int factor = 1;
		for (Actor actor : actors) {
			worldMap.add(actor);
			actor.addAction(Actions.sequence(
					Actions.moveTo(0, 0),
					Actions.parallel(
							Actions.moveTo(actor.getX(), actor.getY(), .5f),
							Actions.rotateBy(factor * 360f, .5f)
					)
					));
			getStage().addActor(actor);
		}
		for (Actor piece : pieces) {
			factor *= -1;
			piece.addAction(Actions.sequence(
					Actions.fadeOut(0),
					Actions.delay(.75f),
					Actions.moveTo(piece.getX(), piece.getY()),
					Actions.parallel(
							Actions.rotateBy(factor * 720f, .5f),
							Actions.fadeIn(1f)
							),
					Actions.scaleTo(.025f, .025f, .125f),
					Actions.scaleTo(1f, 1f, .125f),
					Actions.delay(.125f)
					));
			getStage().addActor(piece);
		}

		remove();
	}
}

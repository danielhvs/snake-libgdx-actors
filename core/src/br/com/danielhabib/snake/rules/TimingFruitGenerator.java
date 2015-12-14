package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class TimingFruitGenerator extends Actor {

	private float timeout;
	private float time;
	private List<Actor> map;
	private Map<Entity, IRule> fruits;

	public TimingFruitGenerator(Map<Entity, IRule> fruits, List<Actor> map, float timeout) {
		this.fruits = fruits;
		this.map = map;
		this.timeout = timeout;
	}

	@Override
	public void act(float delta) {
		// FIXME: generate only after animation...
		if (timeout(delta)) {
			boolean generated = false, full = false;
			while (!generated && !full) {
				// FIXME: same x and y position for now...
				int pos = (int) (Math.random() * 20);
				Vector2 candidate = new Vector2(pos, pos);
				Array<Vector2> allActorPositions = new Array<Vector2>();
				for (Actor actor : map) {
					Vector2 position = new Vector2(actor.getX(), actor.getY());
					allActorPositions.add(position);
				}
				if (!allActorPositions.contains(candidate, false)) {
					generate(candidate);
					generated = true;
				} else {
					full = true;
				}
			}
		}
	}

	private void generate(Vector2 candidate) {
		StaticEntity newFruit = new StaticEntity(new Texture(Gdx.files.internal("apple.png")), candidate);
		fruits.put(newFruit, new FruitRule(getStage()));
		map.add(newFruit);
		getStage().addActor(newFruit);
		newFruit.addAction(Actions.moveTo(0, 0));
		newFruit.addAction(Actions.moveTo(candidate.x, candidate.y, 1f, Interpolation.swing));
	}

	private boolean timeout(float delta) {
		time += delta;
		if (time >= timeout) {
			time = 0;
			return true;
		} else {
			return false;
		}
	}

}

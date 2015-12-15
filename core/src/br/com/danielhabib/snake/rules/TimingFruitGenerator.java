package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.game.EventFirerEntity;
import br.com.danielhabib.snake.game.Fruit;

public class TimingFruitGenerator extends Actor {

	private float timeout;
	private float time;
	private AFruitRule fruitRule;

	public TimingFruitGenerator(AFruitRule fruitRule, float timeout) {
		this.fruitRule = fruitRule;
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
				for (Actor actor : fruitRule.getMap()) {
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
		// FIXME: configure.
		EventFirerEntity newFruit = new Fruit(new Texture(Gdx.files.internal("apple.png")), candidate);
		fruitRule.put(newFruit);
		getStage().addActor(newFruit);
		newFruit.addAction(Actions.moveTo(0, 0));
		newFruit.addAction(Actions.parallel(
				Actions.moveTo(candidate.x, candidate.y, 0.5f),
				Actions.rotateTo(360f, .75f)));
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

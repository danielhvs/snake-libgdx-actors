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
	private int lastX;
	private int lastY;

	public TimingFruitGenerator(AFruitRule fruitRule, int lastX, int lastY, float timeout) {
		this.fruitRule = fruitRule;
		this.lastX = lastX;
		this.lastY = lastY;
		this.timeout = timeout;
	}

	@Override
	public void act(float delta) {
		// FIXME: generate only after animation. Depending on time for now...
		if (timeout(delta)) {
			boolean generated = false, full = false;
			while (!generated && !full) {
				int posX = (int) (Math.random() * lastX);
				int posY = (int) (Math.random() * lastY);
				Vector2 candidate = new Vector2(posX, posY);
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
				Actions.rotateTo(720f, 1.75f)));
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

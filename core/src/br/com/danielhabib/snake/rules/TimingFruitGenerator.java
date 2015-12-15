package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.game.EntityBuilder;
import br.com.danielhabib.snake.game.EventFirerEntity;

public class TimingFruitGenerator extends Actor {

	private float timeout;
	private float time;
	private WorldManager worldManager;
	private int lastX;
	private int lastY;
	private EntityBuilder builder;

	public TimingFruitGenerator(EntityBuilder builder, WorldManager worldManager, int lastX, int lastY,
			float timeout) {
		this.builder = builder;
		this.worldManager = worldManager;
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
				for (Actor actor : worldManager.getMap()) {
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
		EventFirerEntity newFruit = builder.build(candidate);
		worldManager.put(newFruit);
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

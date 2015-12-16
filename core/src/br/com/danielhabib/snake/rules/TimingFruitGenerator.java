package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
	private TiledMapTileLayer layer;

	public TimingFruitGenerator(TiledMapTileLayer layer, EntityBuilder builder, WorldManager worldManager, int lastX, int lastY,
			float timeout) {
		this.layer = layer;
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
			Array<Vector2> allMapPositions = new Array<Vector2>();
			for (int x = 0; x < layer.getWidth(); x++) {
				for (int y = 0; y < layer.getHeight(); y++) {
					allMapPositions.add(new Vector2(x * Entity.SIZE, y * Entity.SIZE));
				}
			}

			for (Actor actor : worldManager.getMap()) {
				Vector2 position = new Vector2(actor.getX(), actor.getY());
				allMapPositions.removeValue(position, false);
			}

			if (allMapPositions.size != 0) {
				int freeIndex = (int) (Math.random() * allMapPositions.size);
				Vector2 candidate = allMapPositions.get(freeIndex);
				generate(candidate);
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

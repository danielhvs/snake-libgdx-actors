package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.entity.EventFirerEntity;

public class AFruitRule extends Actor implements WorldManager {

	private Array<EventFirerEntity> fruits;
	private Snake snake;
	private Array<Actor> worldMap;

	public AFruitRule(Array<Actor> worldMap, Array<EventFirerEntity> fruits, Snake snake) {
		this.worldMap = worldMap;
		this.fruits = fruits;
		this.snake = snake;
	}

	@Override
	public void act(float delta) {
		if (snake.hasActions() || snake.isDead()) {
			return;
		}
		EventFirerEntity toRemove = null;
		for (EventFirerEntity fruit : fruits) {
			Rectangle snakeBounds = snake.getBounds();
			Rectangle fruitBounds = fruit.getBounds();
			if (snakeBounds.contains(fruitBounds) || snakeBounds.overlaps(fruitBounds)) {
				toRemove = fruit;
				fruit.fireEvent();
				break;
			}
		}
		removeFromWorld(toRemove);
	}

	private void removeFromWorld(EventFirerEntity toRemove) {
		if (toRemove != null) {
			toRemove.remove();
			fruits.removeValue(toRemove, true);
			worldMap.removeValue(toRemove, true);
		}
	}

	@Override
	public Array<Actor> getMap() {
		return worldMap;
	}

	@Override
	public void put(EventFirerEntity newFruit) {
		fruits.add(newFruit);
		worldMap.add(newFruit);
		getStage().addActor(newFruit);
		newFruit.addAction(Actions.moveTo(0, 0));
		newFruit.addAction(Actions.moveTo(newFruit.getX(), newFruit.getY(), 0.75f));
		newFruit.addAction(Actions.rotateBy(360f, 0.75f));
	}
}

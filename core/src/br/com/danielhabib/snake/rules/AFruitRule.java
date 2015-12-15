package br.com.danielhabib.snake.rules;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import br.com.danielhabib.snake.game.EventFirerEntity;

public class AFruitRule extends Actor implements WorldManager {

	private List<EventFirerEntity> fruits;
	private Snake snake;
	private List<Actor> worldMap;

	public AFruitRule(List<Actor> worldMap, List<EventFirerEntity> fruits, Snake snake) {
		this.worldMap = worldMap;
		this.fruits = fruits;
		this.snake = snake;
	}

	@Override
	public void act(float delta) {
		EventFirerEntity toRemove = null;
		for (EventFirerEntity fruit : fruits) {
			Vector2 position = new Vector2(fruit.getX(), fruit.getY());
			if (snake.getPosition().equals(position)) {
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
			fruits.remove(toRemove);
			worldMap.remove(toRemove);
		}
	}

	@Override
	public List<Actor> getMap() {
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

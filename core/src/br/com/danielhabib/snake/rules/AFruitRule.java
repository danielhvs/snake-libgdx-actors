package br.com.danielhabib.snake.rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class AFruitRule extends Actor {

	private Map<Entity, IRule> map;
	private Snake snake;
	private List<Actor> mapEntities;

	public AFruitRule(List<Actor> mapEntities, Map<Entity, IRule> map, Snake snake) {
		this.mapEntities = mapEntities;
		this.map = map;
		this.snake = snake;
	}

	@Override
	public void act(float delta) {
		Iterator<Entry<Entity, IRule>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Entity, IRule> entry = iter.next();
			Entity entity = entry.getKey();
			if (snake.getPosition().equals(entity.getPosition())) {
				entry.getValue().fireEvent(entity);
				Array<Actor> actors = getStage().getActors();
				for (Actor actor : actors) {
					if (actor.equals(entity)) {
						actor.remove();
						mapEntities.remove(entity);
					}
				}
				iter.remove();
			}
			// FIXME: Performance. add break; here!?
		}
	}
}

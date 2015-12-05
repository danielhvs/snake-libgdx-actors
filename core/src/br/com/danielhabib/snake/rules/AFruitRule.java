package br.com.danielhabib.snake.rules;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import br.com.danielhabib.snake.game.DrawableManager;

public class AFruitRule implements IRule {

	private Map<Entity, IRule> map;
	private DrawableManager drawingManager;

	public AFruitRule(Map<Entity, IRule> map, DrawableManager drawingManager) {
		this.map = map;
		this.drawingManager = drawingManager;
	}

	@Override
	public Snake update(Snake snake) {
		Iterator<Entry<Entity, IRule>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Entity, IRule> entry = iter.next();
			Entity entity = entry.getKey();
			if (snake.getPosition().equals(entity.getPosition())) {
				Snake updated = entry.getValue().update(snake);
				drawingManager.remove(entity);
				iter.remove();
				return updated;
			}
		}

		return snake;
	}

}

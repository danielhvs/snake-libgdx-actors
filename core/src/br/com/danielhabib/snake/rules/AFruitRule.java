package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.game.DrawableManager;

public class AFruitRule implements IRule {

	private Array<Entity> entities;
	private DrawableManager drawingManager;
	private IRule rule;

	public AFruitRule(Entity entity, DrawableManager drawingManager, IRule rule) {
		this.entities = Array.with(entity);
		this.drawingManager = drawingManager;
		this.rule = rule;
	}

	@Override
	public Snake update(Snake snake) {
		for (Entity entity : entities) {
			if (snake.getPosition().equals(entity.getPosition())) {
				consumesFruit(snake, entity);
			}
		}
		return snake;
	}

	private Snake consumesFruit(Snake snake, Entity entity) {
		entities.removeValue(entity, true);
		drawingManager.remove(entity);
		return rule.update(snake);
	}

}

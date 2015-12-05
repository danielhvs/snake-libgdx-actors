package br.com.danielhabib.snake.game;

import java.util.Map;

import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.Snake;

public class DestroyEntityRule implements IRule {

	private Entity entity;
	private Map<Entity, IRule> map;
	private DrawableManager drawingManager;

	public DestroyEntityRule(Entity entity, Map<Entity, IRule> map, DrawableManager drawingManager) {
		this.entity = entity;
		this.map = map;
		this.drawingManager = drawingManager;
	}

	@Override
	public Snake update(Snake snake) {
		map.remove(entity);
		drawingManager.remove(entity);
		return snake.revert();
	}

}

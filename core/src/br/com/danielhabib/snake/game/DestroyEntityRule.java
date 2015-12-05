package br.com.danielhabib.snake.game;

import java.util.Map;

import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.Snake;

public class DestroyEntityRule implements IRule {

	private Entity entity;
	private Map<Entity, IRule> map;
	private DrawableManager drawingManager;
	private IRule ruleAfterDestroy;

	public DestroyEntityRule(Entity entity, Map<Entity, IRule> map, DrawableManager drawingManager, IRule ruleAfterDestroy) {
		this.entity = entity;
		this.map = map;
		this.drawingManager = drawingManager;
		this.ruleAfterDestroy = ruleAfterDestroy;
	}

	@Override
	public Snake update(Snake snake) {
		map.remove(entity);
		drawingManager.remove(entity);
		return ruleAfterDestroy.update(snake);
	}

}

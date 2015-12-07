package br.com.danielhabib.snake.game;

import java.util.Map;

import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.Snake;

public class DestroyEntityRule implements IRule {

	private Entity entity;
	private Map<Entity, IRule> map;
	private IRule ruleAfterDestroy;

	public DestroyEntityRule(Entity entity, Map<Entity, IRule> map, IRule ruleAfterDestroy) {
		this.entity = entity;
		this.map = map;
		this.ruleAfterDestroy = ruleAfterDestroy;
	}

	@Override
	public Snake update(Snake snake) {
		map.remove(entity);
		return ruleAfterDestroy.update(snake);
	}

}

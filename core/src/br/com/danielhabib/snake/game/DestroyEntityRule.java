package br.com.danielhabib.snake.game;

import java.util.Map;

import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.IRule;

public class DestroyEntityRule extends IRule {

	private Entity entity;
	private Map<Entity, IRule> map;
	private IRule ruleAfterDestroy;

	public DestroyEntityRule(Entity entity, Map<Entity, IRule> map, IRule ruleAfterDestroy) {
		this.entity = entity;
		this.map = map;
		this.ruleAfterDestroy = ruleAfterDestroy;
	}

	@Override
	public boolean fireEvent(float delta) {
		map.remove(entity);
		return ruleAfterDestroy.act(delta);
	}

}

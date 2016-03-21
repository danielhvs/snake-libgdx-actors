package br.com.danielhabib.snake.rules;

import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.entity.Entity;

public class DestroyEntityRule extends ARule {

	private Entity entity;
	private Map<Entity, IRule> map;
	private IRule ruleAfterDestroy;

	public DestroyEntityRule(Entity entity, Map<Entity, IRule> map, IRule ruleAfterDestroy) {
		this.entity = entity;
		this.map = map;
		this.ruleAfterDestroy = ruleAfterDestroy;
	}

	@Override
	public void fireEvent(Actor source) {
		ruleAfterDestroy.fireEvent(source);
		map.remove(entity);
	}

}

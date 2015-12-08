package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class FruitRule extends IRule {
	public FruitRule(Actor actor) {
		super(actor);
	}

	@Override
	public boolean fireEvent(float delta) {
		return getTarget().fire(new SnakeEvent(SnakeEvent.Type.addTail));
	}


}

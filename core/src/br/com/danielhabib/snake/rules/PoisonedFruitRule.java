package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.danielhabib.snake.listeners.SnakeEvent;

public class PoisonedFruitRule extends ARule {
	public PoisonedFruitRule(Stage stage) {
		super(stage);
	}

	@Override
	public void fireEvent(Actor source) {
		for (Actor actor : stage.getActors()) {
			actor.fire(new SnakeEvent(source, SnakeEvent.Type.removeTail));
		}
	}


}

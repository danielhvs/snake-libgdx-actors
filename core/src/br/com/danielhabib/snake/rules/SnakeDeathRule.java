package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.screens.ScreenEnum;
import br.com.danielhabib.snake.screens.ScreenManager;

public class SnakeDeathRule extends ARule {
	public SnakeDeathRule() {
		super();
	}

	// FIXME: Fire some event...
	@Override
	public void fireEvent(Actor source) {
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
	}


}

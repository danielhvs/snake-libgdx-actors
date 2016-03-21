package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;

import br.com.danielhabib.snake.screens.ScreenEnum;
import br.com.danielhabib.snake.screens.ScreenManager;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		VisUI.load(SkinScale.X2);
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
	}


}

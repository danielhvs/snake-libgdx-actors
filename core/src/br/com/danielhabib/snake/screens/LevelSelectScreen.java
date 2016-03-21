package br.com.danielhabib.snake.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.ui.ButtonFactory;
import br.com.danielhabib.snake.ui.UIFactory;

public class LevelSelectScreen extends AbstractScreen {

	protected LevelSelectScreen(SoundManager sounds) {
		super(sounds);
	}

	@Override
	public void buildStage() {
		VisTextButton playButton = ButtonFactory.newButton("First level! GO!");
		VisTextButton level2Button = ButtonFactory.newButton("Crazy level! GO!");
		VisTextButton backButton = ButtonFactory.newButton("<-- Back");

		Table table = UIFactory.newMenu("OMG! Crazy Snakes!", playButton, level2Button, backButton);

		playButton.addListener(UIFactory.createListener(ScreenEnum.GAME, 1));
		level2Button.addListener(UIFactory.createListener(ScreenEnum.GAME, 2));
		backButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		addActor(table);
	}


}

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.rules.SoundManager;

public class LevelSelectScreen extends AbstractScreen {

	protected LevelSelectScreen(SoundManager sounds) {
		super(sounds);
	}

	@Override
	public void buildStage() {

		Label title = new VisLabel("OMG! Crazy Snakes!");

		VisTextButton playButton = ButtonFactory.newButton("First level! GO!");
		VisTextButton level2Button = ButtonFactory.newButton("Crazy level! GO!");
		VisTextButton backButton = ButtonFactory.newButton("<-- Back");

		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);

		UIFactory.addButtonToTable(playButton, table);
		UIFactory.addButtonToTable(level2Button, table);
		UIFactory.addButtonToTable(backButton, table);

		playButton.addListener(UIFactory.createListener(ScreenEnum.GAME, 1));
		level2Button.addListener(UIFactory.createListener(ScreenEnum.GAME, 2));
		backButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		addActor(table);

	}

}

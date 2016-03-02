package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.rules.ToggleButtonTextInputListener;

public class ConfigScreen extends AbstractScreen {

	@Override
	public void buildStage() {

		Label title = new VisLabel("Let's change some stuff...");

		VisTextButton soundButton = ButtonFactory.newButton("Sound");
		VisTextButton musicButton = ButtonFactory.newButton("Music");
		VisTextButton backButton = ButtonFactory.newButton("<-- Back");

		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);

		UIFactory.addButtonToTable(soundButton, table);
		UIFactory.addButtonToTable(musicButton, table);
		UIFactory.addButtonToTable(backButton, table);

		soundButton.addListener(new ToggleButtonTextInputListener(soundButton, "Sound", "NO_SOUND!"));
		musicButton.addListener(new ToggleButtonTextInputListener(musicButton, "Music", "NO_MUSIC!"));
		backButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		addActor(table);

	}

}

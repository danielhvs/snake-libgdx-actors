package br.com.danielhabib.snake.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.rules.CompositeInputListener;
import br.com.danielhabib.snake.rules.SoundConfigInputListener;
import br.com.danielhabib.snake.rules.SoundManager;
import br.com.danielhabib.snake.rules.ToggleButtonTextInputListener;

public class ConfigScreen extends AbstractScreen {

	protected ConfigScreen(SoundManager sounds) {
		super(sounds);
	}

	@Override
	public void buildStage() {
		Label title = new VisLabel("Let's change some stuff...");

		VisTextButton soundButton = ButtonFactory.newButton(sounds.isEnabled() ? "Sound" : "NO_SOUND");
		VisTextButton musicButton = ButtonFactory.newButton("Music");
		VisTextButton backButton = ButtonFactory.newButton("<-- Back");

		Table table = new Table();
		UIFactory.setTitle(title, table);
		UIFactory.addButtonToTable(soundButton, table);
		UIFactory.addButtonToTable(musicButton, table);
		UIFactory.addButtonToTable(backButton, table);

		soundButton.addListener(new CompositeInputListener(
				new ToggleButtonTextInputListener(soundButton, "Sound", "NO_SOUND"),
				new SoundConfigInputListener(sounds)));
		musicButton.addListener(new ToggleButtonTextInputListener(musicButton, "Music", "NO_MUSIC!"));
		backButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		addActor(table);

	}

}

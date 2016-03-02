package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Splash extends AbstractScreen {

	@Override
	public void buildStage() {
		Label title = new VisLabel("OMG! Crazy Snakes!");

		VisTextButton playButton = ButtonFactory.newButton("Go go go!");
		VisTextButton levelButton = ButtonFactory.newButton("Let's see...");
		VisTextButton configButton = ButtonFactory.newButton("Change stuff...");
		VisTextButton quitButton = ButtonFactory.newButton("I'm out!");

		// Use Vistable?
		Table table = new Table();


		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);

		UIFactory.addButtonToTable(playButton, table);
		UIFactory.addButtonToTable(levelButton, table);
		UIFactory.addButtonToTable(configButton, table);
		UIFactory.addButtonToTable(quitButton, table);

		playButton.addListener(UIFactory.createListener(ScreenEnum.GAME, 1));
		levelButton.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT));
		configButton.addListener(UIFactory.createListener(ScreenEnum.CONFIG));
		quitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});

		addActor(table);
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		dispose();
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}

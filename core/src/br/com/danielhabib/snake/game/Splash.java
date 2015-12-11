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
		VisTextButton quitButton = ButtonFactory.newButton("I'm out!");

		// Use Vistable?
		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);
		table.row();
		int width = Gdx.graphics.getWidth() / 4;
		int height = Gdx.graphics.getHeight() / 10;
		table.add(playButton).width(width).height(height);
		table.getCell(playButton).spaceBottom(10);
		table.row();
		table.add(levelButton).width(width).height(height);
		table.getCell(levelButton).spaceBottom(10);
		table.row();
		table.add(quitButton).width(width).height(height);
		table.getCell(quitButton).spaceBottom(10);


		playButton.addListener(UIFactory.createListener(ScreenEnum.GAME));
		levelButton.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT));
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

package br.com.danielhabib.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.ui.ButtonFactory;
import br.com.danielhabib.snake.ui.UIFactory;

public class Splash extends AbstractScreen {

	public Splash(SoundManager sounds) {
		super(sounds);
	}

	@Override
	public void buildStage() {
		VisTextButton playButton = ButtonFactory.newButton("Go go go!");
		VisTextButton levelButton = ButtonFactory.newButton("Let's see...");
		VisTextButton configButton = ButtonFactory.newButton("Change stuff...");
		VisTextButton quitButton = ButtonFactory.newButton("I'm out!");

		Table table = UIFactory.newMenu("OMG! Crazy Snakes!", playButton, levelButton, configButton, quitButton);

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

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Splash extends AbstractScreen {

	private Game game;

	public Splash(Game game) {
		this.game = game;
	}

	@Override
	public void buildStage() {
		Label title = new VisLabel("OMG! Crazy Snakes!");

		VisTextButton playButton = ButtonFactory.newButton("Go go go!");
		VisTextButton quitButton = ButtonFactory.newButton("I'm out!");

		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);
		table.row();
		table.add(playButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(playButton).spaceBottom(10);
		table.row();
		table.add(quitButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(quitButton).spaceBottom(10);

		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SnakeScreen(game));
				clear();
				return true;
			}
		});
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

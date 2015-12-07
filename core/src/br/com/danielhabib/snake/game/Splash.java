package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Splash extends AbstractScreen {

	private Game game;

	public Splash(Game game) {
		this.game = game;
	}

	@Override
	public void buildStage() {
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.ORANGE);
		Label title = new Label("OMG! Crazy Snakes!", labelStyle);
		Skin skin = new Skin(new TextureAtlas("buttons.pack"));
		TextButtonStyle buttonStyle = newSnakeButtonStyle(skin);
		TextButton playButton = newButton("Go go go!", buttonStyle);
		TextButton quitButton = newButton("I'm out!", buttonStyle);
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
		// table.debug();

		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SnakeScreen(game));
				stage.clear();
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

		stage.addActor(table);

	}

	private TextButton newButton(String text, TextButtonStyle buttonStyle) {
		return new TextButton(text, buttonStyle);
	}

	private TextButtonStyle newSnakeButtonStyle(Skin skin2) {
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin2.getDrawable("button");
		buttonStyle.over = skin2.getDrawable("buttonpressed");
		buttonStyle.down = skin2.getDrawable("buttonpressed");
		buttonStyle.font = new BitmapFont(Gdx.files.internal("font.fnt"));
		return buttonStyle;
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

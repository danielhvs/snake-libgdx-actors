package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeFactory;

public class Splash extends AbstractScreen {

	private Game game;

	public Splash(Game game) {
		this.game = game;
	}

	@Override
	public void buildStage() {
		Texture headTexture = new Texture(Gdx.files.internal("head.png"));
		Texture tailTexture = new Texture(Gdx.files.internal("tail.png"));
		Texture pieceTexture = new Texture(Gdx.files.internal("circle.png"));
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.ORANGE);
		Label title = new Label("OMG! Crazy Snakes!", labelStyle);

		TextButton playButton = ButtonFactory.newButton("Go go go!");
		TextButton quitButton = ButtonFactory.newButton("I'm out!");

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
		Snake snake = SnakeFactory.newSnakeAtXY(5, 1, Direction.RIGHT, headTexture, pieceTexture, tailTexture);
	}

	private TextButton newButton(String text, TextButtonStyle buttonStyle) {
		return new TextButton(text, buttonStyle);
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

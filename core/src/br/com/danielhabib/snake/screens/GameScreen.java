package br.com.danielhabib.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisTextButton;

import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.sound.SoundManager;
import br.com.danielhabib.snake.ui.ButtonFactory;
import br.com.danielhabib.snake.ui.FpsCountingLabel;
import br.com.danielhabib.snake.ui.SnakeTimer;
import br.com.danielhabib.snake.ui.UIFactory;

public abstract class GameScreen extends AbstractScreen {

	private boolean paused = false;
	protected Snake snake;
	private Label fpsLabel;
	private Stage uiStage;

	protected GameScreen(SoundManager sounds) {
		super(sounds);
		uiStage = new Stage(new ScreenViewport(new OrthographicCamera()), new SpriteBatch());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!paused) {
			act(delta);
		}
		uiStage.act(delta);

		getCamera().position.set(calculateXCamera(), calculateYCamera(), 0f);
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);

		uiStage.draw();
		draw();
	}

	private float calculateYCamera() {
		float center = getHeight() / 2;
		if (snake.getPosition().y >= center) {
			return snake.getPosition().y;
		}
		else {
			return center;
		}
	}

	private float calculateXCamera() {
		float center = getWidth() / 2;
		if (snake.getPosition().x >= center) {
			return snake.getPosition().x;
		}
		else {
			return center;
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		uiStage.getViewport().update(width, height);
	}

	@Override
	public void buildStage() {
		BitmapFont font = new BitmapFont();
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		fpsLabel = new FpsCountingLabel("", labelStyle);
		buildUiStage();
		addActor(fpsLabel);
	}

	private void buildUiStage() {
		SnakeTimer timer = new SnakeTimer();

		VisTextButton playButton = ButtonFactory.newButton("Menu");
		playButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		Table table = new Table();
		// FIXME size?
		float entitySize = 32f;
		table.setBounds(0, Gdx.graphics.getHeight() - entitySize, Gdx.graphics.getWidth(), entitySize);
		table.left();

		table.add(timer);
		table.add(playButton).expandX().right();
		uiStage.addActor(table);
	}

	@Override
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new SnakeUIInputProcessor(this));
		multiplexer.addProcessor(uiStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	public void pauseGame() {
		paused = true;
	}

	public void unpauseGame() {
		paused = false;
	}
}

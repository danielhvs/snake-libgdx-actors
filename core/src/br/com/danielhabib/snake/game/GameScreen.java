package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import br.com.danielhabib.snake.rules.Snake;

public abstract class GameScreen extends AbstractScreen {
	private boolean paused = false;
	protected Snake snake;
	private Label fpsLabel;
	protected Sound soundDied;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!paused) {
			act(delta);
		}

		getCamera().position.set(snake.getPosition().x, snake.getPosition().y, 0f);
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);

		draw();
	}

	@Override
	public void buildStage() {
		soundDied = Gdx.audio.newSound(Gdx.files.internal("dead.mp3"));
		BitmapFont font = new BitmapFont();
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		fpsLabel = new FpsCountingLabel("", labelStyle);
		addActor(fpsLabel);
	}

	@Override
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new SnakeUIInputProcessor(this));
		Gdx.input.setInputProcessor(multiplexer);
	}

	public void pauseGame() {
		paused = true;
	}

	public void unpauseGame() {
		paused = false;
	}
}

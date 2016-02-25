package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SoundManagerFactory;
import br.com.danielhabib.snake.rules.SoundReader;

public abstract class GameScreen extends AbstractScreen {
	private boolean paused = false;
	protected Snake snake;
	private Label fpsLabel;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!paused) {
			act(delta);
		}

		getCamera().position.set(calculateXCamera(), calculateYCamera(), 0f);
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);

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
	public void buildStage() {
		sounds = new SoundManagerFactory().newSoundManager(new SoundReader());
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

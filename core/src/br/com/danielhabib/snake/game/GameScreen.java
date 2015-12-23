package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import br.com.danielhabib.snake.rules.Snake;

public abstract class GameScreen extends AbstractScreen {
	private boolean paused = false;
	protected Snake snake;
	protected FPSLogger fps;

	public GameScreen() {
		this.fps = new FPSLogger();
	}

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
		fps.log();
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

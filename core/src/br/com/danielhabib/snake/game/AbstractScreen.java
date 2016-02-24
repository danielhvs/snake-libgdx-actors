package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.com.danielhabib.snake.rules.SoundManager;

public abstract class AbstractScreen extends Stage implements Screen {

	protected SoundManager sounds;

	protected AbstractScreen() {
		super(new ScreenViewport(new OrthographicCamera()), new SpriteBatch());
	}

	public abstract void buildStage();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		act(delta);
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);

		draw();
	}

	// FIXME: Dispose.

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		sounds.dispose();
		super.dispose();
	}


}

package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class AbstractScreen implements Screen {

	protected Stage stage;
	protected AbstractScreen() {
		OrthographicCamera camera = new OrthographicCamera();
		stage = new Stage(new ScreenViewport(camera), new SpriteBatch());
	}

	public abstract void buildStage();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		stage.getCamera().update();
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);

		stage.draw();
	}

	// FIXME: Dispose.

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		buildStage();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
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
}
